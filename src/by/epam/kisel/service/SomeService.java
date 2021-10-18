package by.epam.kisel.service;

import by.epam.kisel.dao.EntityTransaction;
import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.payments.bean.User;

public class SomeService {

	public void doService(int parameter) throws ServiceException {
		UserDaoImpl userDao = new UserDaoImpl();
		EntityTransaction<Integer, User> transaction = new EntityTransaction<Integer, User>();
		try {
			userDao.create(new User());
			userDao.update(new User());
			userDao.delete(new User());
			transaction.commit();
		} catch (DAOException e) {
			 transaction.rollback();
			 throw new ServiceException(e);
		} finally {
			// 4. transaction closing
			 transaction.endTransaction();
		}
		
	}
}
