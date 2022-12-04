package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

public class VariableDeclarationInfo {
	
	private String varName;
	private Range varDeclarationPlace;
	
	public VariableDeclarationInfo(String pVarName, Range pVarDeclarationPlace){
		this.varName = pVarName;
		this.varDeclarationPlace = pVarDeclarationPlace;
	}
	
	public String getVarName() {
		return varName;
	}
	
	public Range getVarDeclarationPlace() {
		return varDeclarationPlace;
	}
	
	public VariableDeclarationInfo clone() {
		return new VariableDeclarationInfo(varName, varDeclarationPlace);
	}
}
