package exceptions;

public class FailedVerifyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailedVerifyException(String errorMessage) 
	{ 
        super(errorMessage);
    }
}
