package by.epam.payment.service.entityMaker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payment.bean.Role;
import by.epam.payment.bean.User;
import by.epam.payment.dao.user.UserDaoImpl;
import by.epam.payment.exception.DAOException;
import by.epam.payment.exception.IncorrectEnteredDataException;
import by.epam.payment.exception.ServiceException;
import by.epam.payment.service.hashing.Hasher;
import by.epam.payment.util.MinValue;
import by.epam.payment.util.parameterConstant.ParameterName;
import by.epam.payment.util.parameterConstant.Path;
import by.epam.payment.util.validation.PatternValidator;
import by.epam.payment.util.validation.Validator;

public class UserMaker implements EntityMakerFromRequest {

	private static final String USER_EXISTS = "user with such login already exists";
	private static final String USER_EXISTS_RU = "Пользователь с таким именем или email уже существует";
	private static final String INCORRECT_DATA_FORMAT = "login, email or password is incorrect";
	private static final String INCORRECT_DATA_FORMAT_RU = "Введите данные в требуемом формате";
	
	private static Logger logger = LogManager.getLogger();
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserMaker() {
	}
	
	public UserMaker(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public User makeEntity() throws ServiceException {
		
		String login = PatternValidator.defineParameter(request, ParameterName.LOGIN);
		String email = PatternValidator.defineParameter(request, ParameterName.EMAIL);
		byte[] salt = Hasher.generateSalt();
		byte[] password = PatternValidator.defineProtectedParameter(request, ParameterName.PASSWORD, salt);
		Role role = Role.USER;
		
		sendMessageIfUserExist(login, email);

		User user = new User(login, email, password, salt, role);
		saveDataToSessionIfCorrect(user);
		saveUserToDatabase(user);
		try {
			response.sendRedirect(Path.INDEX_PATH);
		} catch (IOException e) {
			throw new ServiceException();
		}
	

		return user;
	}
	
	private boolean sendMessageIfUserExist(String login, String email) throws ServiceException {
		
		boolean send = true;
		try {
			if (checkIfUserExist(login, email)) {
				request.setAttribute(ParameterName.MESSAGE, USER_EXISTS_RU);
				updatePage();
				throw new IncorrectEnteredDataException(USER_EXISTS);
			}
		} catch (ServiceException | IncorrectEnteredDataException e) {
			send = false;
			throw new ServiceException(e.getMessage());
		} 
		
		return send;
	}
	
	private boolean saveDataToSessionIfCorrect(User user) throws ServiceException {
		boolean save = false;
		HttpSession session = request.getSession();
		try {
			if(!checkIfDataIncorrect(user)) {
				save = true;
				session.setAttribute(ParameterName.LOGIN, user.getLogin());
				session.setAttribute(ParameterName.ROLE, user.getRole());
			}
		} catch (ServiceException | IncorrectEnteredDataException e) {
			throw new ServiceException(e.getMessage());
		}
		return save;
	}
	
	private boolean checkIfDataIncorrect(User user) throws ServiceException, IncorrectEnteredDataException {
		boolean incorrect = false;
		
		if(Validator.isEmpty(user)) {
			incorrect = true;
			request.setAttribute(ParameterName.MESSAGE, INCORRECT_DATA_FORMAT_RU);
			updatePage();
			throw new IncorrectEnteredDataException(INCORRECT_DATA_FORMAT);
		}
		return incorrect;
	}
	
	private boolean updatePage() throws ServiceException {
		boolean update = true;
		try {
			request.getRequestDispatcher(Path.REGISTRATION_PATH).forward(request, response);
		} catch (IOException | ServletException e) {
			update = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return update;
	}

	private boolean checkIfUserExist(String login, String email) throws ServiceException, IncorrectEnteredDataException {
		boolean exists = false;
		UserDaoImpl dao = new UserDaoImpl();
		User userWithSuchLogin;
		User userWithSuchEmail;
		try {
			userWithSuchLogin = dao.findUserByLogin(login);
			userWithSuchEmail = dao.findUserByEmail(email);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		if (!Validator.isEmpty(userWithSuchLogin) || !Validator.isEmpty(userWithSuchEmail)) {
			exists = true;
		}

		return exists;
	}
	
	private boolean saveUserToDatabase(User user) throws ServiceException {
		boolean save = false;
		UserDaoImpl userDao = new UserDaoImpl();
		try {
			save = userDao.insertInto(user);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return save;
	}
}
