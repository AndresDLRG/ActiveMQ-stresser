package andresdlrg.activemq.stresser.exception;

public class InvalidValueForMapException extends RuntimeException {

	private static final long serialVersionUID = 1473249049808435312L;

	public InvalidValueForMapException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidValueForMapException(String message) {
		super(message);
	}

}
