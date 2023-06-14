package ac.code.verifier.engine.coverage.handlers;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;

/**
 * The class FragmentCoverageHandler provides methods that handle coverage of fragment.
 *
 */
public class FragmentCoverageHandler {
	
	private Range mRange;
	private boolean isCovered;
	private boolean isExaminedExpressionExists;
	private List<IfCoverageHandler> ifCoverageList;
	private List<SwitchCoverageHandler> SwitchCoverageList;
	private List<TryCoverageHandler> TryCoverageList;
	
	/**
	 * Constructor
	 * @param pRange The range of the fragment.
	 */
	public FragmentCoverageHandler(Range pRange){
		mRange = pRange;
		isCovered = false;
		isExaminedExpressionExists = false;
		ifCoverageList = new ArrayList<IfCoverageHandler>();
		SwitchCoverageList = new ArrayList<SwitchCoverageHandler>();
		TryCoverageList = new ArrayList<TryCoverageHandler>();
	}
	
	/**
	 * Marks covering of a child node for IfStmt node.
	 * @param pIfStmt The parent IfStmt node.
	 * @param pChildRange The range of a child node.
	 */
	public void coverChildOfIf(IfStmt pIfStmt, Range pChildRange) {	
		for(IfCoverageHandler ifCoverageHandler : ifCoverageList) {
			if(ifCoverageHandler.isSameIf(pIfStmt.getRange().get())) {
				ifCoverageHandler.setCoveredRange(pChildRange);
				return;
			}
		}
		//This is a new if statement
		IfCoverageHandler newIfCoverageHandler = new IfCoverageHandler(pIfStmt);
		newIfCoverageHandler.setCoveredRange(pChildRange);
		ifCoverageList.add(newIfCoverageHandler);
	}
	
	/**
	 * Marks covering of a child node for SwitchStmt node.
	 * @param pSwitchStmt The parent SwitchStmt node.
	 * @param pChildRange The range of a child node.
	 */
	public void coverChildOfSwitch(SwitchStmt pSwitchStmt, Range pChildRange) {	
		for(SwitchCoverageHandler switchCoverageHandler : SwitchCoverageList) {
			if(switchCoverageHandler.isSameSwitch(pSwitchStmt.getRange().get())) {
				switchCoverageHandler.setCoveredRange(pChildRange);
				return;
			}
		}
		//This is a new switch statement
		SwitchCoverageHandler newSwitchCoverageHandler = new SwitchCoverageHandler(pSwitchStmt);
		newSwitchCoverageHandler.setCoveredRange(pChildRange);
		SwitchCoverageList.add(newSwitchCoverageHandler);
	}
	
	/**
	 * Marks covering of a child node for TryStmt node.
	 * @param pTryStmt The parent TryStmt node.
	 * @param pChildRange The range of a child node.
	 */
	public void coverChildOfTry(TryStmt pTryStmt, Range pChildRange) {	
		for(TryCoverageHandler tryCoverageHandler : TryCoverageList) {
			if(tryCoverageHandler.isSameTry(pTryStmt.getRange().get())) {
				tryCoverageHandler.setCoveredRange(pChildRange);
				return;
			}
		}
		//This is a new try
		TryCoverageHandler newTryCoverageHandler = new TryCoverageHandler(pTryStmt);
		newTryCoverageHandler.setCoveredRange(pChildRange);
		TryCoverageList.add(newTryCoverageHandler);
	}
	
	/**
	 * Checks whether the entire IfStmt node is covered.
	 * @param pIfStmt The tested IfStmt node.
	 * @return
	 */
	public boolean isIfCovered(IfStmt pIfStmt) {	
		for(IfCoverageHandler ifCoverageHandler : ifCoverageList) {
			if(ifCoverageHandler.isSameIf(pIfStmt.getRange().get())) {
				return ifCoverageHandler.isCovered();
			}
		}
		return false;
	}
	
	/**
	 * Checks whether the entire SwitchStmt node is covered.
	 * @param pSwitchStmt The tested SwitchStmt node.
	 * @return
	 */
	public boolean isSwitchCovered(SwitchStmt pSwitchStmt) {	
		for(SwitchCoverageHandler switchCoverageHandler : SwitchCoverageList) {
			if(switchCoverageHandler.isSameSwitch(pSwitchStmt.getRange().get())) {
				return switchCoverageHandler.isCovered();
			}
		}
		return false;
	}
	
	/**
	 * Checks whether the entire TryStmt node is covered.
	 * @param pTryStmt The tested TryStmt node.
	 * @return
	 */
	public boolean isTryCovered(TryStmt pTryStmt) {	
		for(TryCoverageHandler tryCoverageHandler : TryCoverageList) {
			if(tryCoverageHandler.isSameTry(pTryStmt.getRange().get())) {
				return tryCoverageHandler.isCovered();
			}
		}
		return false;
	}
	
	/**
	 * Returns the range of the node.
	 * @return
	 */
	public Range getRange() {
		return mRange;
	}
	
	/**
	 * Checks whether the entire node is covered.
	 * @return
	 */
	public boolean isCovered() {
		return isCovered;
	}
	
	/**
	 * Determines that the entire node is covered.
	 */
	public void setCovered() {
		isCovered = true;
	}
	
	/**
	 * Checks whether the examined expression exists.
	 * @return
	 */
	public boolean isExaminedExpressionExists() {
		return isExaminedExpressionExists;
	}
	
	/**
	 * Determines that the examined expression exists.
	 */
	public void setExaminedExpressionExists() {
		isExaminedExpressionExists = true;
	}
}
