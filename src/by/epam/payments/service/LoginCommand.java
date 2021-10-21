package by.epam.payments.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import by.epam.payments.bean.Role;
import by.epam.payments.bean.User;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.IncorrectEnteredDataException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.encrytion.Encrypter;
import by.epam.payments.util.parameterConstants.AttributeName;
import by.epam.payments.util.parameterConstants.LogMessage;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.validation.Validator;

public class LoginCommand implements ServletCommand {
	
	private static final String INVALID_PASSWORD_RU = "Неверный пароль";
	private static final String INVALID_PASSWORD = "invalid password";
	private static final String NO_SUCH_USER_RU = "Пользователя с таким именем не существует";
	private static final String NO_SUCH_USER = "User doen't exist";
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		login(request, response);
	}
	
	private boolean login(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		if(Validator.isNull(request)) {
			return false;
		}
		
		String login = request.getParameter(ParameterName.LOGIN);
		
		User user = findUser(login);

		boolean passwordsEqual;
		try {
			passwordsEqual = checkArePasswordsEqualIfExists(user, request, response);
		} catch (ServiceException | IncorrectEnteredDataException e) {
			throw new ServiceException(e.getMessage());
		}
		
		if(passwordsEqual) {
			saveDataToSession(request, response, user);
		} else {
			logger.log(Level.WARN, LogMessage.INVALID_PASSWORD + user.getId());
			request.setAttribute(ParameterName.LOGIN, login);
			updatePage(request, response, INVALID_PASSWORD_RU);
			throw new ServiceException(INVALID_PASSWORD);
		}
		
		if(user.getRole() == Role.ADMIN) {
			logger.log(Level.INFO, LogMessage.ADMIN_LOGGED_IN + user.getId());
		}
		
		addToDatabase(request, login);
		
		return passwordsEqual;
	}
	
	private User findUser(String login) throws ServiceException {
		UserDaoImpl dao = new UserDaoImpl();
		User user;
		
		try {
			user = dao.findUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}
	
	private boolean doesUserExist(User user) throws ServiceException {

		boolean exists = true;
		if(Validator.isEmpty(user)) {
			exists = false;
		}
		
		return exists;
	} 
	
	private boolean checkArePasswordsEqualIfExists(User user, HttpServletRequest request, 
			HttpServletResponse response) throws ServiceException, IncorrectEnteredDataException {
		boolean passwordsEqual;
		if(doesUserExist(user)) {
			passwordsEqual = Encrypter.compareEncryptedData(user.getPassword(), request.getParameter(ParameterName.PASSWORD).toCharArray(), user.getSalt());
		} else {
			logger.log(Level.WARN, LogMessage.UNKNOWN_USER);
			updatePage(request, response, NO_SUCH_USER_RU);
			throw new IncorrectEnteredDataException(NO_SUCH_USER);
		}
		return passwordsEqual;
	}
	
	private boolean updatePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServiceException {
		boolean update = true;
		request.setAttribute(AttributeName.MESSAGE, message);
		try {
			request.getRequestDispatcher(Path.LOGIN_PATH).forward(request, response);
		} catch (IOException | ServletException e) {
			update = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return update;
	}
	
	private boolean saveDataToSession(HttpServletRequest request, HttpServletResponse response, User user) 
			throws ServiceException {
		boolean save = true;
		HttpSession session = request.getSession();
		session.setAttribute(ParameterName.LOGIN, user.getLogin());
		session.setAttribute(ParameterName.ROLE, user.getRole());
		try {
			response.sendRedirect(Path.INDEX_PATH);
		} catch (IOException e) {
			save = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return save;
	}
	
	private boolean addToDatabase(HttpServletRequest request, String login) throws ServiceException {
		boolean add = true;
		UserDaoImpl userDao = new UserDaoImpl();
		int userId;
		try {
			userId = userDao.findUserIdByLogin(login);
		} catch (DAOException e) {
			add = false;
			throw new ServiceException(e.getMessage());
		}
		HttpSession session = request.getSession();
		session.setAttribute(ParameterName.USER_ID, userId);
		return add;
	}

}
