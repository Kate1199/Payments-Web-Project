package by.epam.payments.service.command;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.payments.bean.Account;
import by.epam.payments.bean.Client;
import by.epam.payments.dao.account.AccountDaoImpl;
import by.epam.payments.dao.client.ClientDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.Page;
import by.epam.payments.service.Pagination;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;

public class ShowClientCommand implements ServletCommand {
	
	private static final int LIMIT = 2; 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		List<Account> accounts = takeAccounts();
		boundClientAndAccount(accounts);
		request.setAttribute(ParameterName.ACCOUNTS, accounts);
		Pagination<Account> pagination = new Pagination<Account>(LIMIT, request);
		pagination.output(accounts);
		Page.forward(request, response, Path.CLIENT_LIST_PATH);
	}
	
	private List<Account> takeAccounts() throws ServiceException {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		List<Account> accounts;
		try {
			accounts = accountDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return accounts;
	}
	
	private boolean boundClientAndAccount(List<Account> accounts) throws ServiceException {
		boolean bound = false;

		for (Account account : accounts) {
			bound = true;
			Client client = takeClient(account.getClientId());
			account.setClient(client);
		}
		return bound;
	}
	
	private Client takeClient(int clientId) throws ServiceException {
		ClientDaoImpl clientDao = new ClientDaoImpl();
		Client client = new Client();
		try {
			client = clientDao.findEntityById(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return client;
	}
}
