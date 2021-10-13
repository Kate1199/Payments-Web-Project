package by.epam.kisel.dao.builders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import by.epam.kisel.bean.Entity;
import by.epam.kisel.exception.DAOException;

public interface EntityBuilder<T extends Entity> {
	
	public T makeEntity(ResultSet resultSet) throws DAOException;
	public boolean putTo(List<T> list, T entity);
	public boolean transmitEntity(PreparedStatement preparedStatement, T entity)
			throws DAOException;
	public List<T> getListOfEntities();
}
