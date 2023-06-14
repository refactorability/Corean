package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.List;
import ac.code.verifier.engine.CodeFragment;

/**
 * The class MethodFragmentMarksData stores an information about marks of fragments.
 *
 */
public class MethodFragmentMarksData {
	
	private List<CodeFragment> codeFragmentMarks = new ArrayList<CodeFragment>();
	private String mMethodName;
	private String mClassName = "";
	private String mMethodSignature = "";
	
	/**
	 * Constructor
	 * @param pMethodName The name of the method.
	 * @param pMethodSignature The signature of the method.
	 * @param pClassName The name of the class.
	 */
	public MethodFragmentMarksData(String pMethodName, String pMethodSignature, String pClassName){
		mMethodName = pMethodName;
		mClassName = pClassName;
		mMethodSignature = pMethodSignature;
	}

	/**
	 * Returns the list of fragment marks.
	 * @return
	 */
	public List<CodeFragment> getCodeFragmentMarks() {
		return codeFragmentMarks;
	}
	
	/**
	 * Returns the name of the method.
	 * @return
	 */
	public String getMethodName() {
		return mMethodName;
	}
	
	/**
	 * Returns the name of the class.
	 * @return
	 */
	public String getClassName() {
		return mClassName;
	}
	
	/**
	 * Returns the signature of the method.
	 * @return
	 */
	public String getMethodSignature() {
		return mMethodSignature;
	}
	
	/**
	 * Adds a mark of the beginning of a fragment.
	 */
	public void addBeginFragment() {
		codeFragmentMarks.add(CodeFragment.BEGIN);
	}
	
	/**
	 * Adds a mark of the end of a fragment.
	 */
	public void addEndFragment() {
		codeFragmentMarks.add(CodeFragment.END);
	}
}
