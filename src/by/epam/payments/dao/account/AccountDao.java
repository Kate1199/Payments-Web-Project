package by.epam.payments.dao.account;

import java.util.List;

import by.epam.payments.bean.Account;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface AccountDao extends Dao<Account> {
	
	public List<Account> findAllExceptCurrent(int clientId, String currentAccountId) throws DAOException;
	public List<Account> takeAccountByClientId(int clientId) throws DAOException;
	public long takeBalanceByAccountNumber(String sqlRequest, String numberIban) throws DAOException;
	public boolean updateAccountBalance(long balance, String numberIban) throws DAOException;
}
