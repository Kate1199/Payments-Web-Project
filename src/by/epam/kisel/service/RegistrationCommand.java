package by.epam.kisel.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.kisel.bean.User;
import by.epam.kisel.dao.user.UserDaoImpl;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.exception.IncorrectEnteredDataException;
import by.epam.kisel.exception.ServiceException;
import by.epam.kisel.service.entityMaker.UserMaker;

public class RegistrationCommand implements ServletCommand {
	
	public RegistrationCommand() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
	User user = null;
		try {
			UserMaker userMaker = new UserMaker();
			user = userMaker.makeEntity(request, response);
		} catch (IncorrectEnteredDataException e) {
			throw new ServiceException(e.getMessage());
		}
	
		UserDaoImpl dao = new UserDaoImpl();
		try {
			dao.create(user);
			response.sendRedirect("index.jsp");
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	
//	private Client makeClient(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectEnteredDataException {
//		String firstName;
//		String lastName;
//		String patronymic;
//		String identificationNumber;
//		String registrationAddress;
//		String realAddress;
//		boolean residentUSA;
//		
//		firstName = defineName(request, FIRST_NAME);
//		lastName = defineName(request, LAST_NAME);
//		patronymic = defineName(request, PATRONYMIC);
//		identificationNumber = defineIdentifiactionNumber(request);
//		registrationAddress = defineAddress(request, REGISTRATION_ADDRESS);
//		realAddress = defineAddress(request, REAL_ADDRESS);
//		residentUSA = false;
//		Client client = new Client(identificationNumber, firstName, lastName, patronymic, registrationAddress, realAddress, residentUSA);
//		isClientCorrect(client, response);
//
//		return client;
//	}
//	
//	private String defineName(HttpServletRequest request, String parameterName) {
//		String name = MinValues.EMPTY_STRING;
//		String tempName = request.getParameter(parameterName);
//		if(RegistrationValidator.checkName(tempName)) {
//			name = tempName;
//		}
//		return name;
//	}
//	
//	private String defineIdentifiactionNumber(HttpServletRequest request) {
//		String identifiactionNumber = MinValues.EMPTY_STRING;
//		String tempIdentifiactionNumber = request.getParameter(IDENTIFIACTION_NUMBER);
//		if(RegistrationValidator.checkIdentificationNumber(tempIdentifiactionNumber)) {
//			identifiactionNumber = tempIdentifiactionNumber;
//		}
//		return identifiactionNumber;
//	}
//	
//	private String defineAddress(HttpServletRequest request, String addressType) {
//		String address = MinValues.EMPTY_STRING;
//		String tempAddress = request.getParameter(addressType);
//		if(RegistrationValidator.checkAddress(tempAddress)) {
//			address = tempAddress;
//		}
//		return address;
//	}
//	
//	private boolean isClientCorrect(Client client, HttpServletResponse response) throws ServiceException, IncorrectEnteredDataException {
//		boolean isCorrect = true;
//		if(Validator.isStringEmpty(client.getFirstName())
//				|| Validator.isStringEmpty(client.getLastName())
//				|| Validator.isStringEmpty(client.getLastName()) 
//				|| Validator.isStringEmpty(client.getPatronymic())
//				|| Validator.isStringEmpty(client.getIdentificationNumber())
//				|| Validator.isStringEmpty(client.getRegistrationAdress())
//				|| Validator.isStringEmpty(client.getRealAdress())) {
//			
//			isCorrect = false;
//			try {
//				//TODO:change
//				
//				response.sendRedirect("index.jsp");
//			} catch (IOException e) {
//				throw new ServiceException(e.getMessage());
//			}
//			throw new IncorrectEnteredDataException(INCORRECT_DATA_FORMAT);
//		} 
//		return isCorrect;
//	}
}
	
	
