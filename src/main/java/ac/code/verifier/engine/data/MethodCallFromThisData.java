package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

public class MethodCallFromThisData {
	private String mCalledMethodName = "";
	private String mParentMethodName = "";
	private String mParentMethodSignature = "";;
	private String mParentMethodClassName = "";
	private int mNumOfParams ;
	private Range mCalledMethodRange;
	
	public MethodCallFromThisData(String pCalledMethodName, String pParentMethodName, String pParentMethodClassName, Range pCalledMethodRange, String pParentMethodSignature, int pNumOfParams){
		mCalledMethodName = pCalledMethodName;
		mParentMethodName = pParentMethodName;
		mParentMethodClassName = pParentMethodClassName;
		mCalledMethodRange = pCalledMethodRange;
		mParentMethodSignature = pParentMethodSignature;
		mNumOfParams = pNumOfParams;
	}
	
	public int getNumOfParams() {
		return mNumOfParams;
	}

	public String getParentMethodSignature() {
		return mParentMethodSignature;
	}

	public String getCalledMethodName() {
		return mCalledMethodName;
	}
	
	public String getmParentMethodName() {
		return mParentMethodName;
	}

	public String getParentMethodClassName() {
		return mParentMethodClassName;
	}

	public Range getCalledMethodRange() {
		return mCalledMethodRange;
	}
	
	@Override
	public String toString() {
		String result = "Called method name: " +  mCalledMethodName + System.lineSeparator();
		result += "Parent method name: " +  mParentMethodName + System.lineSeparator();
		result += "Parent method class name: " +  mParentMethodClassName + System.lineSeparator();
		result += "Called method range: " +  mCalledMethodRange.toString() + System.lineSeparator();
		return result;
	}
}
