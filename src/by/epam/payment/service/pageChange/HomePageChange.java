package by.epam.payment.service.pageChange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payment.dao.payment.PaymentDaoImpl;
import by.epam.payment.exception.DAOException;
import by.epam.payment.exception.ServiceException;
import by.epam.payment.util.parameterConstant.ParameterName;
import by.epam.payment.util.parameterConstant.Path;

public class HomePageChange implements PageChange {
	
	private static final int PREVIOUS_LIMIT = 0;
	private static final int LIMIT = 4;
	
	public HomePageChange() {
	}
	
	@Override
	public String takePage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		loadHomePayments(request);
		return Path.HOME_PATH;
	}
	
	private boolean loadHomePayments(HttpServletRequest request) throws ServiceException {
		boolean load;
		PaymentDaoImpl daoImpl = new PaymentDaoImpl();
		try {
			request.setAttribute(ParameterName.PAYMENTS, daoImpl.findAllWithLimits(PREVIOUS_LIMIT, LIMIT));
			load = true;
		} catch (DAOException e) {
			load = false;
			throw new ServiceException();
		}
		
		return load;
	}

	
}
