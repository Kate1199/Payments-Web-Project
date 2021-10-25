package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.exception.ServiceException;


public class PathMap {
	
	private static HashMap<String, String> path = new HashMap<String, String>();
	
	public PathMap(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		path.put(PageName.REGISTRATION, Path.REGISTRATION_PATH);
		path.put(PageName.LOGIN, Path.LOGIN_PATH);
		path.put(PageName.PAYMENTS, Path.PAYMENTS_PATH);
		path.put(PageName.ABOUT_US, Path.ABOUT_US);
		path.put(PageName.CONTACTS, Path.CONTACTS_PATH);
		path.put(PageName.CASH_IN, Path.CASH_IN_PATH);
	}
	
	public String getPath(String name) {
		return path.get(name);
	}
}
