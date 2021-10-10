package by.epam.kisel.dao.connection;

import java.util.ResourceBundle;

public class DBResourseManager {
	
private static final DBResourseManager instance = new DBResourseManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle("by.epam.kisel.dao.connection.db");
	
	public static DBResourseManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}
