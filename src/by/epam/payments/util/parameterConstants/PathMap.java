package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.exception.ServiceException;


public class PathMap {
	
	private static HashMap<String, String> path = new HashMap<String, String>();
	
	public PathMap(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		path.put(AttributeValue.REGISTRATION, Path.REGISTRATION_PATH);
		path.put(AttributeValue.LOGIN, Path.LOGIN_PATH);
		path.put(AttributeValue.PAYMENTS, Path.PAYMENTS_PATH);
		path.put(AttributeValue.ABOUT_US, Path.ABOUT_US);
		path.put(AttributeValue.EXCHANGE_RATES, Path.EXCHANGE_RATES_PATH);
		path.put(AttributeValue.BANKS_PARTNERS, Path.BANKS_PARTNERS_PATH);
		path.put(AttributeValue.CONTACTS, Path.CONTACTS_PATH);
		path.put(AttributeValue.CASH_IN, Path.CASH_IN_PATH);
	}
	
	public String getPath(String name) {
		return path.get(name);
	}
}
