package by.epam.payment.dao.client;

import java.util.List;

import by.epam.payment.bean.Client;
import by.epam.payment.dao.Dao;
import by.epam.payment.exception.DAOException;

public interface ClientDao extends Dao<Client> {
	
	public Client findClientByUserId(int userId) throws DAOException;
	public int findClientId(int userId) throws DAOException;
	public Client findClientByIdentifiactionNumber(String identifiactionNumber) throws DAOException;
	public List<Client> findClientsByIdentifiactionAndPhone(String identidicationNumber, String phoneNumber) 
			throws DAOException;
}
