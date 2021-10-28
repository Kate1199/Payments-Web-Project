package by.epam.payments.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.payments.bean.Account;
import by.epam.payments.bean.Card;
import by.epam.payments.bean.Client;
import by.epam.payments.bean.User;
import by.epam.payments.dao.account.AccountDaoImpl;
import by.epam.payments.dao.card.CardDaoImpl;
import by.epam.payments.dao.client.ClientDaoImpl;
import by.epam.payments.dao.user.UserDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.validation.Validator;

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
		UserDaoImpl userDao = new UserDaoImpl();
		ClientDaoImpl clientDao = new ClientDaoImpl();
		AccountDaoImpl accountDao = new AccountDaoImpl();
		CardDaoImpl cardDao = new CardDaoImpl();
		
		try {
			User user = userDao.findEntityById(userId);
			Client client = clientDao.findClientByUserId(userId);
			request.setAttribute(ParameterName.USER, user);
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
