package by.epam.kisel.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.LoginCommand;
import by.epam.kisel.service.PaginationCommand;
import by.epam.kisel.service.RedirectCommand;
import by.epam.kisel.service.RegistrationCommand;
import by.epam.kisel.service.ServletCommand;
import by.epam.kisel.service.encrytion.DataEncrypter;

public class Controller extends HttpServlet {
	
	private static final String REGISTRATION = "registration";
	private static final String LOGIN = "login";
	private static final String PAYMENTS = "payments";
	private static final String COMMAND = "command";	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RedirectCommand redirectCommand = new RedirectCommand();
		try {
			redirectCommand.execute(request, response);
		} catch (ServiceException e) {
			//log
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(COMMAND);
		ServletCommand servletCommand = defineCommand(command);
		try {
			servletCommand.execute(request, response);
		} catch (ServiceException e) {
			//log
			e.printStackTrace();
		}
		
	}
	
	private ServletCommand defineCommand(String command) {
		ServletCommand servletCommand = null;
		switch (command) {
		case REGISTRATION:
			servletCommand = new RegistrationCommand();
			break;
		case LOGIN:
			servletCommand = new LoginCommand();
			break;
		case PAYMENTS:
			servletCommand = new PaginationCommand();
			break;
		default:
			break;
		}
		return servletCommand;
	}
	
}
