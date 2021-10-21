package by.epam.payments.dao.user;

import java.util.List;

import by.epam.payments.bean.User;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.builders.UserBuilder;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.parameterConstants.SqlRequest;
import by.epam.payments.util.validation.Validator;

public class UserDaoImpl extends SqlDatabaseDAO<User> implements UserDAO {

	private UserBuilder userBuilder = new UserBuilder();
	
	public UserDaoImpl() {
	}
	
	@Override
	public int findUserIdByLogin(String login) throws DAOException {
		long id = (long) findByParameterField(SqlRequest.FIND_ID_BY_LOGIN, login);
		return (int) id;
	}
	
	@Override
	public User findUserByLogin(String login) throws DAOException {
		return findByParameter(SqlRequest.FIND_USER_BY_LOGIN, login);
	}
	
	@Override
	public User findUserByEmail(String email) throws DAOException {
		return findByParameter(SqlRequest.FIND_USER_BY_EMAIL, email);
	}
	
	private User findByParameter(String sqlRequest, String parameter) throws DAOException {
		return takeEntity(findByParameterEntity(sqlRequest, userBuilder, parameter));
	}
	
	@Override
	public List<User> findAll() throws DAOException {
		return super.findAll(SqlRequest.FIND_ALL_USERS, userBuilder);
	}
	
	@Override
	public User findEntityById(int id) throws DAOException {
		if(id <= 0) {
			return new User();
		}
		return takeEntity(findByParameterEntity(SqlRequest.FIND_USER_BY_ID, userBuilder, id));
	}
	
	@Override
	public boolean insertInto(User user) throws DAOException {
		if(Validator.isNull(user)) {
			return false;
		}
		return super.addNewData(SqlRequest.ADD_USER, user, userBuilder);
	}

	@Override
	public User update(User user) throws DAOException {
		if(Validator.isNull(user)) {
			return new User();
		}
		if(!super.update(SqlRequest.UPDATE_USER, user.getLogin(), user.getEmail(), user.getPassword(),
				user.getSalt(), user.getRole(), user.getId())) {
			user = new User();
		}
		return user;
	}

	@Override
	public boolean delete(User user) throws DAOException {
		if(Validator.isNull(user)) {
			return false;
		}
		return addNewData(SqlRequest.HIDE_USER, user, userBuilder);
	}

	@Override
	public boolean delete(int id) throws DAOException {
		if(id <= 0) {
			return false;
		}
		return update(SqlRequest.HIDE_USER_BY_ID, id);
	}

	

}
