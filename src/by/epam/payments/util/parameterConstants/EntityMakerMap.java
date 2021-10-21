package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.service.entityMaker.EntityMakerFromRequest;
import by.epam.payments.service.entityMaker.UserMaker;

public final class EntityMakerMap {
	
	private HashMap<String, EntityMakerFromRequest> makers;
	
	public EntityMakerMap(HttpServletRequest request, HttpServletResponse response) {
		makers.put(AttributeValue.REGISTRATION, new UserMaker(request, response));
	}
	
	public EntityMakerFromRequest getMaker(String name) {
		return makers.get(name);
	}
}
