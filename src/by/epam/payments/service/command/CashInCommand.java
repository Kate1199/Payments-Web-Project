package by.epam.payments.service.command;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.payments.bean.Bill;
import by.epam.payments.bean.Payment;
import by.epam.payments.bean.Transaction;
import by.epam.payments.dao.bill.BillDaoImpl;
import by.epam.payments.dao.payment.PaymentDaoImpl;
import by.epam.payments.exception.DAOException;
import by.epam.payments.exception.IncorrectEnteredDataException;
import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.AccountTransfer;
import by.epam.payments.util.parameterConstants.LogMessage;
import by.epam.payments.util.parameterConstants.ParameterName;
import by.epam.payments.util.parameterConstants.Path;
import by.epam.payments.util.validation.Validator;
import by.epam.payments.view.View;

public class CashInCommand implements ServletCommand {
	
	private static final String PAYMENT_NAME = "Пополнение счёта";
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		HttpSession session = request.getSession();
		String recieverAccount = (String) session.getAttribute(ParameterName.ACCOUNT_IBAN);
		String senderAccount = request.getParameter(ParameterName.ACCOUNT_IBAN);
		double sumView = Double.parseDouble(request.getParameter(ParameterName.SUM));
		long realSum = View.countRealBalance(sumView);
		AccountTransfer accountTransfer = new AccountTransfer();
		
		try {
			sendMessageIfSenderAccountIsEmpty(senderAccount, request, response);
		} catch (ServiceException | IncorrectEnteredDataException e) {
			throw new ServiceException(e.getMessage());
		}
		
		String path;
		if(accountTransfer.doTransfer(senderAccount, recieverAccount, realSum)) {
			Transaction transaction = new Transaction(senderAccount, recieverAccount);
			path = goToBillPage(request, transaction, realSum);
		} else {
			path = Path.SERVER_ERROR_PATH;
		}
		
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException();
		}
	}
	
	private boolean sendMessageIfSenderAccountIsEmpty(String senderAccount, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectEnteredDataException {
		boolean send = false;
		if(Validator.isNull(senderAccount) || Validator.isStringEmpty(senderAccount)) {
			send = true;
			updatePage(LogMessage.SENDER_ACCOUNT_IS_EMPTY_RU, request, response);
			throw new IncorrectEnteredDataException(LogMessage.SENDER_ACCOUNT_IS_EMPTY);
		}
		return send;
	}
	
	private boolean updatePage(String message, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		boolean update = true;
		request.setAttribute(ParameterName.MESSAGE, message);
		try {
			request.getRequestDispatcher(Path.CASH_IN_PATH).forward(request, response);
		} catch (ServletException | IOException e) {
			update = false;
			logger.log(Level.ERROR, e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return update;
	}
	
	private String goToBillPage(HttpServletRequest request, Transaction transaction, long realSum) throws ServiceException {
		Bill bill = makeBill(realSum);
		PaymentDaoImpl paymentDao = new PaymentDaoImpl();
		Payment payment;
		try {
			payment = paymentDao.findByName(PAYMENT_NAME);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		request.setAttribute(ParameterName.SENDER_ACCOUNT, transaction.getSenderAccount());
		request.setAttribute(ParameterName.RECIEVER_ACCOUNT, transaction.getRecieverAccount());
		request.setAttribute(ParameterName.PAYMENT, payment);
		request.setAttribute(ParameterName.BILL, bill);
		request.setAttribute(ParameterName.DATE_TIME, View.takeDateTime());
		
		addBillToDB(bill);
		
		HttpSession session = request.getSession();
		session.removeAttribute(ParameterName.ACCOUNT_IBAN);
		
		return Path.BILL_PAGE_PATH;
	}
	
	private Bill makeBill(long sum) {
		Calendar dateTime = Calendar.getInstance();
		return new Bill(dateTime, sum);
	}
	
	private boolean addBillToDB(Bill bill) throws ServiceException {
		boolean add = true;
		
		BillDaoImpl billDao = new BillDaoImpl();
		try {
			billDao.insertInto(bill);
		} catch (DAOException e) {
			add = false;
			throw new ServiceException(e.getMessage());
		}
		return add;
	}
	
	
	
	
}
