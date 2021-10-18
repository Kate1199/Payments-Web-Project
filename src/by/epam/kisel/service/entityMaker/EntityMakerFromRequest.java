package by.epam.kisel.service.entityMaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.payments.bean.Entity;

public interface EntityMakerFromRequest<T extends Entity> {

	public T makeEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException;
}
