package by.epam.payment.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import by.epam.payment.util.MinValue;
import by.epam.payment.util.parameterConstant.AccountBalanceMultiplier;

public class View {
	
	private static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
	
	public static String takeDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
		return dateTimeFormatter.format(dateTime);
	}
	
	public static long countRealBalance(double balance) {
		if(balance <= 0) {
			return MinValue.MIN_LONG_VALUE;
		}
		return (long) (balance * AccountBalanceMultiplier.BALANCE_MULTIPLIER);
	}
}
