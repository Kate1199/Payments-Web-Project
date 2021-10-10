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
