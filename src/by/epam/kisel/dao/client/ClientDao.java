package by.epam.kisel.dao.client;

import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Client;

public interface ClientDao extends Dao<Integer, Client> {
	
	public Client findClientByUserId(Integer userId) throws DAOException;
}