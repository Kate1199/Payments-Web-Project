package by.epam.kisel.dao.payment;

import by.epam.kisel.bean.Payment;
import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;

public interface PaymentDAO extends Dao<Integer, Payment>{
	
	public Payment findByName(String name) throws DAOException;
}
