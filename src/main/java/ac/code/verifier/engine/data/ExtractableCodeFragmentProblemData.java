package ac.code.verifier.engine.data;

import ac.code.verifier.engine.CodeFragmentResult;

public class ExtractableCodeFragmentProblemData {

	private MethodData mMethodData;
	private CodeFragmentResult mCodeFragmentResult;
	
	public ExtractableCodeFragmentProblemData(MethodData pMethodData, CodeFragmentResult pCodeFragmentResult) {
		mMethodData = pMethodData;
		mCodeFragmentResult = pCodeFragmentResult;
	}
	
	public MethodData getMethodData() {
		return mMethodData;
	}

	public CodeFragmentResult getCodeFragmentResult() {
		return mCodeFragmentResult;
	}

}
