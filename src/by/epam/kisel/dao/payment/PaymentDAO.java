package by.epam.kisel.dao.payment;

import java.util.List;

import by.epam.kisel.dao.Dao;
import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Payment;

public interface PaymentDAO extends Dao<Integer, Payment>{
	
	public Payment findByName(String name) throws DAOException;
	public List<Payment> findSomeFist(int number);
}
