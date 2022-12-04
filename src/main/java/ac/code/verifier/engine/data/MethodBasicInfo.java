package ac.code.verifier.engine.data;

public class MethodBasicInfo {

	private String mMethodName = "";
	private int mNumOfParams;
	
	public String getMethodName() {
		return mMethodName;
	}
	public int getNumOfParams() {
		return mNumOfParams;
	}
	
	public MethodBasicInfo(String pMethodName, int pNumOfParams){
		mMethodName = pMethodName;
		mNumOfParams = pNumOfParams;
	}

}
