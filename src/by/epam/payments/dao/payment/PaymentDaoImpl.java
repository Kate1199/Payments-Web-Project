package by.epam.payments.dao.payment;
import java.util.List;

import by.epam.payments.bean.Payment;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.builders.PaymentBuilder;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.validation.Validator;

public class PaymentDaoImpl extends SqlDatabaseDAO<Payment> implements PaymentDAO {
	
	private static final String FIND_ALL = "SELECT * FROM payments";
	private static final String FIND_ALL_WHITH_LIMIT = "SELECT * FROM payments ORDER BY payment_id LIMIT ?,?";
	private static final String FIND_BY_NAME = "SELECT * FROM payments WHERE payment_name = ?";

	@Override
	public List<Payment> findAll() throws DAOException {
		return super.findAll(FIND_ALL, new PaymentBuilder());
	}

	@Override
	public Payment findEntityById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Payment entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
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
		return takeEntity(findByParameterEntity(FIND_BY_NAME, new PaymentBuilder(), name));
	}
	
	public List<Payment> findAllWithLimits(int previousLimit, int limit) throws DAOException {
		return findByParameterEntity(FIND_ALL_WHITH_LIMIT, new PaymentBuilder(), previousLimit, limit);
	}

	@Override
	public List<Payment> findSomeFist(int number) {
		
		return null;
	}

	@Override
	public boolean insertInto(Payment entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
