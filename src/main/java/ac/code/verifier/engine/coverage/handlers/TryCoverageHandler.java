package ac.code.verifier.engine.coverage.handlers;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.TryStmt;

public class TryCoverageHandler {
	
	private Range mTryRange = null;
	private CoverageChildNode mFinalyBlock = null;
	private List<CoverageChildNode> mTryStmtList = new ArrayList<CoverageChildNode>();

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
	
	public boolean isSameTry(Range pTryRange){
		return mTryRange.equals(pTryRange);
	}
	
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

