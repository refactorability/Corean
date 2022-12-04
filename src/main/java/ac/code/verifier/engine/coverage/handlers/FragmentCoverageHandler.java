package ac.code.verifier.engine.coverage.handlers;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;

public class FragmentCoverageHandler {
	
	private Range mRange;
	private boolean isCovered;
	private boolean isExaminedExpressionExists;
	private List<IfCoverageHandler> ifCoverageList;
	private List<SwitchCoverageHandler> SwitchCoverageList;
	private List<TryCoverageHandler> TryCoverageList;
	
	public FragmentCoverageHandler(Range pRange){
		mRange = pRange;
		isCovered = false;
		isExaminedExpressionExists = false;
		ifCoverageList = new ArrayList<IfCoverageHandler>();
		SwitchCoverageList = new ArrayList<SwitchCoverageHandler>();
		TryCoverageList = new ArrayList<TryCoverageHandler>();
	}
	
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
	
	public boolean isIfCovered(IfStmt pIfStmt) {	
		for(IfCoverageHandler ifCoverageHandler : ifCoverageList) {
			if(ifCoverageHandler.isSameIf(pIfStmt.getRange().get())) {
				return ifCoverageHandler.isCovered();
			}
		}
		return false;
	}
	
	public boolean isSwitchCovered(SwitchStmt pSwitchStmt) {	
		for(SwitchCoverageHandler switchCoverageHandler : SwitchCoverageList) {
			if(switchCoverageHandler.isSameSwitch(pSwitchStmt.getRange().get())) {
				return switchCoverageHandler.isCovered();
			}
		}
		return false;
	}
	
	public boolean isTryCovered(TryStmt pTryStmt) {	
		for(TryCoverageHandler tryCoverageHandler : TryCoverageList) {
			if(tryCoverageHandler.isSameTry(pTryStmt.getRange().get())) {
				return tryCoverageHandler.isCovered();
			}
		}
		return false;
	}
	
	public Range getRange() {
		return mRange;
	}
	
	public boolean isCovered() {
		return isCovered;
	}
	
	public void setCovered() {
		isCovered = true;
	}
	
	public boolean isExaminedExpressionExists() {
		return isExaminedExpressionExists;
	}
	
	public void setExaminedExpressionExists() {
		isExaminedExpressionExists = true;
	}
}
