package by.epam.kisel.dao.account;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.EntityTransaction;
import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.AccountBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Account;

public class AccountDaoImpl extends SqlDatabaseDAO<Account> implements AccountDao {
	
	private static final int BALANCE_INDEX = 1;
	private static final int NUMBER_IBAN_INDEX = 2;
	
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
	
	public boolean safeUpdate(long balance, String numberIban, EntityTransaction<Account> transaction) throws DAOException {
		boolean update = true;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getConnection().prepareStatement(SqlRequest.UPDATE_ACCOUNT_BALANCE);
			preparedStatement.setLong(BALANCE_INDEX, balance);
			preparedStatement.setString(NUMBER_IBAN_INDEX, numberIban);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			update = false;
			transaction.rollback();
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return update;
	}

	@Override
	public List<Account> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserInto(Account entity) throws DAOException {
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
