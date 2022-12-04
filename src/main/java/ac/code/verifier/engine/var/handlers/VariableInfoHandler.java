package ac.code.verifier.engine.var.handlers;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.Position;
import com.github.javaparser.Range;

import ac.code.verifier.engine.data.ChangeValInfo;
import ac.code.verifier.engine.data.VarebleInfo;
import ac.code.verifier.engine.data.VariableType;

public class VariableInfoHandler {
	
	private List<VarebleInfo> mVarebleInfoList = new ArrayList<VarebleInfo>();
	
	private Range UNDEFINE_RANGE = new Range(new Position(0, 0), new Position(0, 1)); 

	public List<VarebleInfo> getVarebleInfoList() {
		return mVarebleInfoList;
	}
	
	public void addVariableByDeclaration(String pVarName, Range pVarDeclarationPlace, VariableType pVarebleType) {
		for(VarebleInfo vi : mVarebleInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarDeclarationPlace().equals(pVarDeclarationPlace)) {
				System.out.println("[ERROR-DEBUG:] " + pVarName + " already exists.");
				return;
			} 
		}
		VarebleInfo newVarebleInfo = new VarebleInfo(pVarName, pVarDeclarationPlace, pVarebleType);
		mVarebleInfoList.add(newVarebleInfo);
		
	}
	
	public void addVariableUse(String pVarName, Range pVarDeclarationPlace, Range varUsePlace) {
		 
		if(pVarDeclarationPlace == null) {
			pVarDeclarationPlace = UNDEFINE_RANGE;
		} 
	  
		for(VarebleInfo vi : mVarebleInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarDeclarationPlace().equals(pVarDeclarationPlace)) {
				vi.addUsePlace(varUsePlace);
				return;
			}
		}
		VarebleInfo newVarebleInfo = new VarebleInfo(pVarName, pVarDeclarationPlace, VariableType.UNKNOWN);
		newVarebleInfo.addUsePlace(varUsePlace);
		mVarebleInfoList.add(newVarebleInfo);
	}
	
	public void addInstanceVariableUse(String pVarName, Range varUsePlace) {	
		for(VarebleInfo vi : mVarebleInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarType() == VariableType.INSTANCE_VARIABLE) {
				vi.addUsePlace(varUsePlace);
				return;
			}
		}
	}
	
	public void addVariableChangeVal(String pVarName, Range pVarDeclarationPlace, Range varChangeValPlace, boolean pIsUnaryChange) {
		if(pVarDeclarationPlace == null) {
			pVarDeclarationPlace = UNDEFINE_RANGE;
		}
		
		for(VarebleInfo vi : mVarebleInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarDeclarationPlace().equals(pVarDeclarationPlace)) {
				vi.addChangeValPlace(new ChangeValInfo(varChangeValPlace, pIsUnaryChange));
				return;
			}
		}
		VarebleInfo newVarebleInfo = new VarebleInfo(pVarName, pVarDeclarationPlace, VariableType.UNKNOWN);
		newVarebleInfo.addChangeValPlace(new ChangeValInfo(varChangeValPlace, pIsUnaryChange));
		mVarebleInfoList.add(newVarebleInfo);
	}
	
	public void addInstanceVariableChangeVal(String pVarName, Range varChangeValPlace, boolean pIsUnaryChange) {	
		for(VarebleInfo vi : mVarebleInfoList) {
			if(vi.getVarName().equals(pVarName) && vi.getVarType() == VariableType.INSTANCE_VARIABLE) {
				vi.addChangeValPlace(new ChangeValInfo(varChangeValPlace, pIsUnaryChange));
				return;
			}
		}
	}

}
