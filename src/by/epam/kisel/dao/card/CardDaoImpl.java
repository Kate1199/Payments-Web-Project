package by.epam.kisel.dao.card;

import java.util.List;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.CardBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.payments.bean.Card;

public class CardDaoImpl extends SqlDatabaseDAO<Card> {

	public CardDaoImpl() {
		
	}
	
	public List<Card> findCardsByAccountId(int accountId) throws DAOException {
		 return findByParameterEntity(SqlRequest.FIND_CARD_BY_ACCOUNT_ID, new CardBuilder(), accountId);
	}
}
