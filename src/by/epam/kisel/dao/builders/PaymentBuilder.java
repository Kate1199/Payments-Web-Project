package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.dbColoumns.PaymentColoumns;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.BlobByteArrayMaker;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Payment;

public class PaymentBuilder implements EntityBuilder<Payment> {
	
	private static Logger logger = LogManager.getLogger();
	
	public PaymentBuilder() {
	}
	
	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Payment payment) throws DAOException {
		boolean transmit;

		try {
			preparedStatement.setInt(PaymentColoumns.ID, payment.getId());
			preparedStatement.setString(PaymentColoumns.NAME, payment.getName());
			preparedStatement.setBlob(PaymentColoumns.IMAGE, new SerialBlob(payment.getImage()));
			preparedStatement.setString(PaymentColoumns.RECIEVER, payment.getReciever());
			preparedStatement.setString(PaymentColoumns.DETAILS, payment.getPaymentDetails());
			preparedStatement.setString(PaymentColoumns.DESCRIPTION, payment.getDescription());
			preparedStatement.setInt(PaymentColoumns.FIXED_AMOUNT, payment.getFixedAmount());
			preparedStatement.setInt(PaymentColoumns.PROCENT_FEE, payment.getProcentFee());
			preparedStatement.setInt(PaymentColoumns.PAYMENT_ACCOUNT_ID, payment.getAccountId());
			transmit = true;
		} catch (SQLException e) {
			transmit = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return transmit;
	}
	
	@Override
	public List<Payment> getListOfEntities(ResultSet resultSet) throws DAOException {
		if (Validator.isNull(resultSet)) {
			return new ArrayList<Payment>();
		}
		
		List<Payment> payments = new ArrayList<Payment>();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(PaymentColoumns.ID);
				String name = resultSet.getString(PaymentColoumns.NAME);
				byte[] image = BlobByteArrayMaker.makeByteArray(resultSet, PaymentColoumns.IMAGE);
				String reciever = resultSet.getString(PaymentColoumns.RECIEVER);
				String details = resultSet.getString(PaymentColoumns.DETAILS);
				String description = resultSet.getString(PaymentColoumns.DESCRIPTION);
				int fixedAmount = resultSet.getInt(PaymentColoumns.FIXED_AMOUNT);
				int procentFee = resultSet.getInt(PaymentColoumns.PROCENT_FEE);
				int accountId = resultSet.getInt(PaymentColoumns.PAYMENT_ACCOUNT_ID);
				Payment payment = new Payment(id, name, image, reciever, details, description, fixedAmount, procentFee, accountId);
				payments.add(payment);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return payments;
	}


	
	
}
