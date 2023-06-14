package ac.code.verifier.engine.data;

/**
 * The class ExtractableCodeFragmentProblemData stores a basic information about method.
 *
 */
public class MethodBasicInfo {

	private String mMethodName = "";
	private int mNumOfParams;
	
	/**
	 * Returns the name of the method.
	 * @return
	 */
	public String getMethodName() {
		return mMethodName;
	}
	
	/**
	 * Returns the number of parameters.
	 * @return
	 */
	public int getNumOfParams() {
		return mNumOfParams;
	}
	
	/**
	 * Constructor
	 * @param pMethodName The name of the method.
	 * @param pNumOfParams The number of parameters.
	 */
	public MethodBasicInfo(String pMethodName, int pNumOfParams){
		mMethodName = pMethodName;
		mNumOfParams = pNumOfParams;
	}

}
