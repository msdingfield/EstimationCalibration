package msdingfield.estimationcalibrator.dal;

public class JdbcInitializationException extends RuntimeException {

	private static final long serialVersionUID = -7359935117792231032L;

	public JdbcInitializationException() {
	}

	public JdbcInitializationException(String message) {
		super(message);
	}

	public JdbcInitializationException(Throwable cause) {
		super(cause);
	}

	public JdbcInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public JdbcInitializationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
