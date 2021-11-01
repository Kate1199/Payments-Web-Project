package by.epam.payment.dao;
import java.util.List;

import by.epam.payment.bean.Entity;
import by.epam.payment.exception.DAOException;



public interface Dao<T extends Entity> {
		
		public List<T> findAll() throws DAOException;
		public boolean insertInto(T entity) throws DAOException;
		public T findEntityById(int id) throws DAOException;
		public T update(T entity) throws DAOException;
		public boolean delete(T entity) throws DAOException;
		public boolean delete(int id) throws DAOException;
}
