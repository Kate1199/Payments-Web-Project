package by.epam.kisel.dao.account;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.AccountBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Account;

public class AccountDaoImpl extends SqlDatabaseDAO<Account> implements AccountDao {
	
	private static Logger logger = LogManager.getLogger();
	
	public AccountDaoImpl() {
	}
	
	@Override
	public List<Account> takeAccountByClientId(int clientId) throws DAOException {
		if(clientId <= 0) {
			return new ArrayList<Account>();
		}
		return findByParameterEntity(SqlRequest.FIND_ACCOUNT_BY_CLIENT_ID, new AccountBuilder(), clientId);
	}
	
	public long takeBalanceByAccountNumber(String sqlRequest, String numberIban) throws DAOException {
		if(Validator.isNull(numberIban)) {
			return MinValues.MIN_LONG_VALUE;
		}
		return (long) findByParameterField(sqlRequest, numberIban);
	}
	
	public boolean updateAccountBalance(long balance, String numberIban) throws DAOException {
		if(balance < 0 || Validator.isNull(numberIban)) {
			return false;
		}
		return updateCore(SqlRequest.GET_BALANCE_BY_ACCOUNT_NUMBER, balance, numberIban);
	}

	@Override
	public List<Account> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertInto(Account entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account findEntityById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Account entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}
