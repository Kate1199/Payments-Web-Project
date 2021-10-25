package by.epam.payments.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import by.epam.payments.util.MinValues;
import by.epam.payments.util.parameterConstants.AccountBalanceMultiplier;

public class View {
	
	private static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
	
	public static String takeDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
		return dateTimeFormatter.format(dateTime);
	}
	
	public static long countRealBalance(double balance) {
		if(balance <= 0) {
			return MinValues.MIN_LONG_VALUE;
		}
		return (long) (balance * AccountBalanceMultiplier.BALANCE_MULTIPLIER);
	}
}
