package by.epam.kisel.service;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.kisel.bean.User;
import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.encrytion.Encrypter;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.kisel.util.validation.Validator;

public class LoginCommand implements ServletCommand {
	
	private static final String PASSWORD = "password";

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
			try {
				//TODO: message
				System.out.println("user doesn't exist");
				response.sendRedirect(Path.LOGIN_PATH);
				return false;
			} catch (IOException e) {
				throw new ServiceException(e.getMessage());
			}
			
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
			try {
				response.sendRedirect(Path.LOGIN_PATH);
			} catch (IOException e) {
				throw new ServiceException(e.getMessage());
			}
			System.out.println("invalid password");
			//TODO: message
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
