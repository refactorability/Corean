package ac.code.verifier.engine;

public class CodeFragmentResult {

	CodeFragmentResultEnum mResult;
	String mType;
	
	public CodeFragmentResult(CodeFragmentResultEnum pResult, String pType){
		mResult = pResult;
		mType = pType;
	}
	
	public CodeFragmentResultEnum getResult() {
		return mResult;
	}

	public String getType() {
		return mType;
	}
	

}
