package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import by.epam.payments.service.ClientFormCommand;
import by.epam.payments.service.LoginCommand;
import by.epam.payments.service.PaymentsCommand;
import by.epam.payments.service.RegistrationCommand;
import by.epam.payments.service.ServletCommand;
import by.epam.payments.util.validation.Validator;

public class CommandMap {
	
	private static HashMap<String, ServletCommand> commands = new HashMap<String, ServletCommand>();
	private static CommandMap instanse;
	
	private CommandMap() {
		commands.put(AttributeValue.REGISTRATION, new RegistrationCommand());
		commands.put(AttributeValue.LOGIN, new LoginCommand());
		commands.put(AttributeValue.PAYMENTS, new PaymentsCommand());
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
