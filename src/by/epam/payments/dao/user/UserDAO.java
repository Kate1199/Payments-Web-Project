package by.epam.payments.dao.user;

import by.epam.payments.bean.User;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface UserDAO extends Dao<User> {
	public User findByLogin(String login) throws DAOException;
	public int findUserIdByLogin(String login) throws DAOException;
}
