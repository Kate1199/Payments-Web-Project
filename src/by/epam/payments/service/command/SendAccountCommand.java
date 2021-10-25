package by.epam.payments.service.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Account;
import by.epam.payments.dao.account.AccountDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;

public class SendAccountCommand implements ServletCommand {
	
	private static Logger logger = LogManager.getLogger();
	
	public SendAccountCommand() {
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String accountIban = request.getParameter(ParameterName.ACCOUNT_IBAN);
		AccountDaoImpl accountDao = new AccountDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute(ParameterName.ACCOUNT_IBAN, accountIban);
		int clientId = (int) session.getAttribute(ParameterName.CLIENT_ID);
		List<Account> otherAccounts =  new ArrayList<Account>();
		try {
			otherAccounts = accountDao.findAllExceptCurrent(clientId, accountIban);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		session.setAttribute(ParameterName.ACCOUNTS, otherAccounts);
		
		try {
			request.getRequestDispatcher(Path.CASH_IN_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());		
		}
	}
	

}
