package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.Range;

public class VarebleInfo {
	
	public VarebleInfo(String pVarName, Range pVarDeclarationPlace, VariableType pVarType) {
		this.varName = pVarName;
		this.varDeclarationPlace = pVarDeclarationPlace;
		this.varType = pVarType;
	}
	
	public String getVarName() {
		return varName;
	}
	
	public Range getVarDeclarationPlace() {
		return varDeclarationPlace;
	}
	
	public VariableType getVarType() {
		return varType;
	}	
	
	public List<Range> getUsesPlaces() {
		return UsesPlaces;
	}
	public void addUsePlace(Range pRange) {
		UsesPlaces.add(pRange);
	}
	
	public List<ChangeValInfo> getChangeValPlaces() {
		return ChangeValPlaces;
	}
	public void addChangeValPlace(ChangeValInfo pChangeValInfo) {
		ChangeValPlaces.add(pChangeValInfo);
	}
	
	private String varName;
	private Range varDeclarationPlace;
	private VariableType varType;
	private List<Range> UsesPlaces = new ArrayList<Range>();
	private List<ChangeValInfo> ChangeValPlaces = new ArrayList<ChangeValInfo>();
}
