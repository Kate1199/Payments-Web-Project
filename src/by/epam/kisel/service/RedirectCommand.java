package by.epam.kisel.service;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.client.ClientDaoImpl;
import by.epam.kisel.dao.payment.PaymentDaoImpl;
import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.AttributeName;
import by.epam.kisel.util.parameterConstants.AttributeValue;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.kisel.util.parameterConstants.PathMap;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Client;
import by.epam.payments.bean.User;

public class RedirectCommand implements ServletCommand {
	
	public RedirectCommand() {
	}
	
	private static final String PARAMETER_NAME = "redirect";
	
	private static final String HOME_VALUE = "home";
	private static final String EXIT_VALUE = "exit";
	private static final String PAYMENTS_VALUE = "payments";
	
	private static final int PREVIOUS_LIMIT = 0;
	private static final int LIMIT = 4;
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String page = definePage(request);
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ServletException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	private String definePage(HttpServletRequest request) throws ServiceException {
		String value = request.getParameter(PARAMETER_NAME);
		PathMap pathMap = PathMap.getInstanse();
		String page;
		
		if(Validator.isNull(value) || value.equals(HOME_VALUE)) {
			page = pathMap.getPath(HOME_VALUE);
			loadHomePayments(request);
		} else if (value.equals(EXIT_VALUE)) {
			exit(request);
			page = pathMap.getPath(HOME_VALUE);
			loadHomePayments(request);
		} else if(value.equals(AttributeValue.PROFILE)) {
			page = checkIfActiveClient(request);
		} else {
			page = pathMap.getPath(value);
		}
		return page;
	}
	
	private boolean exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(ParameterName.LOGIN);
		session.removeAttribute(ParameterName.ROLE);
		return true;
	}
	
	private boolean loadHomePayments(HttpServletRequest request) throws ServiceException {
		boolean load;
		PaymentDaoImpl daoImpl = new PaymentDaoImpl();
		try {
			request.setAttribute(PAYMENTS_VALUE, daoImpl.findAllWithLimits(PREVIOUS_LIMIT, LIMIT));
			load = true;
		} catch (DAOException e) {
			load = false;
			throw new ServiceException();
		}
		
		return load;
	}
	
	private String checkIfActiveClient(HttpServletRequest request) throws ServiceException {
		String page;
		PathMap pathMap = PathMap.getInstanse();
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute(ParameterName.LOGIN);
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		Client client;
		try {
			User user = userDaoImpl.findByLogin(login);
			ClientDaoImpl clientDaoIml = new ClientDaoImpl();
			client = clientDaoIml.findClientByUserId(user.getId());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
		if(Validator.isEmpty(client) || Validator.isNull(client)) {
			page = pathMap.getPath(AttributeValue.CLIENT_FORM);
		} else {
			page = pathMap.getPath(AttributeValue.PROFILE);
			ProfilePage profilePage = new ProfilePage(session);
			profilePage.outputClientInformation(request);
		}
		
		return page;
		
	}

}
