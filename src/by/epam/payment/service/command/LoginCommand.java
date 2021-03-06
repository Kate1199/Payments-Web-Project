package by.epam.payment.service.command;

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
import by.epam.payment.dao.client.ClientDaoImpl;
import by.epam.payment.dao.user.UserDaoImpl;
import by.epam.payment.exception.DAOException;
import by.epam.payment.exception.IncorrectEnteredDataException;
import by.epam.payment.exception.ServiceException;
import by.epam.payment.service.hashing.Hasher;
import by.epam.payment.util.parameterConstant.LogMessage;
import by.epam.payment.util.parameterConstant.ParameterName;
import by.epam.payment.util.parameterConstant.Path;
import by.epam.payment.util.validation.Validator;

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
			passwordsEqual = Hasher.compareEncryptedData(user.getPassword(), request.getParameter(ParameterName.PASSWORD).toCharArray(), user.getSalt());
		} else {
			logger.log(Level.WARN, LogMessage.UNKNOWN_USER);
			updatePage(request, response, NO_SUCH_USER_RU);
			throw new IncorrectEnteredDataException(NO_SUCH_USER);
		}
		return passwordsEqual;
	}
	
	private boolean updatePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServiceException {
		boolean update = true;
		request.setAttribute(ParameterName.MESSAGE, message);
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
		session.setAttribute(ParameterName.USER_ID, user.getId());
		session.setAttribute(ParameterName.CLIENT_ID, takeClientId(user.getId()));
		try {
			response.sendRedirect(Path.INDEX_PATH);
		} catch (IOException e) {
			save = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return save;
	}
	
	private int takeClientId(int userId) throws ServiceException {
		ClientDaoImpl clientDao = new ClientDaoImpl();
		int clientId;
		try {
			clientId = clientDao.findClientId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return clientId;
	}

}
