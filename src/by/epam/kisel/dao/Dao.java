package by.epam.kisel.dao;
import java.util.List;

import by.epam.kisel.bean.Entity;
import by.epam.kisel.exception.DAOException;



public interface Dao<K, T extends Entity> {
		
		public List<T> findAll() throws DAOException;
		public T findEntityById(K id) throws DAOException;
		public boolean delete(T entity) throws DAOException;
		public boolean delete(K id) throws DAOException;
		public boolean create(T entity) throws DAOException;
		public T update(T entity) throws DAOException;
}
