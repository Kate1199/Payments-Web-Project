package by.epam.kisel.util.parameterConstants;

public class SqlRequest {
	
	
	/*
	 * For user
	 */
	public static final String FIND_ID_BY_LOGIN = "SELECT user_id FROM users WHERE login = ?";
	
	/*
	 * For client
	 */
	public static final String FIND_CLIENT_ID_BY_USER_ID = "SELECT client_id FROM clients WHERE user_id = ?";
	public static final String FIND_CLIENT_BY_IDENTIFIACTION_NUMBER = "SELECT * FROM clients WHERE personal_identification_number = ?";
	public static final String ADD_CLIENT = "INSERT INTO clients VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	/*
	 * For bank
	 */
	public static final String FIND_BANK_NAME_BY_ID = "SELECT bank_id FROM banks WHERE bank_id = ?";
	
	/*
	 * For account
	 */
	public static final String FIND_ACCOUNT_BY_CLIENT_ID = "SELECT * FROM accounts WHERE Clients_client_id = ?";
	public static final String GET_BALANCE_BY_ACCOUNT_NUMBER = "SELECT balnce FROM accounts WHERE account_IBAN_number = ?";	
	public static final String UPDATE_ACCOUNT_BALANCE = "UPDATE accounts SET balance = ? WHERE accoun_IBAN_number = ?";
	
	/*
	 * For card
	 */
	public static final String FIND_CARD_BY_ACCOUNT_ID = "SELECT * FROM cards WHERE Accounts_account_id = ?";
	
}
