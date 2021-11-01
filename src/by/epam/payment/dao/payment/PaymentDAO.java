package by.epam.payment.dao.payment;

import java.util.List;

import by.epam.payment.bean.Payment;
import by.epam.payment.dao.Dao;
import by.epam.payment.exception.DAOException;

public interface PaymentDAO extends Dao<Payment>{
	
	public Payment findByName(String name) throws DAOException;
	public List<Payment> findAllWithLimits(int previousLimit, int limit) throws DAOException;
}
