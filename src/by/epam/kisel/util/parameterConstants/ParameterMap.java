package by.epam.kisel.util.parameterConstants;

import java.util.HashMap;

import by.epam.kisel.util.validation.Validator;

public final class ParameterMap {
	
	private static HashMap<String, String> parameters = new HashMap<String, String>();
	private static ParameterMap instanse;
	
	private ParameterMap() {
		parameters.put(ParameterName.LOGIN, ParameterPattern.LOGIN);
		parameters.put(ParameterName.EMAIL, ParameterPattern.EMAIL);
		parameters.put(ParameterName.PASSWORD, ParameterPattern.PASSWORD);
		parameters.put(ParameterName.IDENTIFIACTION_NUMBER, ParameterPattern.IDENTIFIACTION_NUMBER);
		parameters.put(ParameterName.FIRST_NAME, ParameterPattern.NAME);
		parameters.put(ParameterName.LAST_NAME, ParameterPattern.NAME);
		parameters.put(ParameterName.PATRONYMIC, ParameterPattern.PATRONYMIC);
		parameters.put(ParameterName.PHONE_NUMBER, ParameterPattern.PHONE_NUMBER);
		parameters.put(ParameterName.REGISTRATION_ADDRESS, ParameterPattern.ADDRESS);
		parameters.put(ParameterName.REAL_ADDRESS, ParameterPattern.ADDRESS);
	}
	
	public static synchronized ParameterMap getInstanse() {
		if(Validator.isNull(instanse)) {
			instanse = new ParameterMap();
		}
		
		return instanse;
	}
	
	public String getPattern(String name) {
		return parameters.get(name);
	}
}
