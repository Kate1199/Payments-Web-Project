package by.epam.kisel.util.parameterConstants;

public final class ParameterPattern {
	
	/*
	 * Parameters for user
	 */
	public static final String LOGIN = "\\w+";
	public static final String EMAIL = "[a-zA-z_\u002E\\d*]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+";
	//TODO:change pattern
	public static final String PASSWORD = ".{8,}";
	
	/*
	 * Parameters for client
	 */
	public static final String PHONE_NUMBER = "(\u002B){1}[\\d]{10,}";
	public static final String NAME = "^([A-Z]?|[А-Я]?)([a-z]*|[а-я]*)[\\s-]*([a-z]+|[а-я]+)";
	public static final String IDENTIFIACTION_NUMBER = "[a-zA-z\\d]{5,}";
	public static final String ADDRESS = "[^<>?@#$%&*()=+]";
}
