package ac.code.verifier.engine.var.handlers;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.Position;
import com.github.javaparser.Range;

import ac.code.verifier.engine.data.ChangeValInfo;
import ac.code.verifier.engine.data.VariableInfo;
import ac.code.verifier.engine.data.VariableType;

/**
 * The class VariableInfoHandler provides methods that handle information about variables.
 *
 */
public class VariableInfoHandler {
	
	private List<VariableInfo> mVariableInfoList = new ArrayList<VariableInfo>();
	
	private Range UNDEFINE_RANGE = new Range(new Position(0, 0), new Position(0, 1)); 

	/**
	 * Returns list of variable information.
	 * @return
	 */
	public List<VariableInfo> getVariableInfoList() {
		return mVariableInfoList;
	}
	
	/**
	 * Adds new variable.
	 * @param pVarName The name of the variable. 
	 * @param pVarDeclarationPlace The range in which the variable is declared.
	 * @param pVarebleType The type of the variable.  
	 */
	public void addVariableByDeclaration(String pVarName, Range pVarDeclarationPlace, VariableType pVarebleType) {
		for(VariableInfo vi : mVariableInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarDeclarationPlace().equals(pVarDeclarationPlace)) {
				System.out.println("[ERROR-DEBUG:] " + pVarName + " already exists.");
				return;
			} 
		}
		VariableInfo newVarebleInfo = new VariableInfo(pVarName, pVarDeclarationPlace, pVarebleType);
		mVariableInfoList.add(newVarebleInfo);
		
	}
	
	/**
	 * Adds a place where the variable is used.
	 * @param pVarName The name of the variable. 
	 * @param pVarDeclarationPlace The range in which the variable is declared.
	 * @param varUsePlace The range in which this variable is used.
	 */
	public void addVariableUse(String pVarName, Range pVarDeclarationPlace, Range varUsePlace) {
		 
		if(pVarDeclarationPlace == null) {
			pVarDeclarationPlace = UNDEFINE_RANGE;
		} 
	  
		for(VariableInfo vi : mVariableInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarDeclarationPlace().equals(pVarDeclarationPlace)) {
				vi.addUsePlace(varUsePlace);
				return;
			}
		}
		VariableInfo newVarebleInfo = new VariableInfo(pVarName, pVarDeclarationPlace, VariableType.UNKNOWN);
		newVarebleInfo.addUsePlace(varUsePlace);
		mVariableInfoList.add(newVarebleInfo);
	}
	
	/**
	 * Adds a place where the instance variable is used.
	 * @param pVarName The name of the instance variable. 
	 * @param varUsePlace The range in which this instance variable is used.
	 */
	public void addInstanceVariableUse(String pVarName, Range varUsePlace) {	
		for(VariableInfo vi : mVariableInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarType() == VariableType.INSTANCE_VARIABLE) {
				vi.addUsePlace(varUsePlace);
				return;
			}
		}
	}
	
	/**
	 * Adds a place where the variable changes its value.
	 * @param pVarName The name of the variable. 
	 * @param pVarDeclarationPlace The range in which the variable is declared.
	 * @param varChangeValPlace The range in which the variable changes its value.
	 * @param pIsUnaryChange Indicates whether it is unary change.
	 */
	public void addVariableChangeVal(String pVarName, Range pVarDeclarationPlace, Range varChangeValPlace, boolean pIsUnaryChange) {
		if(pVarDeclarationPlace == null) {
			pVarDeclarationPlace = UNDEFINE_RANGE;
		}
		
		for(VariableInfo vi : mVariableInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarDeclarationPlace().equals(pVarDeclarationPlace)) {
				vi.addChangeValPlace(new ChangeValInfo(varChangeValPlace, pIsUnaryChange));
				return;
			}
		}
		VariableInfo newVarebleInfo = new VariableInfo(pVarName, pVarDeclarationPlace, VariableType.UNKNOWN);
		newVarebleInfo.addChangeValPlace(new ChangeValInfo(varChangeValPlace, pIsUnaryChange));
		mVariableInfoList.add(newVarebleInfo);
	}
	
	/**
	 * Adds a place where the instance variable changes its value.
	 * @param pVarName The name of the instance variable.
	 * @param varChangeValPlace The range in which the instance variable changes its value.
	 * @param pIsUnaryChange Indicates whether it is unary change.
	 */
	public void addInstanceVariableChangeVal(String pVarName, Range varChangeValPlace, boolean pIsUnaryChange) {	
		for(VariableInfo vi : mVariableInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarType() == VariableType.INSTANCE_VARIABLE) {
				vi.addChangeValPlace(new ChangeValInfo(varChangeValPlace, pIsUnaryChange));
				return;
			}
		}
	}

}
