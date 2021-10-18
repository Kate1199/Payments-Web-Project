package by.epam.payments.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.bean.Client;
import by.epam.payments.exception.IncorrectEnteredDataException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.entityMaker.ClientMaker;
import by.epam.payments.util.parameterConstants.Path;

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
