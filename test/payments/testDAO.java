package payments;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.dao.builders.UserBuilder;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.service.encrytion.Encrypter;
import by.epam.payments.bean.Role;
import by.epam.payments.bean.User;

public class testDAO {
	
	private HashMap<Integer, User> users;
	private byte[] password = {15, -67, -85, -45};
	private byte[] salt = Encrypter.generateSalt();
	private User admin = new User(1, "admin", "admin@mail.ru", password, salt, Role.ADMIN);
	private User user1 = new User(2, "user1", "user1@mail.ru", password, salt, Role.USER);
	private User bank = new User(3, "bank", "bank@mail.ru", password, salt, Role.BANK);
	private SqlDatabaseDAO<User> dao;
	private Connection connection;
	
	private String findAll = "SELECT * FROM users";
	private String insert = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";
	
	@Before
	public void setUp() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "654321");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao = new SqlDatabaseDAO<User>(connection);
	}
}
