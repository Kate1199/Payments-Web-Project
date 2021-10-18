package by.epam.payments.dao.payment;
import java.util.List;

import by.epam.payments.bean.Payment;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.builders.PaymentBuilder;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.parameterConstants.SqlRequest;
import by.epam.payments.util.validation.Validator;

public class PaymentDaoImpl extends SqlDatabaseDAO<Payment> implements PaymentDAO {
	
	private PaymentBuilder paymentBuilder = new PaymentBuilder();
	
	@Override
	public Payment findByName(String name) throws DAOException {
		if(Validator.isNull(name)) {
			return new Payment();
		}
		return takeEntity(findByParameterEntity(SqlRequest.FIND_PAYMENT_BY_NAME, paymentBuilder, name));
	}
	
	@Override
	public List<Payment> findAllWithLimits(int previousLimit, int limit) throws DAOException {
		return findByParameterEntity(SqlRequest.FIND_ALL_PAYMENTS_WHITH_LIMIT, paymentBuilder, 
				previousLimit, limit);
	}

	@Override
	public List<Payment> findAll() throws DAOException {
		return super.findAll(SqlRequest.FIND_ALL_PAYMENTS, paymentBuilder);
	}

	@Override
	public Payment findEntityById(int id) throws DAOException {
		if(id <= 0) {
			return new Payment();
		}
		List<Payment> result = findByParameterEntity(SqlRequest.FIND_PAYMENT_BY_ID, paymentBuilder, id);
		return takeEntity(result);
	}
	
	@Override
	public boolean insertInto(Payment payment) throws DAOException {
		if(Validator.isNull(payment)) {
			return false;
		}
		return super.addNewData(SqlRequest.ADD_PAYMENT, payment, paymentBuilder);
	}
	
	@Override
	public Payment update(Payment payment) throws DAOException {
		if(Validator.isNull(payment)) {
			return new Payment();
		}
		if(!super.update(SqlRequest.UPDATE_PAYMENT, payment.getName(), payment.getImage(), 
				payment.getReciever(), payment.getPaymentDetails(), payment.getDescription(), 
				payment.getFixedAmount(), payment.getProcentFee(), payment.getAccountId(), payment.getId())) {
			payment = new Payment();
		}
		return payment;
	}


	@Override
	public boolean delete(Payment payment) throws DAOException {
		if(Validator.isNull(payment)) {
			return false;
		}
		return super.addNewData(SqlRequest.HIDE_PAYMENT, payment, paymentBuilder);
	}

	@Override
	public boolean delete(int id) throws DAOException {
		if(id <= 0) {
			return false;
		}
		return super.update(SqlRequest.HIDE_PAYMENT_BY_ID, id);
	}

	
	
	
	
}
