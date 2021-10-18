package by.epam.payments.service.entityMaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.bean.Entity;
import by.epam.payments.exception.IncorrectEnteredDataException;
import by.epam.payments.exception.ServiceException;

public interface EntityMakerFromRequest<T extends Entity> {

	public T makeEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, IncorrectEnteredDataException;
}
