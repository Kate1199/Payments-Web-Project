package by.epam.payments.service.pageChange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.payments.exception.ServiceException;
import by.epam.payments.util.parameterConstants.ParameterName;

public class ExitPageChange implements PageChange {

	@Override
	public String takePage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		exit(request);
		HomePageChange homeRedirect = new HomePageChange();
		return homeRedirect.takePage(request, response);
	}
	
	private boolean exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(ParameterName.LOGIN);
		session.removeAttribute(ParameterName.ROLE);
		return true;
	}
}
