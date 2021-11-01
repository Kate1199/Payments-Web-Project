package by.epam.payment.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payment.exception.ServiceException;

public class Page {
	
	private static Logger logger = LogManager.getLogger();
	
	private Page() {
	}
	
	public static boolean forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServiceException {
		boolean go = true;
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			go = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return go;
	}
}
