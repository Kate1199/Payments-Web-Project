package by.epam.payments.dao.payment;

import java.util.List;

import by.epam.payments.bean.Payment;
import by.epam.payments.dao.Dao;
import by.epam.payments.exception.DAOException;

public interface PaymentDAO extends Dao<Payment>{
	
	public Payment findByName(String name) throws DAOException;
	public List<Payment> findSomeFist(int number);
}
