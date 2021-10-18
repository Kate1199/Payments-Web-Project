package by.epam.kisel.util.validation;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.core.net.Facility;

import by.epam.kisel.util.MinValues;
import by.epam.payments.bean.Client;
import by.epam.payments.bean.User;

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
	
	public static boolean isEmptyUser(User user) {
		boolean isEmpty = false;
		if(isNull(user) || user.getId() == 0 || isNull(user.getLogin()) || isNull(user.getEmail())
				|| isNull(user.getPassword()) || isNull(user.getRole())) {
			isEmpty = true;
		}
		return isEmpty;
	}
	
	public static boolean isEmptyCharArray(char[] array) {
		return Arrays.equals(array, new char[MinValues.MIN_ARRAY_SIZE]);
	}
	
	public static boolean isEmpty(Client client) {
		return isNull(client) ||
				isNull(client.getId()) ||
				isNull(client.getIdentificationNumber()) ||
				isNull(client.getFirstName()) ||
				isNull(client.getLastName()) ||
				isNull(client.getPatronymic()) ||
				isNull(client.getPhoneNumber()) ||
				isNull(client.getRegistrationAddress()) ||
				isNull(client.getRealAddress());
	}
	
	public static boolean lessThanZero(int number) {
		return number < 0;
	}
	
	public static boolean lessThanZeroOrEqualZero(int number) {
		return number <= 0;
	}
	
	public static <T> boolean isEmptyList(List<T> list) {
		return list.isEmpty();
	}
}
