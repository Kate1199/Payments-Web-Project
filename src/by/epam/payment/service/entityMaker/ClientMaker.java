package by.epam.payment.service.entityMaker;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payment.bean.Client;
import by.epam.payment.dao.client.ClientDaoImpl;
import by.epam.payment.dao.user.UserDaoImpl;
import by.epam.payment.exception.DAOException;
import by.epam.payment.exception.IncorrectEnteredDataException;
import by.epam.payment.exception.ServiceException;
import by.epam.payment.util.parameterConstant.LogMessage;
import by.epam.payment.util.parameterConstant.ParameterName;
import by.epam.payment.util.parameterConstant.Path;
import by.epam.payment.util.validation.PatternValidator;
import by.epam.payment.util.validation.Validator;

public class ClientMaker implements EntityMakerFromRequest {
	
	private static final String INCORRECT_INFORMATION_RU = "Неверно введена информация";
	private static final String CLIENT_EXISTS_RU = "Клиент с таким идентификационным номером или номером телефона существует";
	private static final String CLIENT_EXISTS = "Client exists";
	
	private static Logger logger = LogManager.getLogger();
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ClientMaker() {
	}
	
	public ClientMaker(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public Client makeEntity() throws ServiceException {
		
		
		HttpSession session = request.getSession();

		String identificationNumber = PatternValidator.defineParameter(request, ParameterName.IDENTIFIACTION_NUMBER);;
		String lastName = PatternValidator.defineParameter(request, ParameterName.LAST_NAME);
		String firstName = PatternValidator.defineParameter(request, ParameterName.FIRST_NAME);
		String patronymic = PatternValidator.defineParameter(request, ParameterName.PATRONYMIC);
		String phoneNumber = PatternValidator.defineParameter(request, ParameterName.PHONE_NUMBER);
		String registrationAddress = PatternValidator.defineParameter(request, ParameterName.REGISTRATION_ADDRESS);
		String realAddress = PatternValidator.defineParameter(request, ParameterName.REAL_ADDRESS);
		String login = (String) session.getAttribute(ParameterName.LOGIN);
		int userId = findUserId(login);
		
		session.setAttribute(ParameterName.USER_ID, userId);
			
		Client client = new Client(identificationNumber, lastName, firstName, patronymic, phoneNumber,
				registrationAddress, realAddress, userId);
		
		try {
			if(!checkIfClientExists(identificationNumber, phoneNumber)) {
				addIfCorrect(client, identificationNumber);
			}
		} catch (ServiceException | IncorrectEnteredDataException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return client;
	}
	
	private int findUserId(String login) throws ServiceException {
		UserDaoImpl userDao = new UserDaoImpl();
		int userId;
		try {
			userId = userDao.findUserIdByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return userId;
	}
	
	private boolean addIfCorrect(Client client, String identificationNumber) throws ServiceException {
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
		if (Validator.isEmpty(client)) {	
			isCorrect = false;
			updatePage(INCORRECT_INFORMATION_RU);
			logger.log(Level.ERROR, LogMessage.INCORRECT_CLIENT_DATA);
			throw new IncorrectEnteredDataException(LogMessage.INCORRECT_CLIENT_DATA);
			
		}
		return isCorrect;
	}
	
	private boolean updatePage(String message) throws ServiceException {
		boolean update = true;
		request.setAttribute(ParameterName.MESSAGE, message);
		try {
			request.getRequestDispatcher(Path.CLIENT_FORM_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			update = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return update;
	}

	private boolean checkIfClientExists(String identifiactionNumber, String phoneNumber) 
			throws ServiceException, IncorrectEnteredDataException {
		boolean exists;
		ClientDaoImpl dao = new ClientDaoImpl();
		try {
			List<Client> clients = dao.findClientsByIdentifiactionAndPhone(identifiactionNumber, phoneNumber);
			if (clients.isEmpty()) {
				exists = false;
			} else {
				updatePage(CLIENT_EXISTS_RU);
				throw new IncorrectEnteredDataException(CLIENT_EXISTS);
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
			throw new ServiceException(e.getMessage());
		}
		return add;
	}
}
