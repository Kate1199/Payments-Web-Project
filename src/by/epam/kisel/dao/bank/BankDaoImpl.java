package by.epam.kisel.dao.bank;

import by.epam.kisel.dao.SqlDatabaseDAO;
import by.epam.kisel.exception.DAOException;
import by.epam.kisel.util.MinValues;
import by.epam.kisel.util.parameterConstants.SqlRequest;
import by.epam.kisel.util.validation.Validator;
import by.epam.payments.bean.Bank;

public class BankDaoImpl extends SqlDatabaseDAO<Bank> {

	public BankDaoImpl() {	
	}
	
	public String findBankNameByBankId(int bankId) throws DAOException {
		Object bankNameParameter = findByParameterField(SqlRequest.FIND_BANK_NAME_BY_ID, bankId);
		String bankName = MinValues.EMPTY_STRING;
		if(!Validator.isNull(bankNameParameter)) {
			bankName = (String) bankNameParameter;
		}
		return bankName; 
	}
}
