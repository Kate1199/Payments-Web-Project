package by.epam.payments.service.entityMaker;

import by.epam.payments.bean.Entity;
import by.epam.payments.exception.ServiceException;

public interface EntityMakerFromRequest {

	public Entity makeEntity() throws ServiceException;
}
