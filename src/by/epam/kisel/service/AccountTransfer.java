package by.epam.kisel.service;

import javax.sql.rowset.serial.SerialException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.EntityTransaction;
import by.epam.kisel.dao.account.AccountDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.payments.bean.Account;

public class AccountTransfer {
	
	private static final boolean SENDER = true;
	private static final boolean RECIEVER = false;

	private AccountDaoImpl accountDao = new AccountDaoImpl();
	
	public boolean doTransfer(String senderNumberIban, String recieverNumberIban, long sum) throws ServiceException {
		boolean transfer = true;
		
		EntityTransaction<Account> transaction = new EntityTransaction<Account>();
		try {
			transaction.initTransaction();
			transaction.commit();
			long senderBalance = countBalance(senderNumberIban, sum, SENDER);
			accountDao.safeUpdate(senderBalance, senderNumberIban, transaction);
			long recieverBalance = countBalance(recieverNumberIban, sum, RECIEVER);
			accountDao.safeUpdate(recieverBalance, recieverNumberIban, transaction);
			transaction.commit();
			transaction.endTransaction();
		} catch (DAOException e) {
			transfer = false;
			throw new ServiceException(e.getMessage());
		}
		return transfer;
	}
	
	private long countBalance(String numberIban, long sum, boolean sender) throws ServiceException {
		long balance = MinValues.MIN_LONG_VALUE;
		try {
			balance = accountDao.takeBalanceByAccountNumber(SqlRequest.GET_BALANCE_BY_ACCOUNT_NUMBER, numberIban);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		if(sender) {
			balance = balance - sum;
		} else {
			balance = balance + sum;
		}
		return balance;
	}
}
