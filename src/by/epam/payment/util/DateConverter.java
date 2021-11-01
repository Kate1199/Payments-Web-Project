package by.epam.payment.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateConverter {
	
	private DateConverter() {
	}
	
	public static Timestamp makeTimestamp(Calendar dateTime) {
		return new Timestamp(dateTime.getTimeInMillis());
	}
	
	public static Calendar makeCalendar(Timestamp dateTime) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(dateTime.getTime());
		return calendar;
	}
}
