package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.dbColoumns.UserColoumns;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.BlobByteArrayMaker;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Role;
import by.epam.payments.bean.User;

public class UserBuilder implements EntityBuilder<User> {
	
	private static Logger logger = LogManager.getLogger();

	public UserBuilder() {
	}

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, User user) throws DAOException {
		boolean transmit;

		try {
			preparedStatement.setInt(UserColoumns.ID_COLOUMN, user.getId());
			preparedStatement.setString(UserColoumns.LOGIN_COLOUMN, user.getLogin());
			preparedStatement.setString(UserColoumns.EMAIL_COLOUMN, user.getEmail());
			preparedStatement.setBlob(UserColoumns.PASSWORD_COLOUMN, new SerialBlob(user.getPassword()));
			preparedStatement.setBlob(UserColoumns.SALT_COLOUMN, new SerialBlob(user.getSalt()));
			preparedStatement.setString(UserColoumns.ROLE_COLOUMN, user.getRole().toString());
			transmit = true;
		} catch (SQLException e) {
			transmit = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return transmit;
	}

	@Override
	public List<User> getListOfEntities(ResultSet resultSet) throws DAOException {
		if (Validator.isNull(resultSet)) {
			return new ArrayList<User>();
		}
		List<User> users = new ArrayList<User>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(UserColoumns.ID_COLOUMN);
				String login = resultSet.getString(UserColoumns.LOGIN_COLOUMN);
				String email = resultSet.getString(UserColoumns.EMAIL_COLOUMN);
				byte[] password = BlobByteArrayMaker.makeByteArray(resultSet, UserColoumns.PASSWORD_COLOUMN);
				byte[] salt = BlobByteArrayMaker.makeByteArray(resultSet, UserColoumns.SALT_COLOUMN);
				Role role = Role.valueOf(resultSet.getString(UserColoumns.ROLE_COLOUMN).toUpperCase());
				User user = new User(id, login, email, password, salt, role);
				users.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return users;
	}

}
