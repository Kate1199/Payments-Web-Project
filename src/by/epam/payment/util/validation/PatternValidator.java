package by.epam.payment.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import by.epam.payment.exception.ServiceException;
import by.epam.payment.service.hashing.Hasher;
import by.epam.payment.util.MinValue;
import by.epam.payment.util.parameterConstant.PatternMap;

public class PatternValidator {
	
	private static final int CHAR_SEQUENCE_BEGINNING = 0;
	private static final int CHAR_SEQUENCE_ENDING_DEFINER = 1;
	
	private PatternValidator() {	
	}
	
	public static String defineParameter(HttpServletRequest request, String parameterName) {
		String parameter = MinValue.EMPTY_STRING;
		String tempParameter = request.getParameter(parameterName);
		PatternMap parameterMap = PatternMap.getInstanse();
		if(checkString(tempParameter, parameterMap.getPattern(parameterName))) {
			parameter = tempParameter;
		}
		return parameter;
	}
	
	public static byte[] defineProtectedParameter(HttpServletRequest request, String parameterName, byte[] salt)
			throws ServiceException {
		
		byte[] parameter = new byte[MinValue.MIN_ARRAY_SIZE];
		char[] tempParameter = request.getParameter(parameterName).toCharArray();
		PatternMap parameterMap = PatternMap.getInstanse();
		if(checkString(new String(tempParameter), parameterMap.getPattern(parameterName))) {
			parameter = Hasher.hashData(tempParameter, salt);
		}
		return parameter;
	}
	
	private static boolean checkString(String data, String regex) {
		boolean isMatch = true;
		if(Validator.isStringEmpty(data)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(data.subSequence(CHAR_SEQUENCE_BEGINNING, data.length() - CHAR_SEQUENCE_ENDING_DEFINER));
		if(!matcher.matches() || Validator.isNull(data)) {
			isMatch = false;
		}
		return isMatch;
	}
}
