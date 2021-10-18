package by.epam.payments.util.parameterConstants;

public class SqlRequest {
	
	
	/*
	 * For user
	 */
	public static final String FIND_ID_BY_LOGIN = "SELECT user_id FROM users WHERE hidden = 0 and login = ?";
	
	/*
	 * For client
	 */
	public static final String FIND_CLIENT_BY_USER_ID = "SELECT * FROM clients WHERE Users_user_id = ?";
	public static final String FIND_CLIENT_ID_BY_USER_ID = "SELECT client_id FROM clients WHERE hidden = 0 AND user_id = ?";
	public static final String FIND_CLIENT_BY_IDENTIFIACTION_NUMBER = "SELECT * FROM clients WHERE hidden = 0 AND personal_identification_number = ?";
	public static final String FIND_ALL_CLIENTS = "SELECT * FROM clients WHERE hidden = 0";
	public static final String ADD_CLIENT = "INSERT INTO clients (client_id, personal_identification_number, "
			+ "surname, name, patronymic, phone_number, registration_address, real_address, Users_user_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String FIND_CLIENT_BY_ID = "SELECT * FROM clients WHERE client_id = ?";
	
	/*
	 * For bank
	 */
	public static final String FIND_BANK_NAME_BY_ID = "SELECT bank_id FROM banks WHERE hidden = 0 AND bank_id = ?";
	
	/*
	 * For account
	 */
	public static final String FIND_ALL_ACCOUNTS = "SELECT * FROM accounts WHERE hidden = 0";
	public static final String ADD_ACCOUNT = "INSERT INTO accounts account_id, account_IBAN_number, "
			+ "currency, balance, Cients_client_id, Bank_departents_department_id VALUES(?, ?, ?, ?, ?, ?)";
	public static final String FIND_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE hidden = 0 AND account_id = ?";
	public static final String FIND_ACCOUNT_BY_CLIENT_ID = "SELECT * FROM accounts WHERE hidden = 0 AND Clients_client_id = ?";
	public static final String GET_BALANCE_BY_ACCOUNT_NUMBER = "SELECT balnce FROM accounts WHERE hidden = 0 AND account_IBAN_number = ?";	
	public static final String UPDATE_ACCOUNT_BALANCE = "UPDATE accounts SET balance = ? WHERE hidden = 0 AND account_IBAN_number = ?";
	public static final String HIDE_ACCOUNT = "UPDATE accounts SET hidden = 1 WHERE account_IBAN_number = ? "
			+ "AND currency = ? AND  balance = ? AND Clients_client_id = ? "
			+ "AND Bank_departments_department_id = ?";
	public static final String HIDE_ACCOUNT_BY_ID = "UPDATE accounts SET hidden = 1 WHERE account_id = ?";
	
	/*
	 * For card
	 */
	public static final String FIND_CARD_BY_ACCOUNT_ID = "SELECT * FROM cards WHERE hidden = 0 AND Accounts_account_id = ?";
	public static final String FIND_ALL_CARDS = "SELECT * FROM cards WHERE hidden = 0";
	public static final String ADD_CARD = "INSERT INTO cards card_id, card_number, card_start_digits, "
			+ "card_salt, validity_period, Accounts_account_id VALUES(?, ?, ?, ?, ?, ?)";
	public static final String FIND_CARD_BY_ID = "SELECT * FROM cards WHERE hidden = 0 AND card_id = ?";
	public static final String HIDE_CARD = "UPDATE cards SET hidden = 1 WHERE card_number = ? "
			+ "AND card_start_digits = ? AND card_end_digits = ? AND card_salt = ? AND validity_period = ? "
			+ "AND Accounts_account_id = ?";
	public static final String HIDE_CARD_BY_ID = "UPDATE cards SET hidden = 1 WHERE card_id = ?";
}