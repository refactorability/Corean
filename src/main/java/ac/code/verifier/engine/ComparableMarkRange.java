package ac.code.verifier.engine;

import com.github.javaparser.Range;
/**
 * The class ComparableMarkRange implements Comparable interface for ranges.
 *
 */
public class ComparableMarkRange implements Comparable<ComparableMarkRange> {
	
	Range mRange = null;
	CodeFragment mCodeFragment;

	public ComparableMarkRange(Range pRange, CodeFragment pCodeFragment){
		mRange = pRange;
		mCodeFragment = pCodeFragment;
	}

	/**
	 * Compares two nodes by range.
	 */
	@Override
	public int compareTo(ComparableMarkRange o) {
		if(mRange.begin.isBeforeOrEqual(o.getRange().begin)) {
			return -1;
		}
		if(mRange.begin.equals(o.getRange().begin)) {
			return 0;
		}
		return 1;
	}

	/**
	 * Returns the range.
	 * @return
	 */
	public Range getRange() {
		return mRange;
	}
	
	/**
	 * Returns the type.
	 * @return
	 */
	public CodeFragment getCodeFragmentType() {
		return mCodeFragment;
	}
}
