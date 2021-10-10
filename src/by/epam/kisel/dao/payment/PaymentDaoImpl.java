package by.epam.kisel.dao.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.kisel.bean.Payment;
import by.epam.kisel.dao.DatabaseDAO;
import by.epam.kisel.dao.builders.PaymentBuilder;
import by.epam.kisel.dao.connection.ConnectionPool;
import by.epam.kisel.exception.ConnectionPoolException;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.validation.Validator;

public class PaymentDaoImpl extends DatabaseDAO<Payment> implements PaymentDAO {
	
	private static final String FIND_ALL = "SELECT * FROM payments";
	private static final String FIND_ALL_WHITH_LIMIT = "SELECT * FROM payments ORDER BY payment_id LIMIT ?,?";
	private static final String FIND_BY_NAME = "SELECT * FROM payments WHERE payment_name = ?";
	
	private static final int NAME_POSITION_IN_FIND_BY_NAME_REQUEST = 1;
	private static final int SECOND_POSITION = 2;
	
	private static int previousLimit = 0;
	private static int limit = 3;
	

	@Override
	public List<Payment> findAll() throws DAOException {
		return super.findAll(FIND_ALL, new PaymentBuilder());
	}

	@Override
	public Payment findEntityById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Payment entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Payment entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Payment update(Payment entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment findByName(String name) throws DAOException {
		if(Validator.isNull(name)) {
			return new Payment();
		}
		return super.findByParameter(FIND_BY_NAME, name, new PaymentBuilder());
	}
	
	public List<Payment> findAllWithLimits() throws DAOException {
		List<Payment> payments = new ArrayList<Payment>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ConnectionPool connectionPool;
		try {
			connectionPool = ConnectionPool.getInstanse();
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_WHITH_LIMIT);
			preparedStatement.setInt(NAME_POSITION_IN_FIND_BY_NAME_REQUEST, previousLimit);
			preparedStatement.setInt(SECOND_POSITION, limit);
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		}
		PaymentBuilder paymentBuilder = new PaymentBuilder();
		paymentBuilder.makeEntity(resultSet);
		payments = paymentBuilder.getPayments();
		connectionPool.releaseConnection(connection);
		previousLimit += limit;
		return payments;
	}
}
