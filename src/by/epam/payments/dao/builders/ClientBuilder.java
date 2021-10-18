package by.epam.payments.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Client;
import by.epam.payments.dao.dbColoumns.ColomnName;
import by.epam.payments.dao.dbColoumns.ColoumnNumberInPreparedStatement;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.validation.Validator;

public class ClientBuilder implements EntityBuilder<Client> {
	
	private static Logger logger = LogManager.getLogger();
	
	public ClientBuilder() {
	}

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Client client) throws DAOException {
		boolean transmint = true;
		try {
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_1, client.getId());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_2, client.getIdentificationNumber());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_3, client.getLastName());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_4, client.getFirstName());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_5, client.getPatronymic());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_6, client.getPhoneNumber());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_7, client.getRegistrationAddress());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_8, client.getRegistrationAddress());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_9, client.getUserId());
		} catch (SQLException e) {
			transmint = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return transmint;
	}

	@Override
	public List<Client> getListOfEntities(ResultSet resultSet) {
		if(Validator.isNull(resultSet)) {
			return new ArrayList<Client>();
		}
		
		List<Client> clients = new ArrayList<Client>();
		
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(ColomnName.CLIENT_ID);
				String identifiactionNumber = resultSet.getString(ColomnName.PERSONAL_IDENTIFIACTION_NUMBER);
				String lastName = resultSet.getString(ColomnName.SURNAME);
				String firstName = resultSet.getString(ColomnName.NAME);
				String patronymic = resultSet.getString(ColomnName.PATRONYMIC);
				String phoneNumber = resultSet.getString(ColomnName.PHONE_NUMBER);
				String regisatrtionAddress = resultSet.getString(ColomnName.REGISTARTION_ADDRESS);
				String realAddress = resultSet.getString(ColomnName.REAL_ADDRESS);
				int userId = resultSet.getInt(ColomnName.USERS_USER_ID);
				Client client = new Client(id, identifiactionNumber, lastName, firstName, patronymic, phoneNumber, regisatrtionAddress, realAddress, userId);
				clients.add(client);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			e.getMessage();
		}
		return clients;
	}

}
