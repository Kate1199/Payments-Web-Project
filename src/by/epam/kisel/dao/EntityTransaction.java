package by.epam.kisel.dao;

import java.sql.Connection;
import java.sql.SQLException;

import by.epam.kisel.bean.Entity;
import by.epam.kisel.dao.connection.ConnectionPool;
import by.epam.kisel.exception.DAOException;

public class EntityTransaction<K, T extends Entity> {

	private Connection connection;

	public void initTransaction(Dao<K, T> dao, Dao<K, T>... daos) throws DAOException {
		if (connection == null) {
			try {
				connection = ConnectionPool.getConnection();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		dao.setConnection(connection);
		for (Dao<K, T> daoElement : daos) {
			daoElement.setConnection(connection);
		}
	}

	public void endTransaction() {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			//log
		}
		// TODO: return connection to connection pool
	}

	public void commit() throws DAOException {
		try {
			connection.commit();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public void rollback()  {
		try {
			connection.rollback();
		} catch (SQLException e) {
			//log
		}
	}

	public void init(Dao<K, T> dao) throws DAOException {
		if (connection == null) {
			try {
				connection = ConnectionPool.getConnection();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		dao.setConnection(connection);
	}
	
	public void end() {
		if(connection != null) {
			//return connection
		}
	}
}
