package by.epam.kisel.dao.card;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.CardBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.parameterConstants.LogMessage;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Card;

public class CardDaoImpl extends SqlDatabaseDAO<Card> implements CardDao {
	
	private static Logger logger = LogManager.getLogger();
	
	private CardBuilder cardBuilder = new CardBuilder();

	public CardDaoImpl() {
	}
	
	@Override
	public List<Card> findCardsByAccountId(int accountId) throws DAOException {
		if(accountId <= 0) {
			return new ArrayList<Card>();
		}
		 return findByParameterEntity(SqlRequest.FIND_CARD_BY_ACCOUNT_ID, cardBuilder, accountId);
	}

	@Override
	public List<Card> findAll() throws DAOException {
		return super.findAll(SqlRequest.FIND_ALL_CARDS, cardBuilder);
	}

	@Override
	public boolean insertInto(Card card) throws DAOException {
		if(Validator.isNull(card)) {
			return false;
		}
		return super.insertInto(SqlRequest.ADD_CARD, card, cardBuilder);
	}

	@Override
	public Card findEntityById(int id) throws DAOException {
		if(id <= 0) {
			return new Card();
		}
		List<Card> result = super.findByParameterEntity(SqlRequest.FIND_CARD_BY_ID, cardBuilder, id);
		return takeEntity(result);
	}

	@Override
	public Card update(Card card) throws DAOException {
		logger.log(Level.WARN, LogMessage.CARD_UPDATE + card.getId());
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Card card) throws DAOException {
		if(Validator.isNull(card)) {
			return false;
		}
		return update(SqlRequest.HIDE_CARD, card.getNumber(), card.getStartDigits(), card.getEndDigits(),
				card.getSalt(), card.getValidityPeriod(), card.getAccountId());
		
	}

	@Override
	public boolean delete(int id) throws DAOException {
		if(id <= 0) {
			return false;
		}
		return update(SqlRequest.HIDE_CARD_BY_ID, id);
	}
}
