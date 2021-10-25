package by.epam.payments.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.command.PageChangeCommand;
import by.epam.payments.service.command.ServletCommand;
import by.epam.payments.util.parameterConstants.CommandMap;
import by.epam.payments.util.parameterConstants.LogMessage;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String COMMAND = "command";
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PageChangeCommand redirectCommand = new PageChangeCommand();
		try {
			redirectCommand.execute(request, response);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, LogMessage.REDIRECT_FAILED + e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter(COMMAND);
		ServletCommand servletCommand = defineCommand(command);
		try {
			servletCommand.execute(request, response);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, LogMessage.POST_COMMAND_FAILED + e.getMessage());
		}
		
	}
	
	private ServletCommand defineCommand(String command) {
		CommandMap commandMap = CommandMap.getInstanse();
		return commandMap.getCommand(command);
	}
	
}
