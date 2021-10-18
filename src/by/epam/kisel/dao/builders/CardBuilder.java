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

import by.epam.kisel.dao.dbColoumns.DatabaseColoumn;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.BlobByteArrayMaker;
import by.epam.payments.bean.Card;

public class CardBuilder implements EntityBuilder<Card> {
	
	public static Logger logger = LogManager.getLogger();

	@Override
	public boolean transmitEntity(PreparedStatement preparedStatement, Card card) throws DAOException {
		boolean transmit = true;
		try {
			preparedStatement.setInt(DatabaseColoumn.CARD_ID, card.getId());
			preparedStatement.setBlob(DatabaseColoumn.CARD_NUMBER, new SerialBlob(card.getNumber()));
			preparedStatement.setInt(DatabaseColoumn.CARD_START_DIGITS, card.getStartDigits());
			preparedStatement.setInt(DatabaseColoumn.CARD_END_DIGITS, card.getEndDigits());
			preparedStatement.setBlob(DatabaseColoumn.CARD_SALT, new SerialBlob(card.getSalt()));
			preparedStatement.setString(DatabaseColoumn.VALIDITY_PERIOD, card.getValidityPeriod());
			preparedStatement.setInt(DatabaseColoumn.CARD_ACCOUNT_ID, card.getAccountId());
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
				int id = resultSet.getInt(DatabaseColoumn.CARD_ID);
				byte[] number = BlobByteArrayMaker.makeByteArray(resultSet, DatabaseColoumn.CARD_NUMBER);
				int startDigits = resultSet.getInt(DatabaseColoumn.CARD_START_DIGITS);
				int endDigits = resultSet.getInt(DatabaseColoumn.CARD_END_DIGITS);
				byte[] salt = BlobByteArrayMaker.makeByteArray(resultSet, DatabaseColoumn.CARD_SALT);
				String validityPeriod = resultSet.getString(DatabaseColoumn.VALIDITY_PERIOD);
				int accountId = resultSet.getInt(DatabaseColoumn.CARD_ACCOUNT_ID);
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
