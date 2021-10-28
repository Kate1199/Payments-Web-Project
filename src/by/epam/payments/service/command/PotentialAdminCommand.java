package by.epam.payments.service.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.bean.User;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.Page;
import by.epam.payments.service.Pagination;
import by.epam.payments.util.parameterConstants.Path;

public class PotentialAdminCommand implements ServletCommand {
	
	private static final int LIMIT = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		List<User> users = takePotentialAdmins();
		Pagination<User> pagination = new Pagination<User>(LIMIT, request);
		pagination.output(users);
		Page.forward(request, response, Path.POTENTIAL_ADMINS_PATH);
	}
	
	private List<User> takePotentialAdmins() throws ServiceException{
		UserDaoImpl userDao = new UserDaoImpl();
		List<User> potentialAdmins = new ArrayList<User>();
		try {
			potentialAdmins = userDao.findPotentialAdmins();
		} catch (DAOException e) {
		throw new ServiceException(e.getMessage());
		}
		return potentialAdmins;
	}

}
