package ac.code.verifier.helper;

/**
 * Handles the result of verification
 *
 */
public class VerifyResult {
	
	private Result result;
	private String message;
	private String methodSignature;
	private String className;
	private boolean generalError;

	/**
	 * Checking if this is a general error
	 * @return
	 */
	public boolean isGeneralError() {
		return generalError;
	}

	/**
	 * Set a new value for the general error
	 * @param generalError The value for the general error
	 */
	public void setGeneralError(boolean generalError) {
		this.generalError = generalError;
	}

	/**
	 * Constructor
	 * @param pResult The result of verification 
	 * @param pMessage The message
	 * @param pMethodSignature The signature of the tested method
	 * @param pClassName The name of the class that contains the tested method
	 */
	public VerifyResult(Result pResult, String pMessage, String pMethodSignature, String pClassName){
		result = pResult;
		message = pMessage;
		methodSignature = pMethodSignature;
		className = pClassName;
		generalError = false;
	}
	
	/**
	 * Returns the verification result
	 * @return
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * Returns the verification message
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns the signature of the tested method
	 * @return
	 */
	public String getMethodSignature() {
		return methodSignature;
	}
	
	/**
	 * Returns the name of the class that contains the tested method
	 * @return
	 */
	public String getClassName() {
		return className;
	}
}
