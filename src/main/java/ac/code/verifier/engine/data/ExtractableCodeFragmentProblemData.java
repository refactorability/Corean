package ac.code.verifier.engine.data;

import ac.code.verifier.engine.CodeFragmentResult;

/**
 * The class ExtractableCodeFragmentProblemData stores information about  a problem in an extractable code fragment.
 *
 */
public class ExtractableCodeFragmentProblemData {

	private MethodData mMethodData;
	private CodeFragmentResult mCodeFragmentResult;
	
	/**
	 * Constructor
	 * @param pMethodData The data of the method. 
	 * @param pCodeFragmentResult The result.
	 */
	public ExtractableCodeFragmentProblemData(MethodData pMethodData, CodeFragmentResult pCodeFragmentResult) {
		mMethodData = pMethodData;
		mCodeFragmentResult = pCodeFragmentResult;
	}
	
	/**
	 * Returns the data of the method.
	 * @return
	 */
	public MethodData getMethodData() {
		return mMethodData;
	}

	/**
	 * Returns the result.
	 * @return
	 */
	public CodeFragmentResult getCodeFragmentResult() {
		return mCodeFragmentResult;
	}

}
