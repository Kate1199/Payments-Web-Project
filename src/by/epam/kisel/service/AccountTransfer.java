package by.epam.kisel.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.kisel.dao.EntityTransaction;
import by.epam.kisel.dao.account.AccountDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.RuMessage;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.payments.bean.Account;

public class AccountTransfer {
	
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
		long balance = MinValues.MIN_LONG_VALUE;
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
			request.setAttribute(ParameterName.MESSAGE, RuMessage.NOT_ENOUGH_MONEY);
		} 
		return enough;
	}
}
