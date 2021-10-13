package by.epam.kisel.service;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.payment.PaymentDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.AttributeName;
import by.epam.kisel.util.parameterConstants.AttributeValue;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.kisel.util.validation.Validator;

public class RedirectCommand implements ServletCommand {
	
	public RedirectCommand() {
	}
	
	private static final String PARAMETER_NAME = "redirect";
	
	private static final String REGISTRATION_VALUE = "registration";
	private static final String LOG_IN_VALUE = "login";
	private static final String HOME_VALUE = "home";
	private static final String EXIT_VALUE = "exit";
	private static final String PAYMENTS_VALUE = "payments";
	
	private static final int PREVIOUS_LIMIT = 0;
	private static final int LIMIT = 4;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		definePage(request);
		try {
			request.getRequestDispatcher(Path.STATIC_PAGE_PATH).forward(request, response);
		} catch (ServletException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	private boolean definePage(HttpServletRequest request) throws ServiceException {
		boolean define = true;
		String value = request.getParameter(PARAMETER_NAME);
		
		if(Validator.isNull(value) || value.equals(HOME_VALUE)) {
			request.setAttribute(PARAMETER_NAME, HOME_VALUE);
			loadHomePayments(request);
			define = false;
		} else if (value.equals(EXIT_VALUE)) {
			exit(request);
			request.setAttribute(AttributeName.REDIRECT, AttributeValue.HOME);
			loadHomePayments(request);
		} else {
			request.setAttribute(AttributeName.REDIRECT, value);
		}
		return define;
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

}
