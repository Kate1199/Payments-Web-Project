package by.epam.kisel.util.parameterConstants;

import java.util.HashMap;

import by.epam.kisel.service.ClientFormCommand;
import by.epam.kisel.service.LoginCommand;
import by.epam.kisel.service.PaginationCommand;
import by.epam.kisel.service.RegistrationCommand;
import by.epam.kisel.service.ServletCommand;
import by.epam.kisel.util.validation.Validator;

public class CommandMap {
	
	private static HashMap<String, ServletCommand> commands = new HashMap<String, ServletCommand>();
	private static CommandMap instanse;
	
	private CommandMap() {
		commands.put(AttributeValue.REGISTRATION, new RegistrationCommand());
		commands.put(AttributeValue.LOGIN, new LoginCommand());
		commands.put(AttributeValue.PAYMENTS, new PaginationCommand());
		commands.put(AttributeValue.CLIENT_FORM, new ClientFormCommand());
	}
	
	public static synchronized CommandMap getInstanse() {
		if(Validator.isNull(instanse)) {
			instanse = new CommandMap();
		}
		return instanse;
	}
	
	public ServletCommand getCommand(String key) {
		return commands.get(key);
	}
}
