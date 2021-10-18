package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.dbColoumns.ColomnName;
import by.epam.kisel.dao.dbColoumns.ColoumnNumberInPreparedStatement;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Account;

public class AccountBuilder implements EntityBuilder<Account> {

	private static Logger logger = LogManager.getLogger();
	
	public AccountBuilder() {
	}

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Account entity) throws DAOException {
		try {
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_1, entity.getId());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_2, entity.getNumberIBAN());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_3, entity.getCurrency());
			preparedStatement.setLong(ColoumnNumberInPreparedStatement.COLOUMN_4, entity.getBalance());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_5, entity.getClientId());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_6, entity.getBankDepartmentId());
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
				int id = resultSet.getInt(ColomnName.ACCOUNT_ID);
				String numberIBAN = resultSet.getString(ColomnName.ACCOUNT_IBAN_NUMBER);
				String currency = resultSet.getString(ColomnName.CURRENCY);
				long balance = resultSet.getLong(ColomnName.BALANCE);
				int clientId = resultSet.getInt(ColomnName.CLIENTS_CLIENT_ID);
				int bankDepartmentId = resultSet.getInt(ColomnName.BANK_DEPARTMENTS_DEPARTMENT_ID);
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
