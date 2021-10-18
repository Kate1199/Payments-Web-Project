package by.epam.kisel.dao.client;

import java.util.List;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.ClientBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Client;

public class ClientDaoImpl extends SqlDatabaseDAO<Client> {
	
	private static final String FIND_BY_ID = "SELECT * FROM clients WHERE client_id = ?";
	private static final String FIND_BY_USER_ID = "SELECT * FROM clients WHERE Users_user_id = ?";

	
	public Client findClientByUserId(Integer userId) throws DAOException {
		return takeEntity(findByParameterEntity(FIND_BY_USER_ID, new ClientBuilder(), userId));
	}
	
	public int findClientIdByUserId(int userId) throws DAOException {
		int clientId;
		Object clientIdParameter = findByParameterField(SqlRequest.FIND_CLIENT_ID_BY_USER_ID, userId);
		
		if(Validator.isNull(clientIdParameter)) {
			clientId = 0;
		} else {
			clientId = (int) clientIdParameter;
		}
		return clientId;
	}

}
