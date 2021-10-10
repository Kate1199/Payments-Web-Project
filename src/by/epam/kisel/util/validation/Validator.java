package by.epam.kisel.util.validation;

import java.util.Arrays;

import by.epam.kisel.bean.User;
import by.epam.kisel.util.MinValues;

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
		if(user.getId() == 0 || isNull(user.getLogin()) || isNull(user.getEmail())
				|| isNull(user.getPassword()) || isNull(user.getRole())) {
			isEmpty = true;
		}
		return isEmpty;
	}
	
	public static boolean isEmptyCharArray(char[] array) {
		return Arrays.equals(array, new char[MinValues.MIN_ARRAY_SIZE]);
	}
}
