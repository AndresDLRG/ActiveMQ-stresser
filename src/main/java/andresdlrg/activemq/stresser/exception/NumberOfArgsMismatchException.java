package andresdlrg.activemq.stresser.exception;

public class NumberOfArgsMismatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NumberOfArgsMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumberOfArgsMismatchException(String message) {
		super(message);
	}

}
