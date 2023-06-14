package ac.code.verifier.engine.data;

import com.github.javaparser.Range;

/**
 * The class ChangeValInfo stores information about the value changes of the variable.
 *
 */
public class ChangeValInfo {

	private Range mChangePlace;
	private boolean isUnaryChange;
	
	/**
	 * Constructor
	 * @param pChangePlace The range in which the variable changes value.
	 * @param pIsUnaryChange Indicates whether it is unary change.
	 */
	public ChangeValInfo(Range pChangePlace, boolean pIsUnaryChange) {
		 mChangePlace = pChangePlace;
		 isUnaryChange = pIsUnaryChange;
	}

	/**
	 * Returns the range of the variable change.
	 * @return
	 */
	public Range getChangePlace() {
		return mChangePlace;
	}

	/**
	 * Checks whether it is unary change.
	 * @return
	 */
	public boolean isUnaryChange() {
		return isUnaryChange;
	}
}
