package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

/**
 * The class VariableDeclarationInfo stores an information about the declaration of the variable.
 *
 */
public class VariableDeclarationInfo {
	
	private String varName;
	private Range varDeclarationPlace;
	
	/**
	 * Constructor
	 * @param pVarName The name of the variable.
	 * @param pVarDeclarationPlace The range of the declaration.
	 */
	public VariableDeclarationInfo(String pVarName, Range pVarDeclarationPlace){
		this.varName = pVarName;
		this.varDeclarationPlace = pVarDeclarationPlace;
	}
	
	/**
	 * Returns the name of the variable.
	 * @return
	 */
	public String getVarName() {
		return varName;
	}
	
	/**
	 * Returns the range of the declaration.
	 * @return
	 */
	public Range getVarDeclarationPlace() {
		return varDeclarationPlace;
	}
	
	/**
	 * Clones the VariableDeclarationInfo object.
	 */
	public VariableDeclarationInfo clone() {
		return new VariableDeclarationInfo(varName, varDeclarationPlace);
	}
}
