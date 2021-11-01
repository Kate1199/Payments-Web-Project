package by.epam.payment.service.entityMaker;

import by.epam.payment.bean.Entity;
import by.epam.payment.exception.ServiceException;

public interface EntityMakerFromRequest {

	public Entity makeEntity() throws ServiceException;
}
