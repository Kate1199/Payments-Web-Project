package by.epam.payments.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.Session;

import by.epam.payments.bean.Role;
import by.epam.payments.bean.User;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.encrytion.Encrypter;
import by.epam.payments.util.parameterConstants.AttributeName;
import by.epam.payments.util.parameterConstants.AttributeValue;
import by.epam.payments.util.parameterConstants.LogMessage;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.parameterConstants.SqlRequest;
import by.epam.payments.util.validation.Validator;

public class LoginCommand implements ServletCommand {
	
	private static final String PASSWORD = "password";
	private static final String INVALID_PASSWORD_RU = "Неверный пароль";
	private static final String INVALID_PASSWORD = "invalid password";
	private static final String NO_SUCH_USER_RU = "Пользователя с таким именем не существует";
	private static final String NO_SUCH_USER = "User doen't exist";
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		login(request, response);
	}
	
	//TODO: change name
	private boolean login(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		if(Validator.isNull(request)) {
			return false;
		}
		String login = request.getParameter(ParameterName.LOGIN);
		
		User user = findUser(login);

		boolean passwordsEqual;
		if(userExists(user)) {
			passwordsEqual = Encrypter.compareEncryptedData(user.getPassword(), request.getParameter(PASSWORD).toCharArray(), user.getSalt());
		} else {
			logger.log(Level.WARN, LogMessage.UNKNOWN_USER);
			request.setAttribute(AttributeName.MESSAGE, NO_SUCH_USER_RU);
			try {
				request.getRequestDispatcher(Path.LOGIN_PATH).forward(request, response);
			} catch (IOException | ServletException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			throw new ServiceException(NO_SUCH_USER);
			
		}
		
		if(passwordsEqual) {
			HttpSession session = request.getSession();
			session.setAttribute(ParameterName.LOGIN, login);
			session.setAttribute(ParameterName.ROLE, user.getRole());
			try {
				response.sendRedirect(Path.INDEX_PATH);
			} catch (IOException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		} else {
			logger.log(Level.WARN, LogMessage.INVALID_PASSWORD + user.getId());
			request.setAttribute(AttributeName.MESSAGE, INVALID_PASSWORD_RU);
			request.setAttribute(ParameterName.LOGIN, login);
			try {
				request.getRequestDispatcher(Path.LOGIN_PATH).forward(request, response);
			} catch (IOException | ServletException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			throw new ServiceException(INVALID_PASSWORD);
		}
		
		if(user.getRole() == Role.ADMIN) {
			logger.log(Level.INFO, LogMessage.ADMIN_LOGGED_IN + user.getId());
		}
		
		UserDaoImpl userDao = new UserDaoImpl();
		int userId;
		try {
			userId = userDao.findUserIdByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		HttpSession session = request.getSession();
		session.setAttribute(ParameterName.USER_ID, userId);
		
			
		return passwordsEqual && userExists(user);
	}
	
	private User findUser(String login) throws ServiceException {
		UserDaoImpl dao = new UserDaoImpl();
		User user;
		
		try {
			user = dao.findByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}
	
	private boolean userExists(User user) throws ServiceException {

		boolean exists = false;
		if(!Validator.isNull(user.getLogin())) {
			exists = true;
		}
		
		return exists;
	}

}
