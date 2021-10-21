package by.epam.payments.service.pageChange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.payments.bean.Client;
import by.epam.payments.dao.client.ClientDaoImpl;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.ProfilePage;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.validation.Validator;

public class ProfilePageChange implements PageChange {

	@Override
	public String takePage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Client client = findClient(request);
		return definePage(client, request);
	}
	
	private Client findClient(HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute(ParameterName.LOGIN);
		Client client;
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			int userId = userDaoImpl.findUserIdByLogin(login);
			ClientDaoImpl clientDaoIml = new ClientDaoImpl();
			client = clientDaoIml.findClientByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return client;
	}
	
	private String definePage(Client client, HttpServletRequest request) throws ServiceException {
		HttpSession session = request.getSession();
		String page;
		if(Validator.isEmpty(client)) {
			page = Path.CLIENT_FORM_PATH;
		} else {
			page = Path.PROFILE_PATH;
			ProfilePage profilePage = new ProfilePage(session);
			profilePage.outputClientInformation(request);
		}
		return page;
	}
	
}
