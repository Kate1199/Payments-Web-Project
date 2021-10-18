package by.epam.kisel.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.exception.ConnectionPoolException;
import by.epam.kisel.util.parameterConstants.LogMessage;
import by.epam.kisel.util.validation.Validator;

public class ConnectionPool {
	
	private static final String DB_URL = "db.url";
	private static final String DB_USER = "db.user";
	private static final String DB_PASSWORD = "db.password";
	private static final String DB_POOL_SIZE = "db.poolsize";
	
	private static final int LAST_INDEX_CORRECTOR = 1;

	private static String url;
	private static String user;
	private static String password;
	private static int poolsize;
	private static List<Connection> connectionPool;
	private static List<Connection> usedConnections;
	
	private static ConnectionPool instanse;
	
	private static Logger logger = LogManager.getLogger();

	private ConnectionPool() {
	}
	
	public static synchronized ConnectionPool getInstanse() throws ConnectionPoolException {
		if(Validator.isNull(instanse)) {
			instanse = create();
		}
		
		return instanse;
	}

	public static ConnectionPool create() throws ConnectionPoolException {
		
		DBResourseManager dbResourseManager = DBResourseManager.getInstance();
		url = dbResourseManager.getValue(DB_URL);
		user = dbResourseManager.getValue(DB_USER);
		password = dbResourseManager.getValue(DB_PASSWORD);
		poolsize = Integer.parseInt(dbResourseManager.getValue(DB_POOL_SIZE));
		
		connectionPool = new ArrayList<>(poolsize);
		usedConnections = new ArrayList<>(poolsize);

		for (int i = 0; i < poolsize; i++) {
			Connection connection;
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				logger.log(Level.INFO, e.getMessage());
				throw new ConnectionPoolException(e.getMessage());
			}
			connectionPool.add(connection);
		}
		logger.log(Level.INFO, LogMessage.СONNECTION_POOL_CREATED);
		return new ConnectionPool();
	}

	public Connection getConnection() {
		Connection connection = connectionPool.remove(connectionPool.size() - LAST_INDEX_CORRECTOR);
		usedConnections.add(connection);
		logger.log(Level.INFO, LogMessage.CONNECTION_RECIEVED);
		return connection;
	}

	public boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		logger.log(Level.INFO, LogMessage.CONNECTION_CLOSED);
		return usedConnections.remove(connection);
	}

	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

}
