package by.epam.payment.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payment.exception.ServiceException;
import by.epam.payment.service.entityMaker.UserMaker;

public class RegistrationCommand implements ServletCommand {

	
	public RegistrationCommand() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
			UserMaker userMaker = new UserMaker(request, response);
			userMaker.makeEntity();
	}
}
	
	
