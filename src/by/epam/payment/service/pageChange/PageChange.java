package by.epam.payment.service.pageChange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payment.exception.ServiceException;

public interface PageChange {
	
	public String takePage(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
