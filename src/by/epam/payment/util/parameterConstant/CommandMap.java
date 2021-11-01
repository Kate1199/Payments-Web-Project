package by.epam.payment.util.parameterConstant;

import java.util.HashMap;

import by.epam.payment.service.command.BecomeAdminCommand;
import by.epam.payment.service.command.CashInCommand;
import by.epam.payment.service.command.ClientFormCommand;
import by.epam.payment.service.command.LoginCommand;
import by.epam.payment.service.command.PaymentCommand;
import by.epam.payment.service.command.PotentialAdminCommand;
import by.epam.payment.service.command.RegistrationCommand;
import by.epam.payment.service.command.SendAccountCommand;
import by.epam.payment.service.command.ServletCommand;
import by.epam.payment.service.command.ShowClientCommand;
import by.epam.payment.util.validation.Validator;

public class CommandMap {
	
	private static HashMap<String, ServletCommand> commands = new HashMap<String, ServletCommand>();
	private static CommandMap instanse;
	
	private CommandMap() {
		commands.put(PageName.REGISTRATION, new RegistrationCommand());
		commands.put(PageName.LOGIN, new LoginCommand());
		commands.put(PageName.PAYMENTS, new PaymentCommand());
		commands.put(PageName.CLIENT_FORM, new ClientFormCommand());
		commands.put(PageName.SEND_ACCOUNT, new SendAccountCommand());
		commands.put(PageName.CASH_IN, new CashInCommand());
		commands.put(PageName.BECOME_ADMIN, new BecomeAdminCommand());
		commands.put(PageName.SHOW_CLIENTS, new ShowClientCommand());
		commands.put(PageName.POTENTIAL_ADMINS, new PotentialAdminCommand());
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
