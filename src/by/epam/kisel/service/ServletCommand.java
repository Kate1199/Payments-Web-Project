package by.epam.kisel.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.kisel.exception.ServiceException;

public interface ServletCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
