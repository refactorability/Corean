package ac.code.verifier.engine;

/**
 * The CodeFragmentResult class represents the test result of a fragments.
 *
 */
public class CodeFragmentResult {

	CodeFragmentResultEnum mResult;
	String mType;
	
	/**
	 * Constructor
	 * @param pResult The result of the tests
	 * @param pType The type.
	 */
	public CodeFragmentResult(CodeFragmentResultEnum pResult, String pType){
		mResult = pResult;
		mType = pType;
	}
	
	/**
	 * Returns the result.
	 * @return
	 */
	public CodeFragmentResultEnum getResult() {
		return mResult;
	}

	/**
	 * Returns the type.
	 * @return
	 */
	public String getType() {
		return mType;
	}
	

}
