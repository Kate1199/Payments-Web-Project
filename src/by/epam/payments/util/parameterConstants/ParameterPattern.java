package by.epam.payments.util.parameterConstants;

public final class ParameterPattern {
	
	/*
	 * Parameters for user
	 */
	public static final String LOGIN = "\\w+";
	public static final String EMAIL = "[a-zA-z_\u002E\\d*]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+";
	public static final String PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^@#$%]).{8,20})";
	
	/*
	 * Parameters for client
	 */
	public static final String IDENTIFIACTION_NUMBER = "[A-Za-z0-9]{5,20}";
	public static final String NAME = "^[A-ZА-Я]{1}[a-zа-я]*[\\s-]*[a-zа-я]+";
	public static final String PATRONYMIC = "[А-ЯA-Z]{1}[a-zа-я]+";
	public static final String PHONE_NUMBER = "\\u002B{1}[0-9]{7,}";
	public static final String ADDRESS = "[A-Za-zА-Яа-я0-9\\\\.\\,\\s]{10,}";
}
