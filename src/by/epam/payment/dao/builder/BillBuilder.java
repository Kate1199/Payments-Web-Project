package by.epam.payment.dao.builder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payment.bean.Bill;
import by.epam.payment.dao.dbColoumns.ColomnName;
import by.epam.payment.dao.dbColoumns.ColoumnNumberInPreparedStatement;
import by.epam.payment.exception.DAOException;
import by.epam.payment.util.DateConverter;

public class BillBuilder implements EntityBuilder<Bill> {
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Bill bill) throws DAOException {
		
		try {
			preparedStatement.setTimestamp(ColoumnNumberInPreparedStatement.COLOUMN_1, DateConverter.makeTimestamp(bill.getDateTime()));
			preparedStatement.setLong(ColoumnNumberInPreparedStatement.COLOUMN_2, bill.getAmount());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_3, bill.getFee());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_4, bill.getCardId());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_5, bill.getPaymentId());
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		return false;
	}

	@Override
	public List<Bill> getListOfEntities(ResultSet resultSet) throws DAOException {
		
		List<Bill> bills = new ArrayList<Bill>();
		
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(ColomnName.ID_BILLS);
				Calendar dateTime = DateConverter.makeCalendar(resultSet.getTimestamp(ColomnName.DATE_TIME));
				long amount = resultSet.getLong(ColomnName.AMOUNT);
				int fee = resultSet.getInt(ColomnName.FEE);
				int cardId = resultSet.getInt(ColomnName.CARDS_CARD_ID);
				int paymentId = resultSet.getInt(ColomnName.PAYMENTS_PAYMENT_ID);
				Bill bill = new Bill(id, dateTime, amount, fee, cardId, paymentId);
				bills.add(bill);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		return bills;
	}
	
}
