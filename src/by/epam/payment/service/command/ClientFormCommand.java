package by.epam.payment.service.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payment.exception.ServiceException;
import by.epam.payment.service.ProfilePage;
import by.epam.payment.service.entityMaker.ClientMaker;
import by.epam.payment.util.parameterConstant.Path;

public class ClientFormCommand implements ServletCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		ClientMaker clientMaker = new ClientMaker(request, response);
		clientMaker.makeEntity();
		
		ProfilePage profilePage = new ProfilePage(request.getSession());
		profilePage.outputClientInformation(request);
		try {
			request.getRequestDispatcher(Path.PROFILE_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
