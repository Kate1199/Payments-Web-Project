package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import by.epam.payments.service.pageChange.HomePageChange;
import by.epam.payments.service.pageChange.PageChange;
import by.epam.payments.util.validation.Validator;

public class RedirectMap {
	
	private static RedirectMap instanse;
	
	private HashMap<String, PageChange> pages = new HashMap<String, PageChange>();
	
	private RedirectMap() {
		pages.put(AttributeValue.HOME, new HomePageChange());
	}
	
	public static RedirectMap getInstanse() {
		if(Validator.isNull(instanse)) {
			instanse = new RedirectMap();
		}
		return instanse;
	}
	
	public PageChange getPage(String key) {
		return pages.get(key);
	}
}
