package by.epam.payment.dao.account;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payment.bean.Account;
import by.epam.payment.dao.SqlDatabaseDAO;
import by.epam.payment.dao.builder.AccountBuilder;
import by.epam.payment.exception.DAOException;
import by.epam.payment.util.MinValue;
import by.epam.payment.util.parameterConstant.LogMessage;
import by.epam.payment.util.parameterConstant.SqlRequest;
import by.epam.payment.util.validation.Validator;

public class AccountDaoImpl extends SqlDatabaseDAO<Account> implements AccountDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private AccountBuilder accountBuilder = new AccountBuilder();
	
	public AccountDaoImpl() {
	}
	
	@Override
	public List<Account> findAllExceptCurrent(int clientId, String currentAccountId) throws DAOException {
		return findByParameterEntity(SqlRequest.FIND_ALL_CLIENT_ACCOUNTS_EXCEPT_CURRENT, accountBuilder, 
				clientId, currentAccountId);
	}
	
	@Override
	public List<Account> takeAccountByClientId(int clientId) throws DAOException {
		if(clientId <= 0) {
			return new ArrayList<Account>();
		}
		return findByParameterEntity(SqlRequest.FIND_ACCOUNT_BY_CLIENT_ID, accountBuilder, clientId);
	}
	
	@Override
	public long takeBalanceByAccountNumber(String sqlRequest, String numberIban) throws DAOException {
		if(Validator.isNull(numberIban)) {
			return MinValue.MIN_LONG_VALUE;
		}
		return (long) findByParameterField(sqlRequest, numberIban);
	}
	
	@Override
	public boolean updateAccountBalance(long balance, String numberIban) throws DAOException {
		if(balance < 0 || Validator.isNull(numberIban)) {
			return false;
		}
		return updateCore(SqlRequest.UPDATE_ACCOUNT_BALANCE, balance, numberIban);
	}

	@Override
	public List<Account> findAll() throws DAOException {
		return super.findAll(SqlRequest.FIND_ALL_ACCOUNTS, accountBuilder);
	}

	@Override
	public boolean insertInto(Account account) throws DAOException {
		return super.addNewData(SqlRequest.ADD_ACCOUNT, account, accountBuilder);
	}

	@Override
	public Account findEntityById(int id) throws DAOException {
		if(id <= 0) {
			return new Account();
		}
		List<Account> result = super.findByParameterEntity(SqlRequest.FIND_ACCOUNT_BY_ID, accountBuilder, id);
		return takeEntity(result);
	}

	@Override
	public Account update(Account account) throws DAOException {
		logger.log(Level.WARN, LogMessage.ACCOUNT_UPDATE + account.getId());
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Account account) throws DAOException {
		update(SqlRequest.HIDE_ACCOUNT, account.getNumberIBAN(), account.getCurrency(), 
				account.getBalance(), account.getClientId(), account.getBankDepartmentId());
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		update(SqlRequest.HIDE_ACCOUNT_BY_ID, id);
		return false;
	}
}
