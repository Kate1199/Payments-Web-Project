package by.epam.kisel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.builders.EntityBuilder;
import by.epam.kisel.dao.connection.ConnectionPool;
import by.epam.kisel.exception.ConnectionPoolException;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Entity;

public class SqlDatabaseDAO<T extends Entity> {

	/*
	 * entity index in list of entites from resultSet
	 */
	private static final int ENTITY_INDEX = 0;

	/*
	 * field index in resultset
	 */
	private static final int FIELD_INDEX = 1;

	/*
	 * correction index to make correct coloumn in preparedStatement
	 */
	private static final int CORRECTION_INDEX = 1;

	private static Logger logger = LogManager.getLogger();

	private static ConnectionPool connectionPool;
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	protected SqlDatabaseDAO() {
	}

	public SqlDatabaseDAO(Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public List<T> findAll(String sqlRequest, EntityBuilder<T> entityBuilder) throws DAOException {
		makeConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlRequest);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException();
		}
		List<T> entities = entityBuilder.getListOfEntities(resultSet);
		closeConnection();
		return entities;
	}

	protected Connection makeConnection() throws DAOException {

		try {
			connectionPool = ConnectionPool.getInstanse();
			connection = connectionPool.getConnection();
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return connection;
	}

	protected boolean closeConnection() throws DAOException {
		boolean close = true;
		try {
			if (!Validator.isNull(resultSet)) {
				resultSet.close();
			}
			if (!Validator.isNull(statement)) {
				statement.close();
			}
			if (!Validator.isNull(preparedStatement)) {
				preparedStatement.close();
			}
			connectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			close = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return close;
	}

	public List<T> findByParameterEntity(String sqlRequest, EntityBuilder<T> entityBuilder, Object... parameters)
			throws DAOException {
		if(Validator.isNull(entityBuilder) || Validator.isNull(sqlRequest) || Validator.isNull(parameters)) {
			return new ArrayList<T>();
		}
		
		findByParameter(sqlRequest, parameters);
		List<T> entities = entityBuilder.getListOfEntities(resultSet);
		closeConnection();
		return entities;
	}

	public Object findByParameterField(String sqlRequest, Object...parameters) throws DAOException {
		findByParameter(sqlRequest, parameters);
		Object field = takeFieldFromResultSet();
		closeConnection();
		return field;
	}

	private boolean findByParameter(String sqlRequest, Object...parameters) throws DAOException {
		boolean find = true;
		makeConnection();
		makePreparedStatementForSomeParameters(sqlRequest, parameters);
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			find = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		return find;
	}
	
	private boolean makePreparedStatementForSomeParameters(String sqlRequest, Object...parameters) throws DAOException {
		boolean make = true;
		try {
			preparedStatement = connection.prepareStatement(sqlRequest);

			for (int i = 0; i < parameters.length; i++) {
				preparedStatement.setObject(i + CORRECTION_INDEX, parameters[i]);
			}
		} catch (SQLException e) {
			make = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return make;
	}

	private Object takeFieldFromResultSet() throws DAOException {
		Object field = new Object();
		try {
			while (resultSet.next()) {
				field = resultSet.getObject(FIELD_INDEX);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return field;
	}

	public boolean insertInto(String sqlStatement, T entity, EntityBuilder<T> entityBuilder) throws DAOException {
		boolean insert = true;
		
		if(Validator.isNull(entity) || Validator.isNull(sqlStatement) || Validator.isNull(entityBuilder)) {
			return false;
		}
		makeConnection();
		try {
			preparedStatement = connection.prepareStatement(sqlStatement);
			insert &= entityBuilder.transmitEntity(preparedStatement, entity);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			insert = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		closeConnection();

		return insert;
	}

	/**
	 * takes entity from result list of entities, in such cases if there was only
	 * one suitable entry in database
	 * 
	 * @param entities list of entities that were found in database
	 * @return returns {@code null} if the list is empty, else returns entity
	 */
	public T takeEntity(List<T> entities) {
		if (entities.isEmpty()) {
			return null;
		}
		return entities.get(ENTITY_INDEX);
	}
	
	public boolean updateCore(String sqlRequest, Object...parameters) throws DAOException {
		boolean update = true;
		makePreparedStatementForSomeParameters(sqlRequest, parameters);
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return update;
	}
	
	public boolean makeEntryInvisible(String sqlRequest, Object...parameters) throws DAOException {
		boolean invisible = true;
		makeConnection();
		invisible &= updateCore(sqlRequest, parameters) && closeConnection();
		return invisible;
	}
}
