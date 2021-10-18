package by.epam.kisel.service.entityMaker;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.ClientBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.LogMessage;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.PatternValidator;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Client;
import by.epam.payments.bean.User;

public class ClientMaker implements EntityMakerFromRequest<Client> {

	private static Logger logger = LogManager.getLogger();

	public ClientMaker() {
	}

	@Override
	public Client makeEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException {
		
		SqlDatabaseDAO<User> userDao = new SqlDatabaseDAO<User>();
		HttpSession session = request.getSession();

		String identificationNumber;
		String firstName;
		String lastName;
		String patronymic;
		String phoneNumber;
		String registrationAddress;
		String realAddress;
		int userId;
		try {
			long id = (long) userDao.findByParameterField(SqlRequest.FIND_ID_BY_LOGIN, session.getAttribute(ParameterName.LOGIN));
			userId = (int) id;
			session.setAttribute(ParameterName.USER_ID, userId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		identificationNumber = PatternValidator.defineParameter(request, ParameterName.IDENTIFIACTION_NUMBER);
		lastName = PatternValidator.defineParameter(request, ParameterName.LAST_NAME);
		firstName = PatternValidator.defineParameter(request, ParameterName.FIRST_NAME);
		patronymic = PatternValidator.defineParameter(request, ParameterName.PATRONYMIC);
		phoneNumber = PatternValidator.defineParameter(request, ParameterName.PHONE_NUMBER);
		registrationAddress = PatternValidator.defineParameter(request, ParameterName.REGISTRATION_ADDRESS);
		realAddress = PatternValidator.defineParameter(request, ParameterName.REAL_ADDRESS);
		

		Client client = new Client(identificationNumber, lastName, firstName, patronymic, phoneNumber,
				registrationAddress, realAddress, userId);
		
		if(!checkIfClientExists(identificationNumber)) {
			addIfCorrect(client, response, identificationNumber);
		}
		
		return client;
	}
	
	private boolean addIfCorrect(Client client, HttpServletResponse response, String identificationNumber) throws ServiceException {
		boolean add = true;
		try {
			if (isClientCorrect(client, response)) {
				addClientInDatabase(identificationNumber, client);
			} else {
				add = false;
				throw new ServiceException(LogMessage.INCORRECT_CLIENT_DATA);
			}
		} catch (ServiceException | IncorrectEnteredDataException e) {
			add = false;
			throw new ServiceException(e.getMessage());
		}
		return add;
	}

	private boolean isClientCorrect(Client client, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException {
		boolean isCorrect = true;
		if (Validator.isStringEmpty(client.getFirstName()) || Validator.isStringEmpty(client.getLastName())
				|| Validator.isStringEmpty(client.getLastName()) || Validator.isStringEmpty(client.getPhoneNumber())
				|| Validator.isStringEmpty(client.getIdentificationNumber())
				|| Validator.isStringEmpty(client.getRegistrationAddress())
				|| Validator.isStringEmpty(client.getRealAddress())) {

			isCorrect = false;
			logger.log(Level.ERROR, LogMessage.INCORRECT_CLIENT_DATA);
			try {
				response.sendRedirect(Path.CLIENT_FORM_PATH);
			} catch (IOException e) {
				throw new ServiceException(e.getMessage());
			}
			throw new IncorrectEnteredDataException(LogMessage.INCORRECT_CLIENT_DATA);
		}
		return isCorrect;
	}

	private boolean checkIfClientExists(String identifiactionNumber) throws ServiceException {
		boolean exists = true;
		SqlDatabaseDAO<Client> dao = new SqlDatabaseDAO<Client>();
		try {
			List<Client> result = dao.findByParameterEntity(SqlRequest.FIND_CLIENT_BY_IDENTIFIACTION_NUMBER,
					new ClientBuilder(), identifiactionNumber);
			if (result.isEmpty()) {
				exists = false;
			}
		} catch (DAOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return exists;
	}

	private boolean addClientInDatabase(String identificationNumber, Client client) throws ServiceException {
		boolean add = true;
		SqlDatabaseDAO<Client> dao = new SqlDatabaseDAO<Client>();
		try {
			dao.insertInto(SqlRequest.ADD_CLIENT, client, new ClientBuilder());
		} catch (DAOException e) {
			add = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return add;
	}

}
