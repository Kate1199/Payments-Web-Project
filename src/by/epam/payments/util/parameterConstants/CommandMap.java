package by.epam.payments.util.parameterConstants;

import java.util.HashMap;

import by.epam.payments.service.command.CashInCommand;
import by.epam.payments.service.command.ClientFormCommand;
import by.epam.payments.service.command.LoginCommand;
import by.epam.payments.service.command.PaymentsCommand;
import by.epam.payments.service.command.RegistrationCommand;
import by.epam.payments.service.command.SendAccountCommand;
import by.epam.payments.service.command.ServletCommand;
import by.epam.payments.util.validation.Validator;

public class CommandMap {
	
	private static HashMap<String, ServletCommand> commands = new HashMap<String, ServletCommand>();
	private static CommandMap instanse;
	
	private CommandMap() {
		commands.put(PageName.REGISTRATION, new RegistrationCommand());
		commands.put(PageName.LOGIN, new LoginCommand());
		commands.put(PageName.PAYMENTS, new PaymentsCommand());
		commands.put(PageName.CLIENT_FORM, new ClientFormCommand());
		commands.put(PageName.SEND_ACCOUNT, new SendAccountCommand());
		commands.put(PageName.CASH_IN, new CashInCommand());
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
