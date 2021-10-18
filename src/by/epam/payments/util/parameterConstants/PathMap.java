package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import by.epam.payments.util.validation.Validator;

public class PathMap {
	
	private static HashMap<String, String> path = new HashMap<String, String>();
	
	private static PathMap instanse;
	
	private PathMap() {
		path.put(AttributeValue.HOME, Path.HOME_PATH);
		path.put(AttributeValue.REGISTRATION, Path.REGISTRATION_PATH);
		path.put(AttributeValue.LOGIN, Path.LOGIN_PATH);
		path.put(AttributeValue.PAYMENTS, Path.PAYMENTS_PATH);
		path.put(AttributeValue.PROFILE, Path.PROFILE_PATH);
		path.put(AttributeValue.CLIENT_FORM, Path.CLIENT_FORM_PATH);
		path.put(AttributeValue.ABOUT_US, Path.ABOUT_US);
		path.put(AttributeValue.EXCHANGE_RATES, Path.EXCHANGE_RATES_PATH);
		path.put(AttributeValue.BANKS_PARTNERS, Path.BANKS_PARTNERS_PATH);
		path.put(AttributeValue.CONTACTS, Path.CONTACTS_PATH);
		path.put(AttributeValue.CASH_IN, Path.CASH_IN_PATH);
	}
	
	public static synchronized PathMap getInstanse() {
		if(Validator.isNull(instanse)) {
			instanse = new PathMap();
		}
		return instanse;
	}
	
	public String getPath(String name) {
		return path.get(name);
	}
}
