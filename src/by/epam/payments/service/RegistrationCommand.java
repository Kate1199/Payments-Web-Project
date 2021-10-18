package by.epam.payments.service;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.User;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.IncorrectEnteredDataException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.entityMaker.UserMaker;
import by.epam.payments.util.parameterConstants.AttributeName;
import by.epam.payments.util.parameterConstants.AttributeValue;
import by.epam.payments.util.parameterConstants.Path;

public class RegistrationCommand implements ServletCommand {
	
	private static Logger logger = LogManager.getLogger();
	
	public RegistrationCommand() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
	User user = null;
		try {
			UserMaker userMaker = new UserMaker();
			user = userMaker.makeEntity(request, response);
		} catch (IncorrectEnteredDataException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	
		UserDaoImpl dao = new UserDaoImpl();
		try {
			dao.create(user);
			request.setAttribute(AttributeName.REDIRECT, AttributeValue.HOME);
			response.sendRedirect(Path.INDEX_PATH);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		} 
	}
}
	
	
