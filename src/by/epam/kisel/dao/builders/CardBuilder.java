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

import by.epam.kisel.dao.dbColoumns.ColomnName;
import by.epam.kisel.dao.dbColoumns.ColoumnNumberInPreparedStatement;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.BlobByteArrayMaker;
import by.epam.payments.bean.Card;

public class CardBuilder implements EntityBuilder<Card> {
	
	public static Logger logger = LogManager.getLogger();
	
	public CardBuilder() {
	}

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Card card) throws DAOException {
		boolean transmit = true;
		try {
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_1, card.getId());
			preparedStatement.setBlob(ColoumnNumberInPreparedStatement.COLOUMN_2, new SerialBlob(card.getNumber()));
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_3, card.getStartDigits());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_4, card.getEndDigits());
			preparedStatement.setBlob(ColoumnNumberInPreparedStatement.COLOUMN_5, new SerialBlob(card.getSalt()));
			preparedStatement.setString(ColoumnNumberInPreparedStatement.COLOUMN_6, card.getValidityPeriod());
			preparedStatement.setInt(ColoumnNumberInPreparedStatement.COLOUMN_7, card.getAccountId());
		} catch (SQLException e) {
			transmit = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		return transmit;
	}

	@Override
	public List<Card> getListOfEntities(ResultSet resultSet) throws DAOException {
		List<Card> cards = new ArrayList<Card>();
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(ColomnName.CARD_ID);
				byte[] number = BlobByteArrayMaker.makeByteArray(resultSet, ColomnName.CARD_NUMBER);
				int startDigits = resultSet.getInt(ColomnName.CARD_START_DIGITS);
				int endDigits = resultSet.getInt(ColomnName.CARD_END_DIGITS);
				byte[] salt = BlobByteArrayMaker.makeByteArray(resultSet,ColomnName.CARD_SALT);
				String validityPeriod = resultSet.getString(ColomnName.VALIDITY_PERIOD);
				int accountId = resultSet.getInt(ColomnName.ACCOUNTS_ACCOUNT_ID);
				Card card = new Card(id, number, startDigits, endDigits, salt, validityPeriod, accountId);
				cards.add(card);
			}
			
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		return cards;
	}

	
	
}
