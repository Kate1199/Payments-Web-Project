package payments;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.UserDataHandler;

import by.epam.kisel.bean.User;
import by.epam.kisel.exception.DAOException;

public class testDAO {

	private Connection connection;
	private HashMap<Integer, User> users;
	private User admin = new User(1, "admin", "admin@mail.ru", "654321", false);
	private User user1 = new User(2, "user1", 375447748956L, "user1@mail.ru", "654321", true);
	private User user2 = new User(3, "user2", 375258974525L, "user2@mail.ru", "654321", true);
	private UserDAOforDB<Integer, User> dao;
	
	@Before
	public void setUp() {
		users = new HashMap<Integer, User>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
					"root", "654321");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testReadAll() {
		users.put(1, admin);
		users.put(2, user1);
		HashMap<Integer, User> actual = null;
		try {
			actual = dao.readAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(users, actual);
	}
	
	@Test
	public void testReadBy() {
		User expected = admin;
		User actual = null;
		try {
			actual = dao.readBy(1);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
	
	@Test
	public void testWrite() {
		boolean expected = true;
		boolean actual = false;
		try {
			actual = dao.write(user2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
}
