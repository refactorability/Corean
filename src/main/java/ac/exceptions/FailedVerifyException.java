package ac.exceptions;

/**
 * An exception FailedVerifyException represents failure in running the tests.
 */
public class FailedVerifyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param errorMessage The message.
	 */
	public FailedVerifyException(String errorMessage) 
	{ 
        super(errorMessage);
    }
}
