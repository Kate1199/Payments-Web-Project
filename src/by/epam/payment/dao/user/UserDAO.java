package by.epam.payment.dao.user;

import java.util.List;

import by.epam.payment.bean.User;
import by.epam.payment.dao.Dao;
import by.epam.payment.exception.DAOException;

public interface UserDAO extends Dao<User> {

	public List<User> findPotentialAdmins() throws DAOException;
	public boolean updatePotentialAdmin(String potentialAdmin, int userId) throws DAOException;
	public int findUserIdByLogin(String login) throws DAOException;
	public User findUserByLogin(String login) throws DAOException;
	public User findUserByEmail(String email) throws DAOException;
}
