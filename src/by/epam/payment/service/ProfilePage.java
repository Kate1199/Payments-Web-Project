package by.epam.payment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.payment.bean.Account;
import by.epam.payment.bean.Card;
import by.epam.payment.bean.Client;
import by.epam.payment.bean.User;
import by.epam.payment.dao.account.AccountDaoImpl;
import by.epam.payment.dao.card.CardDaoImpl;
import by.epam.payment.dao.client.ClientDaoImpl;
import by.epam.payment.dao.user.UserDaoImpl;
import by.epam.payment.exception.DAOException;
import by.epam.payment.exception.ServiceException;
import by.epam.payment.util.parameterConstant.ParameterName;
import by.epam.payment.util.validation.Validator;

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
