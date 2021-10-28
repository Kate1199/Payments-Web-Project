package by.epam.payments.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.payments.bean.Account;
import by.epam.payments.dao.EntityTransaction;
import by.epam.payments.dao.account.AccountDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.util.MinValue;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.SqlRequest;
import by.epam.payments.util.validation.Validator;

public class AccountTransfer {
	
	private static final String NOT_ENOUGH_MONEY = "Недостаточно средств";
	
	private static final boolean SENDER = true;
	private static final boolean RECIEVER = false;

	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private EntityTransaction<Account> transaction = new EntityTransaction<Account>();
	private HttpServletRequest request;
	
	public AccountTransfer() {
	}
	
	public AccountTransfer(HttpServletRequest request) {
		this.request = request;
	}
	
	public boolean doTransfer(String senderNumberIban, String recieverNumberIban, long sum) throws ServiceException {
		boolean transfer = true;
		
		if(sum <= 0 || Validator.isNull(senderNumberIban) || Validator.isNull(recieverNumberIban)) {
			return false;
		}

		try {
			transaction.initTransaction();
			transaction.commit();
		} catch (DAOException e) {
			transfer = false;
			throw new ServiceException(e.getMessage());
		}
		makeUpdate(sum, senderNumberIban, SENDER);
		makeUpdate(sum, recieverNumberIban, RECIEVER);
		
		try {
			transaction.commit();
			transaction.endTransaction();
		} catch (DAOException e) {
			transfer = false;
		}
		return transfer;
	}
	
	private boolean makeUpdate(long sum, String numberIban, boolean sender) throws ServiceException {
		boolean update = true;
		long balance = countBalance(numberIban, sum, sender);
		try {
			accountDao.updateAccountBalance(balance, numberIban);
		} catch (DAOException e) {
			transaction.rollback();
			throw new ServiceException(e.getMessage());
		}
		return update;
	}
	
	private long countBalance(String numberIban, long sum, boolean sender) throws ServiceException {
		long balance = MinValue.MIN_LONG_VALUE;
		try {
			balance = accountDao.takeBalanceByAccountNumber(SqlRequest.GET_BALANCE_BY_ACCOUNT_NUMBER, numberIban);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		if(sender) {
			isEnoughMoney(balance, sum);
			balance = balance - sum;
		} else {
			balance = balance + sum;
		}
		return balance;
	}
	
	private boolean isEnoughMoney(long balance, long sum) {
		boolean enough = true;
		if(sum > balance) {
			enough = false;
			request.setAttribute(ParameterName.MESSAGE, NOT_ENOUGH_MONEY);
		} 
		return enough;
	}
}
