package luxoft.ch.sortingstation;

public class SortingFailureException extends RuntimeException {

	public SortingFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public SortingFailureException(String message) {
		super(message);
	}

}
