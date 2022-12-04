package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.List;
import ac.code.verifier.engine.CodeFragment;

public class MethodFragmentMarksData {
	
	private List<CodeFragment> codeFragmentMarks = new ArrayList<CodeFragment>();
	private String mMethodName;
	private String mClassName = "";
	private String mMethodSignature = "";
	
	public MethodFragmentMarksData(String pMethodName, String pMethodSignature, String pClassName){
		mMethodName = pMethodName;
		mClassName = pClassName;
		mMethodSignature = pMethodSignature;
	}

	public List<CodeFragment> getCodeFragmentMarks() {
		return codeFragmentMarks;
	}
	
	public String getMethodName() {
		return mMethodName;
	}
	
	public String getClassName() {
		return mClassName;
	}
	
	public String getMethodSignature() {
		return mMethodSignature;
	}
	
	public void addBeginFragment() {
		codeFragmentMarks.add(CodeFragment.BEGIN);
	}
	
	public void addEndFragment() {
		codeFragmentMarks.add(CodeFragment.END);
	}
}
