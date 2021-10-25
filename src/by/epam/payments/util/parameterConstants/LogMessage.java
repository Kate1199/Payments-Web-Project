package by.epam.payments.util.parameterConstants;

public class LogMessage {
	
	public static final String APPLICATION_INITIALIZED = "Application initialized";
	public static final String APPLICATION_STOPPED = "Application stopped";
	
	public static final String СONNECTION_POOL_CREATED = "ConnectionPool created";
	public static final String CONNECTION_RECIEVED = "Connection for database taken from connection pool";
	public static final String CONNECTION_CLOSED = "Connection returned to connection pool";
	
	public static final String ACCOUNT_UPDATE = "Attempt to update account information for account with id: ";
	public static final String CARD_UPDATE = "Attempt to update card information for account with id: ";
	
	public static final String REDIRECT_FAILED = "Redirect command failed for the reason: ";
	public static final String POST_COMMAND_FAILED = "Command in method post failed for the reason: ";
	
	public static final String ADMIN_LOGGED_IN = "Admin logged with id: ";
	public static final String UNKNOWN_USER = "Unknown user trying to login";
	public static final String INVALID_PASSWORD = "Invalid password entered for user with id: ";
	
	public static final String NEW_USER = "New user registarted with id and role: ";
	
	public static final String INCORRECT_CLIENT_DATA = "Attempt to enter invalid client data";
	
	public static final String SENDER_ACCOUNT_IS_EMPTY = "Sender account is empty";
	public static final String SENDER_ACCOUNT_IS_EMPTY_RU = "Счёт для операции не выбран";
}
