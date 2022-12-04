package ac.code.verifier.helper;

public class VerifyResult {
	
	private Result result;
	private String message;
	private String methodSignature;
	private String className;
	private boolean generalError;

	public boolean isGeneralError() {
		return generalError;
	}

	public void setGeneralError(boolean generalError) {
		this.generalError = generalError;
	}

	public VerifyResult(Result pResult, String pMessage, String pMethodSignature, String pClassName){
		result = pResult;
		message = pMessage;
		methodSignature = pMethodSignature;
		className = pClassName;
		generalError = false;
	}
	
	public Result getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
	
	public String getMethodSignature() {
		return methodSignature;
	}
	
	public String getClassName() {
		return className;
	}
}
