package andresdlrg.activemq.stresser.exception;

public class InvalidValueForDatePatternException extends RuntimeException {

	private static final long serialVersionUID = 3230360830345567725L;

	public InvalidValueForDatePatternException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidValueForDatePatternException(String message) {
		super(message);
	}

}
