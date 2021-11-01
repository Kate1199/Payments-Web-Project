package by.epam.payment.dao.card;

import java.util.List;

import by.epam.payment.bean.Card;
import by.epam.payment.dao.Dao;
import by.epam.payment.exception.DAOException;

public interface CardDao extends Dao<Card> {
	
	public List<Card> findCardsByAccountId(int accountId) throws DAOException;
}
