package by.epam.payments.dao.user;

import java.util.List;

import by.epam.payments.bean.User;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface UserDAO extends Dao<User> {

	public List<User> findPotentialAdmins() throws DAOException;
	public boolean updatePotentialAdmin(String potentialAdmin, int userId) throws DAOException;
	public int findUserIdByLogin(String login) throws DAOException;
	public User findUserByLogin(String login) throws DAOException;
	public User findUserByEmail(String email) throws DAOException;
}
