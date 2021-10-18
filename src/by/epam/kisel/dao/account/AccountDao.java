package by.epam.kisel.dao.account;

import java.util.List;

import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Account;

public interface AccountDao extends Dao<Account> {
	
	public List<Account> takeAccountByClientId(int clientId) throws DAOException;
}
