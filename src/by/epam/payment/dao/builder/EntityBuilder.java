package by.epam.payment.dao.builder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import by.epam.payment.bean.Entity;
import by.epam.payment.exception.DAOException;

public interface EntityBuilder<T extends Entity> {
	
	public boolean transmitEntity(PreparedStatement preparedStatement, T entity)
			throws DAOException;
	public List<T> getListOfEntities(ResultSet resultSet) throws DAOException;
}
