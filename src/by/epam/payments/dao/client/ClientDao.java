package by.epam.payments.dao.client;

import by.epam.payments.bean.Client;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface ClientDao extends Dao<Client> {
	
	public Client findClientByUserId(int userId) throws DAOException;
	public int findClientId(int userId) throws DAOException;
}
