package ac.code.verifier.engine.coverage.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.SwitchStmt;

/**
 * The class SwitchCoverageHandler provides methods that handle coverage of SwitchStmt.
 *
 */
public class SwitchCoverageHandler {
	
	private Range mSwitchRange = null;
	private List<CoverageChildNode> mSwitchStmtList = new ArrayList<CoverageChildNode>();

	/**
	 * Constructor
	 * @param pSwitchStmt The SwitchStmt node.
	 */
	public SwitchCoverageHandler(SwitchStmt pSwitchStmt){
		mSwitchRange = pSwitchStmt.getRange().get();

		for(Node n : pSwitchStmt.getChildNodes()) {
			if(n.getMetaModel().toString().equals("LineComment") || n.getMetaModel().toString().equals("BlockComment")) {
				continue;
			}				
			mSwitchStmtList.add(new CoverageChildNode(n.getRange().get(), n));	
		}
		Collections.sort(mSwitchStmtList);
	}
	
	/**
	 * Compares the current SwitchStmt node to other node by range.
	 * @param pSwitchRange The range of the other node.
	 * @return
	 */
	public boolean isSameSwitch(Range pSwitchRange){
		return mSwitchRange.equals(pSwitchRange);
	}
	
	/**
	 * Checks whether the entire node is covered.
	 * @return
	 */
	public boolean isCovered() {
		if(mSwitchStmtList.get(0).isCovered) {
			return true;
		}
		
		for(CoverageChildNode ccn : mSwitchStmtList) {
			if(!ccn.isCovered() && ccn.isTypeExists("BreakStmt")) {
				return false;
			}
		}		
		return mSwitchStmtList.get(mSwitchStmtList.size()-1).isCovered;	
	}
	
	/**
	 * Determines that the given range belonging to the node is covered.
	 * @param pRange The given range.
	 */
	public void setCoveredRange(Range pRange) {
		for(CoverageChildNode ccn : mSwitchStmtList) {
			if(ccn.getRange().equals(pRange)) {
				ccn.setCovered();
				return;
			}
		}
		System.out.println("ERROR Switch Range");		
	}
}
