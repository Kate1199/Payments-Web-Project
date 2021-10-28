package by.epam.payments.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.Page;
import by.epam.payments.util.MinValue;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;

public class BecomeAdminCommand implements ServletCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute(ParameterName.USER_ID);
		makeUserPotentialAdmin(userId);
		Page.forward(request, response, Path.APLICATION_SEND_PATH);
	}
	
	private boolean makeUserPotentialAdmin(int userId) throws ServiceException {
		boolean make;
		UserDaoImpl userDao = new UserDaoImpl();
		try {
			make = userDao.updatePotentialAdmin(MinValue.TRUE, userId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return make;
	}	
}
