package by.epam.kisel.dao.user;

import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.User;

public interface UserDAO extends Dao<User> {
	public User findByLogin(String login) throws DAOException;
	public int findUserIdByLogin(String login) throws DAOException;
}
