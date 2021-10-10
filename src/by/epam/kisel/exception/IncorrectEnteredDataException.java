package by.epam.kisel.exception;

public class IncorrectEnteredDataException extends Exception {
	public IncorrectEnteredDataException() {
		super();
	}
	
	public IncorrectEnteredDataException(String message) {
		super(message);
	}
	
	public IncorrectEnteredDataException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IncorrectEnteredDataException(Throwable cause) {
		super(cause);
	}
}
