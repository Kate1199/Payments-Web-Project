package by.epam.kisel.service;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.parameterConstants.Path;

public class RedirectCommand implements ServletCommand {
	
	public RedirectCommand() {
	}
	
	private static final String PARAMETER_NAME = "redirect";
	
	private static final String REGISTRATION_VALUE = "registration";
	private static final String LOG_IN_VALUE = "login";
	private static final String HOME_VALUE = "home";
	private static final String EXIT_VALUE = "exit";
	private static final String PAYMENTS_VALUE = "payments";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		definePage(request);
		try {
			request.getRequestDispatcher(Path.INDEX_PATH).forward(request, response);
		} catch (ServletException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	//TODO: change return type
	private void definePage(HttpServletRequest request) throws ServiceException {
		String value = request.getParameter(PARAMETER_NAME);
		
		switch (value) {
		case REGISTRATION_VALUE:
			request.setAttribute(PARAMETER_NAME, REGISTRATION_VALUE);
			break;
		case LOG_IN_VALUE:
			request.setAttribute(PARAMETER_NAME, LOG_IN_VALUE);
			break;
		case PAYMENTS_VALUE:
			request.setAttribute(PARAMETER_NAME, PAYMENTS_VALUE);
			break;
		case HOME_VALUE:
			request.setAttribute(PARAMETER_NAME, HOME_VALUE);
			break;
		case EXIT_VALUE:
			exit(request);
			request.setAttribute(PARAMETER_NAME, HOME_VALUE);
			break;
		default:
			request.setAttribute(PARAMETER_NAME, HOME_VALUE);
		}
	}
	
	private static boolean exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(ParameterName.LOGIN);
		session.removeAttribute(ParameterName.ROLE);
		return true;
	}

}
