package by.epam.payments.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Payment;
import by.epam.payments.dao.dbColoumns.ColomnName;
import by.epam.payments.dao.dbColoumns.ColoumnNumberInPreparedStatement;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.BlobByteArrayMaker;
import by.epam.payments.util.validation.Validator;

public class PaymentBuilder implements EntityBuilder<Payment> {
	
	private static Logger logger = LogManager.getLogger();
	
	public PaymentBuilder() {
	}
	
	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Payment payment) throws DAOException {
		boolean transmit;

		try {
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_1, payment.getId());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_2, payment.getName());
			preparedStatement.setBlob(ColoumnNumberInPreparedStatement.COLOUMN_3, new SerialBlob(payment.getImage()));
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_4, payment.getReciever());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_5, payment.getPaymentDetails());
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_6, payment.getDescription());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_7, payment.getFixedAmount());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_8, payment.getProcentFee());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_9, payment.getAccountId());
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
				int id = resultSet.getInt(ColomnName.PAYMENT_ID);
				String name = resultSet.getString(ColomnName.PAYMENT_NAME);
				byte[] image = BlobByteArrayMaker.makeByteArray(resultSet, ColomnName.IMAGE);
				String reciever = resultSet.getString(ColomnName.RECIEVER);
				String details = resultSet.getString(ColomnName.PAYMENT_DETAILS);
				String description = resultSet.getString(ColomnName.DESCRIPTION);
				int fixedAmount = resultSet.getInt(ColomnName.FIXED_AMOUNT);
				int procentFee = resultSet.getInt(ColomnName.PROCENT_FEE);
				int accountId = resultSet.getInt(ColomnName.ACCOUNTS_ACCOUNT_ID);
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
