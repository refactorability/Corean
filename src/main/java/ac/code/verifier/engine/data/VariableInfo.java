package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.Range;

/**
 * The class VariableInfo stores an information about the variable.
 *
 */
public class VariableInfo {
	
	/**
	 * 
	 * @param pVarName The name of the variable.
	 * @param pVarDeclarationPlace The range of the declaration.
	 * @param pVarType The type of the variable. 
	 */
	public VariableInfo(String pVarName, Range pVarDeclarationPlace, VariableType pVarType) {
		this.varName = pVarName;
		this.varDeclarationPlace = pVarDeclarationPlace;
		this.varType = pVarType;
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
	 * Returns the type of the variable. 
	 * @return
	 */
	public VariableType getVarType() {
		return varType;
	}	
	
	/**
	 * Returns a list of ranges where the variable is used. 
	 * @return List of Range.
	 */
	public List<Range> getUsesPlaces() {
		return UsesPlaces;
	}
	
	/**
	 * Adds a range of use.
	 * @param pRange The range.
	 */
	public void addUsePlace(Range pRange) {
		UsesPlaces.add(pRange);
	}
	
	/**
	 * Returns a list of ranges where the value of variable changed. 
	 * @return
	 */
	public List<ChangeValInfo> getChangeValPlaces() {
		return ChangeValPlaces;
	}
	
	/**
	 * Adds a range where the value of variable changed.
	 * @param pChangeValInfo The change value information.
	 */
	public void addChangeValPlace(ChangeValInfo pChangeValInfo) {
		ChangeValPlaces.add(pChangeValInfo);
	}
	
	private String varName;
	private Range varDeclarationPlace;
	private VariableType varType;
	private List<Range> UsesPlaces = new ArrayList<Range>();
	private List<ChangeValInfo> ChangeValPlaces = new ArrayList<ChangeValInfo>();
}
