package andresdlrg.activemq.stresser.exception;

public class InvalidTimePeriod extends RuntimeException {

	private static final long serialVersionUID = 4437575788970596499L;

	public InvalidTimePeriod(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTimePeriod(String message) {
		super(message);
	}

}
