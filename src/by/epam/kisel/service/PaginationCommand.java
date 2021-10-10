package by.epam.kisel.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import by.epam.kisel.bean.Payment;
import by.epam.kisel.dao.payment.PaymentDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.Path;

public class PaginationCommand implements ServletCommand {
	
	private static final String PARAMETER_NAME = "payments";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		outputPayments(request);
		try {
			request.getRequestDispatcher(Path.INDEX_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	private boolean outputPayments(HttpServletRequest request) throws ServiceException {
		
		boolean output = true;
		PaymentDaoImpl dao = new PaymentDaoImpl();
		List<Payment> payments;
		try {
			payments = dao.findAllWithLimits();
		} catch (DAOException e) {
			output = false;
			throw new ServiceException(e.getMessage());
		}
		
		request.setAttribute(PARAMETER_NAME, payments);
		request.setAttribute("redirect", "payments");
		return output;
	}

}
