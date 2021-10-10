package by.epam.kisel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.kisel.bean.Entity;
import by.epam.kisel.bean.User;
import by.epam.kisel.dao.builders.EntityBuilder;
import by.epam.kisel.dao.builders.UserBuilder;
import by.epam.kisel.dao.connection.ConnectionPool;
import by.epam.kisel.exception.ConnectionPoolException;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.validation.Validator;

public class DatabaseDAO<T extends Entity> {
	
	private static final int KEY_INDEX = 1;
	private static final int PARAMETER_POSITION = 1;
	
	public List<T> findAll(String sqlRequest, EntityBuilder<T> entityBuilder) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<T> entities = new ArrayList<T>();
		ConnectionPool myConnectionPool;
		try {
			myConnectionPool = ConnectionPool.getInstanse();
			connection = myConnectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlRequest);
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		}
		entities = takeEntityList(sqlRequest, resultSet, entityBuilder);
		myConnectionPool.releaseConnection(connection);
		return entities;
	}
	
	//TODO: make private
	public List<T> takeEntityList(String sql, ResultSet resultSet, EntityBuilder<T> entityBuilder) throws DAOException {
		List<T> list = new ArrayList<T>();
		T entity;
		try {
			while(resultSet.next()) {
				entity = entityBuilder.makeEntity(resultSet);
				entityBuilder.putTo(list, entity);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return list;
	}

	public T findEntityById(String sqlRequest, String id, EntityBuilder<T> entityBuilder) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConnectionPool connectionPool;
		try {
			connectionPool = ConnectionPool.getInstanse();
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sqlRequest);
			resultSet = preparedStatement.executeQuery();
			preparedStatement.setString(KEY_INDEX, id);
			
		} catch (SQLException e) {
			throw new DAOException("Error setting key in preparedStatement");
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		}
		T entity = entityBuilder.makeEntity(resultSet);
		connectionPool.releaseConnection(connection);
		return entity;
	}
	
	public boolean insertInto(String sqlStatement, T entity, EntityBuilder<T> entityBuilder) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean insert = true;
		ConnectionPool myConnectionPool;
		try {
			myConnectionPool = ConnectionPool.getInstanse();
			connection = myConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sqlStatement);
			insert &= entityBuilder.transmitEntity(preparedStatement, entity);
		} catch (SQLException e) {
			insert = false;
			throw new DAOException(e.getMessage());
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		}
		myConnectionPool.releaseConnection(connection);
		
		return insert;
	}
	
public T findByParameter(String sqlStatement, String parameter, EntityBuilder<T> entityBuilder) throws DAOException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConnectionPool myConnectionPool;
		try {
			myConnectionPool = ConnectionPool.getInstanse();
			connection = myConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(PARAMETER_POSITION, parameter);
			resultSet = preparedStatement.executeQuery();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
		T entity = entityBuilder.makeEntity(resultSet);

		return entity;
	}
	
	public void close(Statement statement) throws DAOException {
		try {
			if(statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			//log
			throw new DAOException(e.getMessage());
		}
	}
	
	public void close(Connection connection) throws DAOException {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			//log
			throw new DAOException(e.getMessage());
		}
	}

}
