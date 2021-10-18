package by.epam.kisel.service;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.entityMaker.UserMaker;
import by.epam.kisel.util.parameterConstants.AttributeName;
import by.epam.kisel.util.parameterConstants.AttributeValue;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.payments.bean.User;

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
	
	
