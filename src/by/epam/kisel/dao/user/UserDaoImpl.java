package by.epam.kisel.dao.user;

import java.util.List;

import by.epam.kisel.bean.User;
import by.epam.kisel.dao.DatabaseDAO;
import by.epam.kisel.dao.builders.UserBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.validation.Validator;

public class UserDaoImpl extends DatabaseDAO<User> implements UserDAO {
	
	private static final int LOGIN_POSITION = 1;

	private static final String READ_ALL_USERS = "SELECT * FROM users";

	private static final String READ_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
	
	private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

	private static final String DELETE_USER = "DELETE FROM users WHERE user_id = ? and "
			+ "login = ? and email = ? and password = ? and user_salt = ? and role = ?";

	private static final String INSERT_USER = "INSERT INTO users (user_id, login, email, password, user_salt, role) VALUES (?, ?, ?, ?, ?, ?)";
	
	@Override
	public List<User> findAll() throws DAOException {
		return super.findAll(READ_ALL_USERS, new UserBuilder());
	}

	@Override
	public User findEntityById(String id) throws DAOException {
		return super.findEntityById(READ_USER_BY_ID, id, new UserBuilder());
	}

	@Override
	public boolean delete(User entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(User entity) throws DAOException {
		return super.insertInto(INSERT_USER, entity, new UserBuilder());
	}

	@Override
	public User update(User entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByLogin(String login) throws DAOException {
		
		if(Validator.isNull(login)) {
			return new User();
		}
		return super.findByParameter(FIND_USER_BY_LOGIN, login, new UserBuilder());
	}

	

}
