package by.epam.kisel.dao;
import java.util.List;

import by.epam.kisel.bean.Entity;
import by.epam.kisel.exception.DAOException;



public interface Dao<K, T extends Entity> {
		
		public abstract List<T> findAll() throws DAOException;
		public abstract T findEntityById(K id) throws DAOException;
		public abstract boolean delete(T entity) throws DAOException;
		public abstract boolean delete(K id) throws DAOException;
		public abstract boolean create(T entity) throws DAOException;
		public abstract T update(T entity) throws DAOException;
}
