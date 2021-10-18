package by.epam.payments.util.validation;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import by.epam.payments.exception.ServiceException;
import by.epam.payments.service.encrytion.Encrypter;
import by.epam.payments.util.MinValues;
import by.epam.payments.util.parameterConstants.ParameterMap;

public class PatternValidator {
	
	private static final int CHAR_SEQUENCE_BEGINNING = 0;
	private static final int CHAR_SEQUENCE_ENDING_DEFINER = 1;
	
	private PatternValidator() {	
	}
	
	public static String defineParameter(HttpServletRequest request, String parameterName) {
		String parameter = MinValues.EMPTY_STRING;
		String tempParameter = request.getParameter(parameterName);
		ParameterMap parameterMap = ParameterMap.getInstanse();
		if(checkString(tempParameter, parameterMap.getPattern(parameterName))) {
			parameter = tempParameter;
		}
		return parameter;
	}
	
	public static byte[] defineProtectedParameter(HttpServletRequest request, String parameterName, byte[] salt)
			throws ServiceException {
		
		byte[] parameter = new byte[MinValues.MIN_ARRAY_SIZE];
		char[] tempParameter = request.getParameter(parameterName).toCharArray();
		ParameterMap parameterMap = ParameterMap.getInstanse();
		if(checkString(new String(tempParameter), parameterMap.getPattern(parameterName))) {
			parameter = Encrypter.hashData(tempParameter, salt);
		}
		return parameter;
	}
	
	public static boolean checkString(String data, String regex) {
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
