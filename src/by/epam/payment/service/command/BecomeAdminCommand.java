package by.epam.payment.service.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.payment.dao.user.UserDaoImpl;
import by.epam.payment.exception.DAOException;
import by.epam.payment.exception.ServiceException;
import by.epam.payment.service.Page;
import by.epam.payment.util.MinValue;
import by.epam.payment.util.parameterConstant.ParameterName;
import by.epam.payment.util.parameterConstant.Path;

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
