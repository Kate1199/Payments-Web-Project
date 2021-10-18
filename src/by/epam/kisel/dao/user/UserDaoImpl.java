package by.epam.kisel.dao.user;

import java.util.List;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.UserBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.User;

public class UserDaoImpl extends SqlDatabaseDAO<User> {

	private static final String READ_ALL_USERS = "SELECT * FROM users";

	private static final String READ_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
	
	private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

	private static final String DELETE_USER = "DELETE FROM users WHERE user_id = ? and "
			+ "login = ? and email = ? and password = ? and user_salt = ? and role = ?";

	private static final String INSERT_USER = "INSERT INTO users (user_id, login, email, password, user_salt, role) VALUES (?, ?, ?, ?, ?, ?)";
	
	public int findUserIdByLogin(String login) throws DAOException {
		long id = (long) findByParameterField(SqlRequest.FIND_ID_BY_LOGIN, login);
		return (int) id;
	}
	public List<User> findAll() throws DAOException {
		return super.findAll(READ_ALL_USERS, new UserBuilder());
	}

	public User findEntityById(Integer id) throws DAOException {
		return takeEntity(findByParameterEntity(READ_USER_BY_ID, new UserBuilder(), id));
	}

	public boolean create(User entity) throws DAOException {
		return super.insertInto(INSERT_USER, entity, new UserBuilder());
	}

	public User findByLogin(String login) throws DAOException {
		
		if(Validator.isNull(login)) {
			return new User();
		}
		return takeEntity(findByParameterEntity(FIND_USER_BY_LOGIN, new UserBuilder(), login));
	}

	

}
