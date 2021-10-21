package by.epam.payments.dao.user;

import by.epam.payments.bean.User;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface UserDAO extends Dao<User> {

	public int findUserIdByLogin(String login) throws DAOException;
	public User findUserByLogin(String login) throws DAOException;
	public User findUserByEmail(String email) throws DAOException;
}
