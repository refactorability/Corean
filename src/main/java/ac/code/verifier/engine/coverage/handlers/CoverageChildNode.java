package ac.code.verifier.engine.coverage.handlers;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;

public class CoverageChildNode implements Comparable<CoverageChildNode> {
	private Range mRange;
	private Node mChiledRootNode;
	boolean isCovered;
	
	CoverageChildNode(Range pRange, Node pChiledRootNode){
		mRange = pRange;
		mChiledRootNode = pChiledRootNode;
		isCovered = false;
	}
	
	@Override
	public int compareTo(CoverageChildNode o) {
		if(mRange.begin.isBeforeOrEqual(o.getRange().begin)) {
			return -1;
		}
		if(mRange.equals(o.getRange())) {
			return 0;
		}
		return 1;
	}
	
	void setCovered() {
		isCovered = true;
	}
	
	public Range getRange() {
		return mRange;
	}

	public String getType() {
		return mChiledRootNode.getMetaModel().toString();
	}

	public boolean isCovered() {
		return isCovered;
	}
	
	public boolean isSameChild(Range pChildRange){
		return mRange.equals(pChildRange);
	}
	
	public boolean isTypeExists(String pTypeName) {
		return isTypeExists(pTypeName, mChiledRootNode);
	}
	
	private boolean isTypeExists(String pTypeName, Node pRootNode) {		
		if(pRootNode.getMetaModel().toString().equals(pTypeName)) {
			return true;
		}
		
		for(Node n : pRootNode.getChildNodes()) {
			if(isTypeExists(pTypeName, n)) {
				return true;
			}
		}
		return false;
	}
}
