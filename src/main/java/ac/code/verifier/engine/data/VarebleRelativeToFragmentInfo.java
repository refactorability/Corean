package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

public class VarebleRelativeToFragmentInfo {
	
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public Range getVarDeclarationPlace() {
		return varDeclarationPlace;
	}
	public void setVarDeclarationPlace(Range varDeclarationPlace) {
		this.varDeclarationPlace = varDeclarationPlace;
	}
	public boolean isDeclaredBeforeFragment() {
		return isDeclaredBeforeFragment;
	}
	public void setDeclaredBeforeFragment(boolean isDeclaredBeforeFragment) {
		this.isDeclaredBeforeFragment = isDeclaredBeforeFragment;
	}
	public boolean isDeclaredInFragment() {
		return isDeclaredInFragment;
	}
	public void setDeclaredInFragment(boolean isDeclaredInFragment) {
		this.isDeclaredInFragment = isDeclaredInFragment;
	}
	public boolean isChengedInFragment() {
		return isChengedInFragment;
	}
	public void setChengedInFragment(boolean isChengedInFragment) {
		this.isChengedInFragment = isChengedInFragment;
	}
	public boolean isUsedAfterFragment() {
		return isUsedAfterFragment;
	}
	public void setUsedAfterFragment(boolean isUsedAfterFragment) {
		this.isUsedAfterFragment = isUsedAfterFragment;
	}
	
	private String varName;
	private Range varDeclarationPlace;
	private boolean isDeclaredBeforeFragment;
	private boolean isDeclaredInFragment;
	private boolean isChengedInFragment;
	private boolean isUsedAfterFragment;
}
