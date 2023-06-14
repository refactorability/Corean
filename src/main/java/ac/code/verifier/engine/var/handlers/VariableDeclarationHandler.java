package ac.code.verifier.engine.var.handlers;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import ac.code.verifier.engine.data.VariableDeclarationInfo;

/**
 * The class VariableDeclarationHandler provides methods that handle information about declarations of variables.
 *
 */
public class VariableDeclarationHandler {
	
	/**
	 * Finds declaration of variable.
	 * @param pVarName The name of the variable.
	 * @param pVarebleDeclarationInfoList The list of known declarations of variables.
	 * @return
	 */
	public static VariableDeclarationInfo findDeclarationByName(String pVarName, List<VariableDeclarationInfo> pVarebleDeclarationInfoList) {
		for(VariableDeclarationInfo v : pVarebleDeclarationInfoList) {
			if(v.getVarName().equals(pVarName)) {
				return v;
			}
		}
		return null;
	}
	
	/**
	 * Clones the list of declarations.
	 * @param pVarebleDeclarationList The current list of declarations.
	 * @return
	 */
	public static List<VariableDeclarationInfo> clone(List<VariableDeclarationInfo> pVarebleDeclarationList){
		
		List<VariableDeclarationInfo> clonesList = new ArrayList<VariableDeclarationInfo>();
		
		for(VariableDeclarationInfo v : pVarebleDeclarationList) {
				clonesList.add(v.clone());
		}	
		return clonesList;
	}
	
	/**
	 * Add a new declaration.
	 * @param pVarName The name of the variable. 
	 * @param pVarDeclarationPlace The range in which the variable is declared.
	 * @param pVarebleDeclarationInfoList The list of known declarations.
	 */
	public static void addVaebleDeclarationRemoveOldIfExist(String pVarName, Range pVarDeclarationPlace, List<VariableDeclarationInfo> pVarebleDeclarationInfoList){
		pVarebleDeclarationInfoList.removeIf(s -> s.getVarName().equals(pVarName));
		pVarebleDeclarationInfoList.add(new VariableDeclarationInfo(pVarName, pVarDeclarationPlace));
	}
}
