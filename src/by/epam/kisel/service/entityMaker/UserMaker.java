package by.epam.kisel.service.entityMaker;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.kisel.bean.Role;
import by.epam.kisel.bean.User;
import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.encrytion.Encrypter;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.validation.PatternValidator;
import by.epam.kisel.util.validation.Validator;

public class UserMaker implements EntityMakerFromRequest<User>{
	
	private static final String USER_EXISTS = "user with such login already exists";
	private static final String INCORRECT_DATA_FORMAT = "login, email or password is incorrect";
	
	private static final String STANDART_ROLE = "user";
	
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
		checkUserWithSuchLogin(login);
		email = PatternValidator.defineParameter(request, ParameterName.EMAIL);
		password = PatternValidator.defineProtectedParameter(request, ParameterName.PASSWORD, salt);
		password = equalPasswords(request, password, salt);
		role = Role.USER;
		
		User user = new User(login, email, password, salt, role);
		isEnteredDataCorrect(user, response);
		
		session.setAttribute(ParameterName.LOGIN, login);
		session.setAttribute(ParameterName.ROLE, role);

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
		
		if(!Validator.isEmptyUser(userWithSuchLogin)) {
			//TODO: something
			isExist = true;
			throw new IncorrectEnteredDataException(USER_EXISTS);
		}
		
		return isExist;
	}
	
	private byte[] equalPasswords(HttpServletRequest request, byte[] password, byte[] salt) throws ServiceException {
		char[] repeatPassword = request.getParameter(ParameterName.REPEAT_PASSWORD).toCharArray();
		if(!Encrypter.compareEncryptedData(password, repeatPassword, salt)) {
			password = new byte[MinValues.MIN_ARRAY_SIZE];
		}
		return password;
	}
	
	private boolean isEnteredDataCorrect(User user, HttpServletResponse response) throws ServiceException, IncorrectEnteredDataException {
		boolean isCorrect = true;
		if(Validator.isStringEmpty(user.getLogin())
				|| Validator.isStringEmpty(user.getEmail()) 
				|| Validator.isEmptyByteArray(user.getPassword()) ) {
			
			isCorrect = false;
			try {
				//TODO:change
				
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				throw new ServiceException(e.getMessage());
			}
			throw new IncorrectEnteredDataException(INCORRECT_DATA_FORMAT);
		} 
		return isCorrect;
	}
}
