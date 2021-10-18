package by.epam.kisel.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.entityMaker.ClientMaker;
import by.epam.kisel.util.parameterConstants.Path;
import by.epam.payments.bean.Client;

public class ClientFormCommand implements ServletCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ClientMaker clientMaker = new ClientMaker();
		Client client;
		try {
			client = clientMaker.makeEntity(request, response);
		} catch (ServiceException | IncorrectEnteredDataException e) {
			throw new ServiceException(e.getMessage());
		}
		ProfilePage profilePage = new ProfilePage(request.getSession());
		profilePage.outputClientInformation(request);
		try {
			request.getRequestDispatcher(Path.PROFILE_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
