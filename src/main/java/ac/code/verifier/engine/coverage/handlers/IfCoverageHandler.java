package ac.code.verifier.engine.coverage.handlers;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.IfStmt;

public class IfCoverageHandler {
	private Range mIfRange = null;
	private CoverageChildNode mFirstChild = null;
	private CoverageChildNode mSecondChild = null;
	private CoverageChildNode mThirdChild = null;
		
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
	
	public boolean isSameIf(Range pIfRange){
		return mIfRange.equals(pIfRange);
	}
	
	public boolean isCovered() {
		if(mFirstChild.isCovered) {
			return true;
		}
		if(mThirdChild == null) {
			return false;
		}
		return (mSecondChild.isCovered && mThirdChild.isCovered);
	}
	
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
