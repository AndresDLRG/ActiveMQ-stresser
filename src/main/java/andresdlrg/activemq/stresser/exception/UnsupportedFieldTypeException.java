package andresdlrg.activemq.stresser.exception;

public class UnsupportedFieldTypeException extends RuntimeException {

	private static final long serialVersionUID = 4867386928792897401L;

	public UnsupportedFieldTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedFieldTypeException(String message) {
		super(message);
	}

}
