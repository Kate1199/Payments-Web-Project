package by.epam.payments.dao.client;

import java.util.List;

import by.epam.payments.bean.Client;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.builders.ClientBuilder;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.parameterConstants.SqlRequest;
import by.epam.payments.util.validation.Validator;

public class ClientDaoImpl extends SqlDatabaseDAO<Client> implements ClientDao {
	
	private ClientBuilder clientBuilder = new ClientBuilder();

	@Override
	public Client findClientByUserId(int userId) throws DAOException {
		if(userId <= 0) {
			return new Client();
		}
		
		return takeEntity(findByParameterEntity(SqlRequest.FIND_CLIENT_BY_USER_ID, clientBuilder, userId));
	}
	
	@Override
	public int findClientId(int userId) throws DAOException {
		int clientId;
		Object clientIdParameter = findByParameterField(SqlRequest.FIND_CLIENT_ID_BY_USER_ID, userId);
		
		if(Validator.isNull(clientIdParameter)) {
			clientId = 0;
		} else {
			clientId = (int) clientIdParameter;
		}
		return clientId;
	}
	
	public Client findClientByIdentifiactionNumber(String identifiactionNumber) throws DAOException {
		List<Client> result = findByParameterEntity(SqlRequest.FIND_CLIENT_BY_IDENTIFIACTION_NUMBER,
				clientBuilder, identifiactionNumber);
		return takeEntity(result);
	}

	@Override
	public List<Client> findAll() throws DAOException {
		return super.findAll(SqlRequest.FIND_ALL_CLIENTS, clientBuilder);
	}

	@Override
	public boolean insertInto(Client client) throws DAOException {
		return super.addNewData(SqlRequest.ADD_CLIENT, client, clientBuilder);
	}

	@Override
	public Client findEntityById(int id) throws DAOException {
		if(id <= 0) {
			return new Client();
		}
		List<Client> result = findByParameterEntity(SqlRequest.FIND_CLIENT_BY_ID, clientBuilder, id);
		return takeEntity(result);
	}

	@Override
	public Client update(Client client) throws DAOException {
		if(Validator.isNull(client)) {
			return new Client();
		}
		
		if(!addNewData(SqlRequest.UPDATE_CLIENT, client, clientBuilder)) {
			client = new Client();
		} 
		return client;
	}

	@Override
	public boolean delete(Client client) throws DAOException {
		if(Validator.isNull(client)) {
			return false;
		}
		return super.addNewData(SqlRequest.HIDE_CLIENT, client, clientBuilder);
	}

	@Override
	public boolean delete(int id) throws DAOException {
		if(id <= 0) {
			return false;
		}
		return super.update(SqlRequest.HIDE_CLIENT_BY_ID, id);
	}

}
