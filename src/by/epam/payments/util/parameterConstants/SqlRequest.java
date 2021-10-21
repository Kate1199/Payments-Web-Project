package by.epam.payments.util.parameterConstants;

public class SqlRequest {
	
	
	/*
	 * For user
	 */
	public static final String FIND_ID_BY_LOGIN = "SELECT user_id FROM users WHERE hidden = 0 and login = ?";
	public static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE hidden = 0 AND login = ?";
	public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE hidden = 0 AND email = ?";
	public static final String FIND_ALL_USERS = "SELECT * FROM users WHERE hidden = 0";
	public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE hidden = 0 user_id = ?";
	public static final String ADD_USER = "INSERT INTO users (user_id, login, email, password, user_salt, role) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_USER = "UPDATE users SET login = ?, email = ?, password = ?, "
			+ "user_salt = ?, role = ? WHERE hidden = 0 AND user_id = ?";
	public static final String HIDE_USER = "UPDATE users set hidden = 1 WHERE login = ? AND email = ? "
			+ "AND password = ? AND user_salt = ? AND role = ?";
	public static final String HIDE_USER_BY_ID = "UPDATE users SET hidden = 1 WHERE user_id = ?";
	
	/*
	 * For client
	 */
	public static final String FIND_CLIENT_BY_USER_ID = "SELECT * FROM clients WHERE Users_user_id = ?";
	public static final String FIND_CLIENT_ID_BY_USER_ID = "SELECT client_id FROM clients WHERE hidden = 0 AND user_id = ?";
	public static final String FIND_CLIENT_BY_IDENTIFIACTION_NUMBER = "SELECT * FROM clients WHERE hidden = 0 AND personal_identification_number = ?";
	public static final String FIND_CLIENT_BY_IDENTIFIACTION_NUMBER_AND_PHONE_NUMBER = "SELECT * FROM clients "
			+ "WHERE hidden = 0 AND personal_identification_number = ? or phone_number = ?";
	public static final String FIND_ALL_CLIENTS = "SELECT * FROM clients WHERE hidden = 0";
	public static final String ADD_CLIENT = "INSERT INTO clients (client_id, personal_identification_number, "
			+ "surname, name, patronymic, phone_number, registration_address, real_address, Users_user_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String FIND_CLIENT_BY_ID = "SELECT * FROM clients WHERE client_id = ?";
	public static final String UPDATE_CLIENT = "UPDATE clients SET personal_identification_number = ?, "
			+ "surname = ?, name = ?, patronymic = ?, phone_number = ?, registration_address = ?, "
			+ "real_address = ?, Users_user_id = ? WHERE hidden = 0 AND client_id = ?";
	public static final String HIDE_CLIENT = "UPDATE clients SET hidden = 1 WHERE personal_identification_number = ? AND"
			+ " surname = ? AND name = ? AND patronymic = ? AND phone_number = ? AND registration_address = ? AND "
			+ "real_address = ? AND Users_user_id = ?";
	public static final String HIDE_CLIENT_BY_ID = "UPDATE clients SET hidden = 1 WHERE client_id = ?";
	
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
	public static final String ADD_CARD = "INSERT INTO cards (card_id, card_number, card_start_digits, "
			+ "card_salt, validity_period, Accounts_account_id) VALUES(?, ?, ?, ?, ?, ?)";
	public static final String FIND_CARD_BY_ID = "SELECT * FROM cards WHERE hidden = 0 AND card_id = ?";
	public static final String HIDE_CARD = "UPDATE cards SET hidden = 1 WHERE card_number = ? "
			+ "AND card_start_digits = ? AND card_end_digits = ? AND card_salt = ? AND validity_period = ? "
			+ "AND Accounts_account_id = ?";
	public static final String HIDE_CARD_BY_ID = "UPDATE cards SET hidden = 1 WHERE card_id = ?";
	
	/*
	 * For payments
	 */
	public static final String FIND_ALL_PAYMENTS_WHITH_LIMIT = "SELECT * FROM payments WHERE hidden = 0 ORDER BY payment_id LIMIT ?,?";
	public static final String FIND_ALL_PAYMENTS = "SELECT * FROM payments WHERE hidden = 0";
	public static final String FIND_PAYMENT_BY_NAME = "SELECT * FROM payments WHERE hidden = 0 AND payment_name = ?";
	public static final String FIND_PAYMENT_BY_ID = "SELECT * FROM payments WHERE hidden = 0 AND payment_id = ?";
	public static final String ADD_PAYMENT = "INSERT INTO payments (payment_name, image, reciever, "
			+ "payment_details, description, fixed_amount, procent_fee, Accounts_account_id) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_PAYMENT = "UPDATE payments SET payment_name = ?, image = ?, reciever = ?,"
			+"payment_details = ?, description = ?, fixed_amount = ?, procent_fee = ?, "
			+ "Accounts_account_id = ? WHERE hidden = 0 AND payment_id = ?";
	public static final String HIDE_PAYMENT = "UPDATE payments SET hidden = 1 WHERE payment_name = ? AND "
			+ "image = ? AND reciever = ? AND payment_details = ? AND description = ? AND fixed_amount = ?"
			+ " AND procent_fee = ? AND Accounts_account_id = ?";
	public static final String HIDE_PAYMENT_BY_ID = "UPDATE payment SET hidden = 1 WHERE payment_id = ?";
}
