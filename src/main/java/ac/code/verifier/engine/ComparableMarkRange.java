package ac.code.verifier.engine;

import com.github.javaparser.Range;

public class ComparableMarkRange implements Comparable<ComparableMarkRange> {
	
	Range mRange = null;
	CodeFragment mCodeFragment;

	public ComparableMarkRange(Range pRange, CodeFragment pCodeFragment){
		mRange = pRange;
		mCodeFragment = pCodeFragment;
	}

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

	public Range getRange() {
		return mRange;
	}
	
	public CodeFragment getCodeFragmentType() {
		return mCodeFragment;
	}
}
