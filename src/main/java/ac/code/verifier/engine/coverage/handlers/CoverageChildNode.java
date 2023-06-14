package ac.code.verifier.engine.coverage.handlers;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;

/**
 *  The class CoverageChildNode implements Comparable interface to enable sorting of nodes by range. In addition, it adds the ability to determine whether the node is covered.
 */
public class CoverageChildNode implements Comparable<CoverageChildNode> {
	private Range mRange;
	private Node mChiledRootNode;
	boolean isCovered;
	
	/**
	 * Constructor
	 * @param pRange The range of the node
	 * @param pChiledRootNode The node
	 */
	CoverageChildNode(Range pRange, Node pChiledRootNode){
		mRange = pRange;
		mChiledRootNode = pChiledRootNode;
		isCovered = false;
	}
	
	/**
	 * Compares two nodes by range.
	 */
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
	
	/**
	 * Determines whether the node is covered.
	 */
	void setCovered() {
		isCovered = true;
	}
	
	/**
	 * Returns the range of the node.
	 * @return
	 */
	public Range getRange() {
		return mRange;
	}

	/**
	 * Returns the type of the node.
	 * @return
	 */
	public String getType() {
		return mChiledRootNode.getMetaModel().toString();
	}

	/**
	 * Checks whether the node is covered.
	 * @return
	 */
	public boolean isCovered() {
		return isCovered;
	}
	
	/**
	 * Checks if it is the same range.
	 * @param pChildRange The range
	 * @return
	 */
	public boolean isSameChild(Range pChildRange){
		return mRange.equals(pChildRange);
	}
	
	/**
	 * Checks whether a node of a given type exists.
	 * @param pTypeName The type
	 * @return
	 */
	public boolean isTypeExists(String pTypeName) {
		return isTypeExists(pTypeName, mChiledRootNode);
	}
	
	/**
	 * Checks whether a node of a given type exists.
	 * @param pTypeName The type.
	 * @param pRootNode The root node.
	 * @return
	 */
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
