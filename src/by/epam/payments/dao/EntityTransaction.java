package by.epam.payments.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Entity;
import by.epam.payments.exception.DAOException;

public class EntityTransaction<T extends Entity> extends SqlDatabaseDAO<T> {
	
	private static Logger logger = LogManager.getLogger();

	private Connection connection;

	public void initTransaction() throws DAOException {
		
		connection = makeConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		setConnection(connection);
	}

	public void endTransaction() throws DAOException {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		closeConnection();
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
			logger.log(Level.ERROR, e.getMessage());
		}
	}
}
