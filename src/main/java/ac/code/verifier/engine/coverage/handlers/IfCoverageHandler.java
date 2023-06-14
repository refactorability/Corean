package ac.code.verifier.engine.coverage.handlers;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.IfStmt;

/**
 * The class IfCoverageHandler provides methods that handle coverage of IfStmt.
 *
 */
public class IfCoverageHandler {
	private Range mIfRange = null;
	private CoverageChildNode mFirstChild = null;
	private CoverageChildNode mSecondChild = null;
	private CoverageChildNode mThirdChild = null;
		
	/**
	 * Constructor
	 * @param pIfStmt The IfStmt node.
	 */
	public IfCoverageHandler(IfStmt pIfStmt){
		mIfRange = pIfStmt.getRange().get();
		int i = 1;
		for(Node n : pIfStmt.getChildNodes()) {
			if(n.getMetaModel().toString().equals("LineComment") || n.getMetaModel().toString().equals("BlockComment")) {
				continue;
			}			
			
			if(i==1) {
				mFirstChild = new CoverageChildNode(n.getRange().get(), n);
				i++;
			}
			else if(i==2) {;
				mSecondChild = new CoverageChildNode(n.getRange().get(), n);
				i++;						
			}
			else if(i==3) {
				mThirdChild = new CoverageChildNode(n.getRange().get(), n);
				i++;
			}
		}
	}
	
	/**
	 * Compares the current IfStmt node to other node by range.
	 * @param pIfRange The range of the other node.
	 * @return
	 */
	public boolean isSameIf(Range pIfRange){
		return mIfRange.equals(pIfRange);
	}
	
	/**
	 * Checks whether the entire node is covered.
	 * @return
	 */
	public boolean isCovered() {
		if(mFirstChild.isCovered) {
			return true;
		}
		if(mThirdChild == null) {
			return false;
		}
		return (mSecondChild.isCovered && mThirdChild.isCovered);
	}
	
	/**
	 * Determines that the given range belonging to the node is covered.
	 * @param pRange The given range.
	 */
	public void setCoveredRange(Range pRange) {
		if(mFirstChild.isSameChild(pRange)) {
			mFirstChild.setCovered();
		}
		else if(mSecondChild.isSameChild(pRange)) {
			mSecondChild.setCovered();
		}
		else if((mThirdChild != null) && mThirdChild.isSameChild(pRange)) {
			mThirdChild.setCovered();
		}
		else {
			System.out.println("ERROR if Range: " + pRange);
		}
	}
}
