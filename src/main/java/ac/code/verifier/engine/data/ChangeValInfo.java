package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

public class ChangeValInfo {

	private Range mChangePlace;
	private boolean isUnaryChange;
	
	public ChangeValInfo(Range pChangePlace, boolean pIsUnaryChange) {
		 mChangePlace = pChangePlace;
		 isUnaryChange = pIsUnaryChange;
	}

	public Range getChangePlace() {
		return mChangePlace;
	}

	public boolean isUnaryChange() {
		return isUnaryChange;
	}
}
