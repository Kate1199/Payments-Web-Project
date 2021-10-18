package by.epam.kisel.dao.card;

import java.util.List;

import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Card;

public interface CardDao extends Dao<Card> {
	
	public List<Card> findCardsByAccountId(int accountId) throws DAOException;
}
