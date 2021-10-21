package by.epam.payments.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.entityMaker.UserMaker;

public class RegistrationCommand implements ServletCommand {

	
	public RegistrationCommand() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
			UserMaker userMaker = new UserMaker(request, response);
			userMaker.makeEntity();
	}
}
	
	
