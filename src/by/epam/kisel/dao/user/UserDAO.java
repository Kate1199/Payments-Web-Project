package by.epam.kisel.dao.user;

import by.epam.kisel.bean.User;
import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;

public interface UserDAO extends Dao<String, User> {
	public User findByLogin(String login) throws DAOException;
}
