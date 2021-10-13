package by.epam.kisel.service;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.kisel.bean.User;
import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.encrytion.Encrypter;
import by.epam.kisel.util.parameterConstants.AttributeName;
import by.epam.kisel.util.parameterConstants.AttributeValue;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.kisel.util.validation.Validator;

public class LoginCommand implements ServletCommand {
	
	private static final String PASSWORD = "password";
	private static final String INVALID_PASSWORD_RU = "Неверный пароль";
	private static final String INVALID_PASSWORD = "invalid password";
	private static final String NO_SUCH_USER_RU = "Пользователя с таким именем не существует";
	private static final String NO_SUCH_USER = "User doen't exist";

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
			request.setAttribute(AttributeName.MESSAGE, NO_SUCH_USER_RU);
			request.setAttribute(AttributeName.REDIRECT, AttributeValue.LOGIN);
			try {
				request.getRequestDispatcher(Path.STATIC_PAGE_PATH).forward(request, response);
			} catch (IOException | ServletException e) {
				throw new ServiceException(e.getMessage());
			}
			throw new ServiceException(NO_SUCH_USER);
			//log
			
		}
		
		if(passwordsEqual) {
			HttpSession session = request.getSession();
			session.setAttribute(ParameterName.LOGIN, login);
			session.setAttribute(ParameterName.ROLE, user.getRole());
			try {
				response.sendRedirect(Path.INDEX_PATH);
			} catch (IOException e) {
				throw new ServiceException(e.getMessage());
			}
		} else {
			request.setAttribute(AttributeName.MESSAGE, INVALID_PASSWORD_RU);
			request.setAttribute(AttributeName.REDIRECT, AttributeValue.LOGIN);
			request.setAttribute(ParameterName.LOGIN, login);
			try {
				request.getRequestDispatcher(Path.STATIC_PAGE_PATH).forward(request, response);
			} catch (IOException | ServletException e) {
				throw new ServiceException(e.getMessage());
			}
			throw new ServiceException(INVALID_PASSWORD);
			//log
		}
		
		return passwordsEqual && userExists(user);
	}
	
	private User findUser(String login) throws ServiceException {
		UserDaoImpl dao = new UserDaoImpl();
		User user;
		
		try {
			user = dao.findByLogin(login);
		} catch (DAOException e) {
			user = new User();
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
