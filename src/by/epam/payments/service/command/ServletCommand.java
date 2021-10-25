package by.epam.payments.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.exception.ServiceException;

public interface ServletCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
