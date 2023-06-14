package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

/**
 * The class MethodCallFromThisData stores an information about calling a method.
 *
 */
public class MethodCallFromThisData {
	private String mCalledMethodName = "";
	private String mParentMethodName = "";
	private String mParentMethodSignature = "";;
	private String mParentMethodClassName = "";
	private int mNumOfParams ;
	private Range mCalledMethodRange;
	
	/**
	 * Constructor
	 * @param pCalledMethodName The name of the called method.
	 * @param pParentMethodName The method name contains the call. 
	 * @param pParentMethodClassName The class name.
	 * @param pCalledMethodRange The range of the call.
	 * @param pParentMethodSignature The signature of the method contains the call.
	 * @param pNumOfParams The number of parameters.
	 */
	public MethodCallFromThisData(String pCalledMethodName, String pParentMethodName, String pParentMethodClassName, Range pCalledMethodRange, String pParentMethodSignature, int pNumOfParams){
		mCalledMethodName = pCalledMethodName;
		mParentMethodName = pParentMethodName;
		mParentMethodClassName = pParentMethodClassName;
		mCalledMethodRange = pCalledMethodRange;
		mParentMethodSignature = pParentMethodSignature;
		mNumOfParams = pNumOfParams;
	}
	
	/**
	 * Returns the number of parameters.
	 * @return
	 */
	public int getNumOfParams() {
		return mNumOfParams;
	}

	/**
	 * Returns the signature of the method contains the call.
	 * @return
	 */
	public String getParentMethodSignature() {
		return mParentMethodSignature;
	}

	/**
	 * Returns the  name of the called method.
	 * @return
	 */
	public String getCalledMethodName() {
		return mCalledMethodName;
	}
	
	/**
	 * Returns the name of the method contains the call.  
	 * @return
	 */
	public String getmParentMethodName() {
		return mParentMethodName;
	}

	/**
	 * Returns  name of the class.  
	 * @return
	 */
	public String getParentMethodClassName() {
		return mParentMethodClassName;
	}

	/**
	 * Returns the range of the call. 
	 * @return
	 */
	public Range getCalledMethodRange() {
		return mCalledMethodRange;
	}
	
	/**
	 * Returns a string with all the information about the method call.
	 */
	@Override
	public String toString() {
		String result = "Called method name: " +  mCalledMethodName + System.lineSeparator();
		result += "Parent method name: " +  mParentMethodName + System.lineSeparator();
		result += "Parent method class name: " +  mParentMethodClassName + System.lineSeparator();
		result += "Called method range: " +  mCalledMethodRange.toString() + System.lineSeparator();
		return result;
	}
}
