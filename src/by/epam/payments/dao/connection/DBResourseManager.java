package by.epam.payments.dao.connection;

import java.util.ResourceBundle;

public class DBResourseManager {
	
private static final DBResourseManager instance = new DBResourseManager();

	private static final String PROPERTY_FILE_PATH = "by.epam.payments.dao.connection.db";
	
	private ResourceBundle bundle = ResourceBundle.getBundle(PROPERTY_FILE_PATH);
	
	public static DBResourseManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}
