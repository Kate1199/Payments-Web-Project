package by.epam.payments.service.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Payment;
import by.epam.payments.dao.payment.PaymentDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.Pagination;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.validation.Validator;

public class PaymentsCommand implements ServletCommand {
	
	private static int limit = 2;
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if(Validator.isNull(request) || Validator.isNull(response)) {
			return;
		}
		
		List<Payment> payments = takePayments(request, response);
		Pagination<Payment> pagination = new Pagination<Payment>(limit, request);
		pagination.output(payments);
		try {
			request.getRequestDispatcher(Path.PAYMENTS_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public List<Payment> takePayments(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		PaymentDaoImpl dao = new PaymentDaoImpl();
		List<Payment> payments;
		
		try {
			payments = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return payments;
		
	}
}
