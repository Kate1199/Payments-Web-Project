package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.dbColoumns.DatabaseColoumn;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Account;

public class AccountBuilder implements EntityBuilder<Account> {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Account entity) throws DAOException {
		try {
			preparedStatement.setInt(DatabaseColoumn.ACCOUNT_ID, entity.getId());
			preparedStatement.setString(DatabaseColoumn.NUMBER_IBAN, entity.getNumberIBAN());
			preparedStatement.setString(DatabaseColoumn.CURRENCY, entity.getCurrency());
			preparedStatement.setLong(DatabaseColoumn.BALANCE, entity.getBalance());
			preparedStatement.setInt(DatabaseColoumn.CLIENT_ID, entity.getClientId());
			preparedStatement.setInt(DatabaseColoumn.BANK_DEPARTMENT_ID, entity.getBankDepartmentId());
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}

		return false;
	}

	@Override
	public List<Account> getListOfEntities(ResultSet resultSet) throws DAOException {
		List<Account> accounts = new ArrayList<Account>();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(DatabaseColoumn.ACCOUNT_ID);
				String numberIBAN = resultSet.getString(DatabaseColoumn.NUMBER_IBAN);
				String currency = resultSet.getString(DatabaseColoumn.CURRENCY);
				long balance = resultSet.getLong(DatabaseColoumn.BALANCE);
				int clientId = resultSet.getInt(DatabaseColoumn.CLIENT_ID);
				int bankDepartmentId = resultSet.getInt(DatabaseColoumn.BANK_DEPARTMENT_ID);
				Account account = new Account(id, numberIBAN, currency, balance, clientId, bankDepartmentId);
				accounts.add(account);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return accounts;
	}

}
