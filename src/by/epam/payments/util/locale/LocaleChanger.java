package by.epam.payments.util.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleChanger {
	
	private static Locale ruLocale = new Locale("ru", "RU");
	
	public static void translateHomePage() {
		
	}
	
	public static void main(String... args) {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("by/epam/payments/util/locale/payments", ruLocale);
		String value = resourceBundle.getString("brand");
		System.out.println(value);
	}
	
	
}
