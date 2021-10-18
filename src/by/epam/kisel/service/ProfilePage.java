package by.epam.kisel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.kisel.dao.account.AccountDaoImpl;
import by.epam.kisel.dao.card.CardDaoImpl;
import by.epam.kisel.dao.client.ClientDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.util.parameterConstants.ParameterName;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Account;
import by.epam.payments.bean.Card;
import by.epam.payments.bean.Client;

public class ProfilePage {
	
	private HttpSession session;
	
	public ProfilePage(HttpSession session) {
		this.session = session;
	}
	
	public boolean outputClientInformation(HttpServletRequest request) throws ServiceException {
		if(Validator.isNull(request)) {
			return false;
		}
		
		boolean output = true;
		int userId = (int) session.getAttribute(ParameterName.USER_ID);
		ClientDaoImpl clientDao = new ClientDaoImpl();
		AccountDaoImpl accountDao = new AccountDaoImpl();
		CardDaoImpl cardDao = new CardDaoImpl();
		try {
			Client client = clientDao.findClientByUserId(userId);
			request.setAttribute(ParameterName.CLIENT, client);
			List<Account> accounts = accountDao.takeAccountByClientId(client.getId());
			for(Account account : accounts) {
				List<Card> cards = cardDao.findCardsByAccountId(account.getId());
				account.setCards(cards);
			}
			request.setAttribute(ParameterName.ACCOUNTS, accounts);
		} catch (DAOException e) {
			output = false;
			throw new ServiceException(e.getMessage());
		}
		return output;
	}
	
}
