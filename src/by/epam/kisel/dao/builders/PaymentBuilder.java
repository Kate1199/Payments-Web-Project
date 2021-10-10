package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import by.epam.kisel.bean.Payment;
import by.epam.kisel.bean.User;
import by.epam.kisel.dao.dbColoumns.PaymentColoumns;
import by.epam.kisel.dao.dbColoumns.UserColoumns;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.BlobByteArrayMaker;
import by.epam.kisel.util.validation.Validator;

public class PaymentBuilder implements EntityBuilder<Payment> {
	
	private List<Payment> payments = new ArrayList<Payment>();
	
	public List<Payment> getPayments() {
		return payments;
	}

	@Override
	public Payment makeEntity(ResultSet resultSet) throws DAOException {
		if (Validator.isNull(resultSet)) {
			return new Payment();
		}
		
		Payment payment = new Payment();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(PaymentColoumns.ID);
				String name = resultSet.getString(PaymentColoumns.NAME);
				String reciever = resultSet.getString(PaymentColoumns.RECIEVER);
				String details = resultSet.getString(PaymentColoumns.DETAILS);
				String description = resultSet.getString(PaymentColoumns.DESCRIPTION);
				int fixedAmount = resultSet.getInt(PaymentColoumns.FIXED_AMOUNT);
				int procentFee = resultSet.getInt(PaymentColoumns.PROCENT_FEE);
				payment = new Payment(id, name, reciever, details, description, fixedAmount, procentFee);
				payments.add(payment);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return payment;
	}

	@Override
	public boolean putTo(List<Payment> payments, Payment payment) {
		return payments.add(payment);
	}

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Payment payment) throws DAOException {
		boolean transmit;

		try {
			preparedStatement.setInt(PaymentColoumns.ID, payment.getId());
			preparedStatement.setString(PaymentColoumns.NAME, payment.getName());
			preparedStatement.setString(PaymentColoumns.RECIEVER, payment.getReciever());
			preparedStatement.setString(PaymentColoumns.DETAILS, payment.getPaymentDetails());
			preparedStatement.setString(PaymentColoumns.DESCRIPTION, payment.getDescription());
			preparedStatement.setInt(PaymentColoumns.FIXED_AMOUNT, payment.getFixedAmount());
			preparedStatement.setInt(PaymentColoumns.PROCENT_FEE, payment.getProcentFee());
			
			transmit = true;
		} catch (SQLException e) {
			transmit = false;
			throw new DAOException(e.getMessage());
		}
		return transmit;
	}
	
}
