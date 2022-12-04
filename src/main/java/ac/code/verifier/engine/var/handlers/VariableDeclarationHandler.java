package ac.code.verifier.engine.var.handlers;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import ac.code.verifier.engine.data.VariableDeclarationInfo;

public class VariableDeclarationHandler {
	
	public static VariableDeclarationInfo findDeclarationByName(String pVarName, List<VariableDeclarationInfo> pVarebleDeclarationInfoList) {
		for(VariableDeclarationInfo v : pVarebleDeclarationInfoList) {
			if(v.getVarName().equals(pVarName)) {
				return v;
			}
		}
		return null;
	}
	
	public static List<VariableDeclarationInfo> clone(List<VariableDeclarationInfo> pVarebleDeclarationList){
		
		List<VariableDeclarationInfo> clonesList = new ArrayList<VariableDeclarationInfo>();
		
		for(VariableDeclarationInfo v : pVarebleDeclarationList) {
				clonesList.add(v.clone());
		}	
		return clonesList;
	}
	
	public static void addVaebleDeclarationRemoveOldIfExist(String pVarName, Range pVarDeclarationPlace, List<VariableDeclarationInfo> pVarebleDeclarationInfoList){
		pVarebleDeclarationInfoList.removeIf(s -> s.getVarName().equals(pVarName));
		pVarebleDeclarationInfoList.add(new VariableDeclarationInfo(pVarName, pVarDeclarationPlace));
	}
}
