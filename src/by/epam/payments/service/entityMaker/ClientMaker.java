package by.epam.payments.service.entityMaker;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Client;
import by.epam.payments.bean.User;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.builders.ClientBuilder;
import by.epam.payments.dao.client.ClientDaoImpl;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.IncorrectEnteredDataException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.util.parameterConstants.LogMessage;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.parameterConstants.SqlRequest;
import by.epam.payments.util.validation.PatternValidator;
import by.epam.payments.util.validation.Validator;

public class ClientMaker implements EntityMakerFromRequest<Client> {

	private static Logger logger = LogManager.getLogger();

	public ClientMaker() {
	}

	@Override
	public Client makeEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException {
		
		UserDaoImpl userDao = new UserDaoImpl();
		HttpSession session = request.getSession();

		String identificationNumber;
		String firstName;
		String lastName;
		String patronymic;
		String phoneNumber;
		String registrationAddress;
		String realAddress;
		String login = (String) session.getAttribute(ParameterName.LOGIN);
		int userId;
		try {
			userId = userDao.findUserIdByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
			session.setAttribute(ParameterName.USER_ID, userId);

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
		ClientDaoImpl dao = new ClientDaoImpl();
		try {
			Client client = dao.findClientByIdentifiactionNumber(identifiactionNumber);
			if (Validator.isNull(client)) {
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
		ClientDaoImpl clientDao = new ClientDaoImpl();
		try {
			clientDao.insertInto(client);
		} catch (DAOException e) {
			add = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return add;
	}

}
