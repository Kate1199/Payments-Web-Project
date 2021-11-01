package by.epam.payment.service.pageChange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.payment.exception.ServiceException;

public class ExitPageChange implements PageChange {

	@Override
	public String takePage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		exit(request);
		HomePageChange homeRedirect = new HomePageChange();
		return homeRedirect.takePage(request, response);
	}
	
	private boolean exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return true;
	}
}
