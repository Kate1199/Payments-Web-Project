package by.epam.payments.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;

import by.epam.payments.bean.Entity;
import by.epam.payments.controller.Controller;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.validation.Validator;

public class Pagination<T extends Entity> {
	
	private static final String PAGINATION_PARAMETER = "pagination";
	private static final String PAGINATION_PARAMETER_PREVIOUS = "previous";
	private static final String PAGINATION_PARAMETER_NEXT = "next";
	private static final String PAGINATION_PARAMETER_GO_TO = "goTo";
	private static final String PAGE = "currentPage";
	private static final String TOTAL_PAGES = "totalPages";
	private static final String PAGE_NUMBERS = "pageNumbers";
	
	private static final int FIRST_PAGE = 1;
	private static final int CORRECTION_PAGE = 1;
	private static final int MIN_MIDDLE_PAGE_NUMBER = 3;
	private static final int ZERO_REMINDER = 0;
	private static final int ZERO_LIMIT = 0;
	private static final int VISIBLE_PAGE_NUMBERS = 5;
	
	private int previousLimit;
	private int limit;
	private int currentPage;
	private int totalPages;
	
	private HttpServletRequest request;
	
	public Pagination() {
	}
	
	public Pagination(int limit, HttpServletRequest request) {
		this.limit = limit;
		this.request = request;
		defineCurrentPage();
	}
	
	private boolean defineCurrentPage() {
		HttpSession session = request.getSession();
		Object currentPageAttribute = session.getAttribute(PAGE);
		if(Validator.isNull(currentPageAttribute)) {
			currentPage = FIRST_PAGE;
		} else {
			currentPage = (int) currentPageAttribute;
		}
		return true;
	}
	
	public boolean output(List<T> allEntities) throws ServiceException {
		
		boolean output = true;
		
		countTotalPages(allEntities);
		String pagiantion = request.getParameter(PAGINATION_PARAMETER);
		definePage(pagiantion);
		int endIndex = makeEndIndex(allEntities.size());
		List<T> onePageEntities = allEntities.subList(previousLimit, endIndex);
		
		HttpSession session = request.getSession();
		session.setAttribute(PAGE, currentPage);
		request.setAttribute(TOTAL_PAGES, totalPages);
		request.setAttribute(ParameterName.ENTITIES, onePageEntities);
		request.setAttribute(PAGE_NUMBERS, showPageNumbers());
		
		return output;
		
	}
	
	private boolean definePage(String pagiantion) throws ServiceException {
		boolean page = true;
		if(Validator.isNull(pagiantion)) {
			page = false;
		} else if(pagiantion.equals(PAGINATION_PARAMETER_NEXT)) {
			nextPage();
		} else if(pagiantion.equals(PAGINATION_PARAMETER_PREVIOUS)) {
			previousPage();
		} else if(pagiantion.equals(PAGINATION_PARAMETER_GO_TO)) {
			goToPage();
		}
		return page;
	}
	
	private boolean nextPage() {
		
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
	
	private boolean previousPage() throws ServiceException {
		
		boolean go = false;
		if(currentPage < 0 && previousLimit <= 0) {
			currentPage = FIRST_PAGE;
			previousLimit = ZERO_LIMIT;
		} else {
			currentPage--;
			previousLimit = (currentPage - CORRECTION_PAGE) * limit;
			go = true;
		}
		return go;
	}
	
	private boolean goToPage() {
		int number = Integer.parseInt(request.getParameter(PAGINATION_PARAMETER_GO_TO));
		currentPage = number + CORRECTION_PAGE;
		previousLimit = number * limit;
		return true;
	}
	
	private int makeEndIndex(int listLength) {
		int endIndex = previousLimit + limit;
		
		if(endIndex >= listLength) {
			endIndex = listLength;
		}
		return endIndex;
	}
	
	private void countTotalPages(List<T> allEntities) {
		if(allEntities.size() % limit == ZERO_REMINDER) {
			totalPages = allEntities.size() / limit;
		} else {
			totalPages = allEntities.size() / limit + CORRECTION_PAGE;
		}
	}

	private List<Integer> showPageNumbers() {
		List<Integer> pageNumbers = new ArrayList<Integer>();
		int firstNumber;
		int lastNumber;
		if(totalPages <= VISIBLE_PAGE_NUMBERS) {
			firstNumber = FIRST_PAGE;
			lastNumber = totalPages;
		} else {
			firstNumber = defineFirstNumber();
			lastNumber = firstNumber + VISIBLE_PAGE_NUMBERS;
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
