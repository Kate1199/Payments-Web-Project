package by.epam.payments.service.entityMaker;

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
import by.epam.payments.util.parameterConstants.AttributeValue;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.validation.PatternValidator;
import by.epam.payments.util.validation.Validator;

public class UserMaker implements EntityMakerFromRequest<User> {

	private static final String USER_EXISTS = "user with such login already exists";
	private static final String USER_EXISTS_RU = "Пользователь с таким именем уже существует";
	private static final String INCORRECT_DATA_FORMAT = "login, email or password is incorrect";
	
	private static Logger logger = LogManager.getLogger();

	public UserMaker() {
	}

	public User makeEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException {
		
		String login;
		String email;
		byte[] password;
		byte[] salt = Encrypter.generateSalt();
		Role role;

		HttpSession session = request.getSession();
		login = PatternValidator.defineParameter(request, ParameterName.LOGIN);
		email = PatternValidator.defineParameter(request, ParameterName.EMAIL);
		password = PatternValidator.defineProtectedParameter(request, ParameterName.PASSWORD, salt);
		role = Role.USER;
		
		if (checkUserWithSuchLogin(login)) {
			request.setAttribute(AttributeName.MESSAGE, USER_EXISTS_RU);
			request.setAttribute(ParameterName.EMAIL, email);
			request.setAttribute(ParameterName.PASSWORD, request.getParameter(ParameterName.PASSWORD));
			try {
				request.getRequestDispatcher(Path.REGISTRATION_PATH).forward(request, response);
			} catch (IOException | ServletException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			throw new IncorrectEnteredDataException(USER_EXISTS);
		} 
		

		User user = new User(login, email, password, salt, role);
		if (isEnteredDataCorrect(user, response)) {
			session.setAttribute(ParameterName.LOGIN, login);
			session.setAttribute(ParameterName.ROLE, role);

		} else {
			try {
				request.getRequestDispatcher(Path.HOME_PATH).forward(request, response);
			} catch (ServletException | IOException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}

		return user;
	}

	private boolean checkUserWithSuchLogin(String login) throws ServiceException, IncorrectEnteredDataException {
		boolean isExist = false;
		UserDaoImpl dao = new UserDaoImpl();
		User userWithSuchLogin = null;
		try {
			userWithSuchLogin = dao.findByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		if (!Validator.isNull(userWithSuchLogin) || !Validator.isEmptyUser(userWithSuchLogin)) {
			isExist = true;
		}

		return isExist;
	}

	private boolean isEnteredDataCorrect(User user, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException {
		boolean isCorrect = true;
		if (Validator.isStringEmpty(user.getLogin()) || Validator.isStringEmpty(user.getEmail())
				|| Validator.isEmptyByteArray(user.getPassword())) {

			isCorrect = false;
			try {
				// TODO:change

				response.sendRedirect(Path.CLIENT_FORM_PATH);
			} catch (IOException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			throw new IncorrectEnteredDataException(INCORRECT_DATA_FORMAT);
		}
		return isCorrect;
	}
}
