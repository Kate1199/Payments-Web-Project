package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.dbColoumns.ClientColoumns;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Client;

public class ClientBuilder implements EntityBuilder<Client> {
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Client client) throws DAOException {
		boolean transmint = true;
		try {
			preparedStatement.setInt(ClientColoumns.ID, client.getId());
			preparedStatement.setString(ClientColoumns.IDENTIFIACTION_NUMBER, client.getIdentificationNumber());
			preparedStatement.setString(ClientColoumns.LAST_NAME, client.getLastName());
			preparedStatement.setString(ClientColoumns.FIRST_NAME, client.getFirstName());
			preparedStatement.setString(ClientColoumns.PATRONYMIC, client.getPatronymic());
			preparedStatement.setString(ClientColoumns.PHONE_NUMBER, client.getPhoneNumber());
			preparedStatement.setString(ClientColoumns.REGISTARTION_ADDRESS, client.getRegistrationAddress());
			preparedStatement.setString(ClientColoumns.REAL_ADDRESS, client.getRegistrationAddress());
			preparedStatement.setInt(ClientColoumns.USER_ID, client.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			transmint = false;
			logger.log(Level.ERROR, e.getMessage());
			e.printStackTrace();
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
				int id = resultSet.getInt(ClientColoumns.ID);
				String identifiactionNumber = resultSet.getString(ClientColoumns.IDENTIFIACTION_NUMBER);
				String lastName = resultSet.getString(ClientColoumns.LAST_NAME);
				String firstName = resultSet.getString(ClientColoumns.FIRST_NAME);
				String patronymic = resultSet.getString(ClientColoumns.PATRONYMIC);
				String phoneNumber = resultSet.getString(ClientColoumns.PHONE_NUMBER);
				String regisatrtionAddress = resultSet.getString(ClientColoumns.REGISTARTION_ADDRESS);
				String realAddress = resultSet.getString(ClientColoumns.REAL_ADDRESS);
				int userId = resultSet.getInt(ClientColoumns.USER_ID);
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
