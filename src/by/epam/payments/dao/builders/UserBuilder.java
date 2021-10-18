package by.epam.payments.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Role;
import by.epam.payments.bean.User;
import by.epam.payments.dao.dbColoumns.ColomnName;
import by.epam.payments.dao.dbColoumns.ColoumnNumberInPreparedStatement;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.BlobByteArrayMaker;
import by.epam.payments.util.validation.Validator;

public class UserBuilder implements EntityBuilder<User> {
	
	private static Logger logger = LogManager.getLogger();

	public UserBuilder() {
	}

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, User user) throws DAOException {
		boolean transmit;

		try {
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_1, user.getId());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_2, user.getLogin());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_3, user.getEmail());
			preparedStatement.setBlob(ColoumnNumberInPreparedStatement.COLOUMN_4, new SerialBlob(user.getPassword()));
			preparedStatement.setBlob(ColoumnNumberInPreparedStatement.COLOUMN_5, new SerialBlob(user.getSalt()));
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_6, user.getRole().toString());
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
				int id = resultSet.getInt(ColomnName.USER_ID);
				String login = resultSet.getString(ColomnName.LOGIN);
				String email = resultSet.getString(ColomnName.EMAIL);
				byte[] password = BlobByteArrayMaker.makeByteArray(resultSet, ColomnName.PASSWORD);
				byte[] salt = BlobByteArrayMaker.makeByteArray(resultSet, ColomnName.USER_SALT);
				Role role = Role.valueOf(resultSet.getString(ColomnName.ROLE).toUpperCase());
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
