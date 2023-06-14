package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

/**
 * The class AssignExprData stores information about assign expression.
 *
 */
public class AssignExprData {
	
	String mAssignExprVarName = "";
	private String mAssignExprClassName = "";
	private String mAssignExprMethodName = "";
	private Range mAssignExprRange;
	private String mAssignExprMethodSignature = "";
	
	/**
	 * Constructor
	 * @param pAssignExprVarName The name of variable.
	 * @param pAssignExprClassName The name of class.
	 * @param pAssignExprMethodName The name of method.
	 * @param pAssignExprRange The range in which this assign expression appears.
	 * @param pAssignExprMethodSignature The method signature.
	 */
	public AssignExprData(String pAssignExprVarName, String pAssignExprClassName, String pAssignExprMethodName, Range pAssignExprRange, String pAssignExprMethodSignature){
		mAssignExprVarName = pAssignExprVarName;
		mAssignExprClassName = pAssignExprClassName;
		mAssignExprMethodName = pAssignExprMethodName;
		mAssignExprRange = pAssignExprRange;
		mAssignExprMethodSignature = pAssignExprMethodSignature;
	}
	
	/**
	 * Returns the name of the variable.
	 * @return
	 */
	public String getmAssignExprVarName() {
		return mAssignExprVarName;
	}

	/**
	 * Returns the name of the class.
	 * @return
	 */
	public String getAssignExprClassName() {
		return mAssignExprClassName;
	}

	/**
	 * Returns the name of the method.
	 * @return
	 */
	public String getAssignExprMethodName() {
		return mAssignExprMethodName;
	}

	/**
	 * Returns the range in which this assign expression appears.
	 * @return
	 */
	public Range getAssignExprRange() {
		return mAssignExprRange;
	}
	
	/**
	 * Returns the signature of the method.
	 * @return
	 */
	public String getAssignExprMethodSignature() {
		return mAssignExprMethodSignature;
	}
	/**
	 * Returns a string with all the information about this assign expression. 
	 */
	@Override
	public String toString() {
		String result = " Assign Expr Var Name: " +  mAssignExprVarName + System.lineSeparator();
		result += " Assign Expr Class Name: " +  mAssignExprClassName + System.lineSeparator();
		result += " Assign Expr Method Name: " +  mAssignExprMethodName + System.lineSeparator();
		result += " Assign Expr Method Signature: " +  mAssignExprMethodSignature + System.lineSeparator();
		result += "Assign Expr Range: " +  mAssignExprRange.toString() + System.lineSeparator();
		return result;
	}

}
