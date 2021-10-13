package by.epam.kisel.service;

import java.io.IOException;
import java.util.ArrayList;
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
import by.epam.kisel.util.validation.Validator;

public class PaginationCommand implements ServletCommand {
	
	private static final String PARAMETER_NAME = "payments";
	private static final String REDIRECT_ATTRIBUTE = "redirect";
	private static final String REDIRECT_ATTRIBUTE_PAYMENTS = "payments";
	private static final String PAGINATION_PARAMETER = "pagination";
	private static final String PAGINATION_PARAMETER_PREVIOUS = "previous";
	private static final String PAGINATION_PARAMETER_NEXT = "next";
	private static final String PAGINATION_PARAMETER_GO_TO = "goTo";
	private static final String PAGINATION_ATTRIBUTE_GO_TO_PAGE = "goToPage";
	private static final String PAGE = "currentPage";
	private static final String TOTAL_PAGES = "totalPages";
	private static final String PAGE_NUMBERS = "pageNumbers";
	
	private static final int FIRST_PAGE = 1;
	private static final int CORRECTION_PAGE = 1;
	private static final int MIN_MIDDLE_PAGE_NUMBER = 3;
	private static final int ZERO_REMINDER = 0;
	private static final int ZERO_LIMIT = 0;
	
	private static int previousLimit = 0;
	private static int limit = 2;
	private static int currentPage = 1;
	private static int totalPages;
	private static int visiblePageNumbers = 5;
	
	public PaginationCommand() {
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if(Validator.isNull(request) || Validator.isNull(response)) {
			return;
		}
		outputPayments(request, response);
		try {
			request.getRequestDispatcher(Path.STATIC_PAGE_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	private boolean outputPayments(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		boolean output = true;
		PaymentDaoImpl dao = new PaymentDaoImpl();
		List<Payment> onePagePayments;
		List<Payment> payments;
		
		String pagiantion = request.getParameter(PAGINATION_PARAMETER);
		if(Validator.isNull(pagiantion)) {
			
		} else if(pagiantion.equals(PAGINATION_PARAMETER_NEXT)) {
			nextPage(request);
		} else if(pagiantion.equals(PAGINATION_PARAMETER_PREVIOUS)) {
			previousPage(request, response);
		} else if(pagiantion.equals(PAGINATION_PARAMETER_GO_TO)) {
			goToPage(request);
		}
		try {
			payments = dao.findAll();
			onePagePayments = dao.findAllWithLimits(previousLimit, limit);
		} catch (DAOException e) {
			output = false;
			throw new ServiceException(e.getMessage());
		}
		
		request.setAttribute(REDIRECT_ATTRIBUTE, REDIRECT_ATTRIBUTE_PAYMENTS);
		HttpSession session = request.getSession();
		session.setAttribute(PAGE, currentPage);
		request.setAttribute(TOTAL_PAGES, totalPages);
		countTotalPages(payments);
		request.setAttribute(PARAMETER_NAME, onePagePayments);
		request.setAttribute(PAGE_NUMBERS, showPageNumbers());
		
		return output;
		
	}
	
	private boolean nextPage(HttpServletRequest request) {
		
		boolean go = false;
		if(currentPage <= totalPages) {
			previousLimit = currentPage * limit;
			currentPage++;
			go = true;
		} else {
			currentPage = totalPages;
		}
		return go;
	}
	
	private boolean previousPage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		boolean go = false;
		if(Validator.lessThanZero(currentPage) 
				&& Validator.lessThanZeroOrEqualZero(previousLimit)) {
			currentPage = FIRST_PAGE;
			previousLimit = ZERO_LIMIT;
		} else {
			previousLimit = previousLimit - limit;
			currentPage--;
			go = true;
		}
		return go;
	}
	
	private boolean goToPage(HttpServletRequest request) {
		int number = Integer.parseInt(request.getParameter(PAGINATION_PARAMETER_GO_TO));
		currentPage = number + CORRECTION_PAGE;
		previousLimit = number * limit;
		return true;
	}
	
	private void countTotalPages(List<Payment> allPayments) {
		if(allPayments.size() % limit == ZERO_REMINDER) {
			totalPages = allPayments.size() / limit;
		} else {
			totalPages = allPayments.size() / limit + CORRECTION_PAGE;
		}
	}

	private List<Integer> showPageNumbers() {
		List<Integer> pageNumbers = new ArrayList<Integer>();
		int firstNumber;
		int lastNumber;
		if(totalPages <= visiblePageNumbers) {
			firstNumber = FIRST_PAGE;
			lastNumber = totalPages;
		} else {
			firstNumber = defineFirstNumber();
			lastNumber = firstNumber + visiblePageNumbers;
		}
		
		for(int i = firstNumber; i <= lastNumber; i++) {
			pageNumbers.add(i);
		}
		return pageNumbers;
	}
	
	private int defineFirstNumber() {
		int firstNumber;
		if(currentPage < MIN_MIDDLE_PAGE_NUMBER) {
			firstNumber = FIRST_PAGE;
		} else {
			firstNumber = currentPage - MIN_MIDDLE_PAGE_NUMBER;
		}
		return firstNumber;
	}
}
