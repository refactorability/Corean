package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

public class AssignExprData {
	
	String mAssignExprVarName = "";
	private String mAssignExprClassName = "";
	private String mAssignExprMethodName = "";
	private Range mAssignExprRange;
	private String mAssignExprMethodSignature = "";
	
	public AssignExprData(String pAssignExprVarName, String pAssignExprClassName, String pAssignExprMethodName, Range pAssignExprRange, String pAssignExprMethodSignature){
		mAssignExprVarName = pAssignExprVarName;
		mAssignExprClassName = pAssignExprClassName;
		mAssignExprMethodName = pAssignExprMethodName;
		mAssignExprRange = pAssignExprRange;
		mAssignExprMethodSignature = pAssignExprMethodSignature;
	}
	
	public String getmAssignExprVarName() {
		return mAssignExprVarName;
	}

	public String getAssignExprClassName() {
		return mAssignExprClassName;
	}

	public String getAssignExprMethodName() {
		return mAssignExprMethodName;
	}

	public Range getAssignExprRange() {
		return mAssignExprRange;
	}
	
	public String getAssignExprMethodSignature() {
		return mAssignExprMethodSignature;
	}
	
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
