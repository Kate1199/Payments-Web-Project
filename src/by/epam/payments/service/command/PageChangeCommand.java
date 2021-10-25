package by.epam.payments.service.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.pageChange.ExitPageChange;
import by.epam.payments.service.pageChange.HomePageChange;
import by.epam.payments.service.pageChange.ProfilePageChange;
import by.epam.payments.util.parameterConstants.PageName;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.PathMap;
import by.epam.payments.util.validation.Validator;

public class PageChangeCommand implements ServletCommand {
	
	public PageChangeCommand() {
	}
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String page = definePage(request, response);
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
	
	private String definePage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String value = request.getParameter(ParameterName.REDIRECT);
		PathMap pathMap = new PathMap(request, response);
		String page;
		
		if(Validator.isNull(value) || value.equals(PageName.HOME)) {
			page = new HomePageChange().takePage(request, response);
		} else if(value.equals(PageName.EXIT)) {
			page = new ExitPageChange().takePage(request, response);
		} else if(value.equals(PageName.PROFILE)) {
			page = new ProfilePageChange().takePage(request, response);
		} else {
			page = pathMap.getPath(value);
		}
		
		return page;
	}
	
}
