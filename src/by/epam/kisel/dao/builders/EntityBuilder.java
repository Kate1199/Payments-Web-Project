package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import by.epam.kisel.exception.DAOException;
import by.epam.payments.bean.Entity;

public interface EntityBuilder<T extends Entity> {
	
	public boolean transmitEntity(PreparedStatement preparedStatement, T entity)
			throws DAOException;
	public List<T> getListOfEntities(ResultSet resultSet) throws DAOException;
}
