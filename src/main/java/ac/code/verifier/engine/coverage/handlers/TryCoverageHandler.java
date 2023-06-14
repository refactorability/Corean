package ac.code.verifier.engine.coverage.handlers;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.TryStmt;

/**
 * The class SwitchCoverageHandler provides methods that handle coverage of TryStmt.
 *
 */
public class TryCoverageHandler {
	
	private Range mTryRange = null;
	private CoverageChildNode mFinalyBlock = null;
	private List<CoverageChildNode> mTryStmtList = new ArrayList<CoverageChildNode>();

	/**
	 * Constructor
	 * @param pTryStmt The TryStmt node.
	 */
	public TryCoverageHandler(TryStmt pTryStmt){
		mTryRange = pTryStmt.getRange().get();	
		boolean nextBlockShouldBeFinaly = false;

		for(Node n : pTryStmt.getChildNodes()) {
			if(n.getMetaModel().toString().equals("LineComment") || n.getMetaModel().toString().equals("BlockComment")) {
				continue;
			}
			
			if(n.getMetaModel().toString().equals("CatchClause")) {
				mTryStmtList.add(new CoverageChildNode(n.getRange().get(), n));
				continue;
			}
			
			if(n.getMetaModel().toString().equals("BlockStmt") && !nextBlockShouldBeFinaly) {
				mTryStmtList.add(new CoverageChildNode(n.getRange().get(), n));
				nextBlockShouldBeFinaly = true;
				continue;
			}
			
			if(n.getMetaModel().toString().equals("BlockStmt") && nextBlockShouldBeFinaly) {
				mFinalyBlock = new CoverageChildNode(n.getRange().get(), n);
				break;
			}
		}
	}
	
	/**
	 * Compares the current TryStmt node to other node by range.
	 * @param pTryRange The range of the other node.
	 * @return
	 */
	public boolean isSameTry(Range pTryRange){
		return mTryRange.equals(pTryRange);
	}
	
	/**
	 * Checks whether the entire node is covered.
	 * @return
	 */
	public boolean isCovered() {	
		if(mFinalyBlock != null && mFinalyBlock.isCovered()) {
			return true;
		}

		for(CoverageChildNode ccn : mTryStmtList) {
			if(!ccn.isCovered()) {
				return false;
			}
		}		
		return true;
	}
	
	/**
	 * Determines that the given range belonging to the node is covered.
	 * @param pRange The given range.
	 */
	public void setCoveredRange(Range pRange) {
		if(mFinalyBlock != null && mFinalyBlock.getRange().equals(pRange)) {
			mFinalyBlock.setCovered();
			return;
		}
		
		for(CoverageChildNode ccn : mTryStmtList) {
			if(ccn.getRange().equals(pRange)) {
				ccn.setCovered();
				return;
			}
		}
		System.out.println("ERROR Try Range " + pRange);		
	}
}

