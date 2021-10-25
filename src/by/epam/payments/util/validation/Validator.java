package by.epam.payments.util.validation;

import java.util.Arrays;
import java.util.List;

import by.epam.payments.bean.Client;
import by.epam.payments.bean.User;
import by.epam.payments.util.MinValues;

public class Validator {
	
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	public static boolean isStringEmpty(String str) {
		return str.trim().equals(MinValues.EMPTY_STRING);
	}
	
	public static boolean isEmptyByteArray(byte[] array) {
		return Arrays.equals(array, new byte[MinValues.MIN_ARRAY_SIZE]);
	}
	
	public static boolean isEmptyCharArray(char[] array) {
		return Arrays.equals(array, new char[MinValues.MIN_ARRAY_SIZE]);
	}
	
	public static boolean isEmpty(User user) {
		return isNull(user) 
				|| isNull(user.getLogin()) || isStringEmpty(user.getLogin()) 
				|| isNull(user.getEmail()) || isStringEmpty(user.getEmail())
				|| isNull(user.getPassword()) || isEmptyByteArray(user.getPassword())
				|| isNull(user.getRole()) || isStringEmpty(user.getRole().toString());
	
	}
	
	public static boolean isEmpty(Client client) {
		return isNull(client) ||
				isNull(client.getIdentificationNumber()) || isStringEmpty(client.getIdentificationNumber())
				|| isNull(client.getFirstName()) || isStringEmpty(client.getFirstName())
				|| isNull(client.getLastName()) || isStringEmpty(client.getLastName())
				|| isNull(client.getPatronymic())
				|| isNull(client.getPhoneNumber()) || isStringEmpty(client.getPhoneNumber())
				|| isNull(client.getRegistrationAddress()) || isStringEmpty(client.getRegistrationAddress())
				|| isNull(client.getRealAddress()) || isStringEmpty(client.getRealAddress());
	}
	
	public static boolean lessThanZero(long number) {
		return number < 0;
	}
	
	public static boolean lessThanZeroOrEqualZero(long number) {
		return number <= 0;
	}
	
	public static <T> boolean isEmptyList(List<T> list) {
		return list.isEmpty();
	}
}
