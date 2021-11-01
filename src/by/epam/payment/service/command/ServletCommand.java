package by.epam.payment.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payment.exception.ServiceException;

public interface ServletCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
