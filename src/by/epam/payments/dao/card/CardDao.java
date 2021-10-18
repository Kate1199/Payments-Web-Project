package by.epam.payments.dao.card;

import java.util.List;

import by.epam.payments.bean.Card;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface CardDao extends Dao<Card> {
	
	public List<Card> findCardsByAccountId(int accountId) throws DAOException;
}
