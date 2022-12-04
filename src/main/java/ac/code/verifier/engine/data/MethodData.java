package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.github.javaparser.Range;
import ac.code.verifier.engine.CodeFragment;
import ac.code.verifier.engine.ComparableMarkRange;
import ac.code.verifier.engine.coverage.handlers.FragmentCoverageHandler;
import ac.code.verifier.engine.var.handlers.VariableInfoHandler;

public class MethodData {
	
	public String getMethodBody() {
		return methodBody;
	}
	public void setMethodBody(String methodBody) {
		this.methodBody = methodBody;
	}
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getMethodSignature() {
		return methodSignature;
	}
	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}
	
	public boolean isAbstract() {
		return isAbstract;
	}
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	
	public boolean isConstructor() {
		return isConstructor;
	}
	public void setConstructor(boolean isAbstract) {
		this.isConstructor = isAbstract;
	}
	
	public boolean isOverride() {
		return isOverride;
	}
	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
	}
	
	public int getNumOfParams() {
		return numOfParams;
	}
	public void setNumOfParams(int params) {
		this.numOfParams = params;
	}
	
	public String getMethodReturnType() {
		return methodReturnType;
	}
	public void setMethodReturnType(String methodReturnType) {
		this.methodReturnType = methodReturnType;
	}
	
	public String getMethodBelongToClass() {
		return methodBelongToClass;
	}
	public void setMethodBelongToClass(String methodBelongToClass) {
		this.methodBelongToClass = methodBelongToClass;
	}
	
	public boolean isAnnotated() {
		return isAnnotated;
	}
	public void setAnnotated(boolean isAnnotated) {
		this.isAnnotated = isAnnotated;
	}
	
	public boolean isMovable() {
		return isMovable;
	}
	public void setMovable(boolean pIsMovable) {
		this.isMovable = pIsMovable;
	}
	
	public boolean isLockedOnThis() {
		return isLockedOnThis;
	}
	
	public void setLockedOnThis(boolean isLockedOnThis) {
		this.isLockedOnThis = isLockedOnThis;
	}
	
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	
	public Range getRange() {
		return methodRange;
	}
	public void setRange(Range pRange) {
		methodRange = pRange;
	}
	
	public Range getBodyRange() {
		return methodBodyRange;
	}
	public void setBodyRange(Range pRange) {
		methodBodyRange = pRange;
	}
	
	public void addCodeFragmentMark(CodeFragment pCodeFragment, Range pRange) {
		switch (pCodeFragment) {
	    case BEGIN:
	    	beginFragmentsRange.add(new ComparableMarkRange(pRange, pCodeFragment));
	    	isFragmentsListReady = false;
	    	break;
	    case END:
	    	endFragmentsRange.add(new ComparableMarkRange(pRange, pCodeFragment));
	    	isFragmentsListReady = false;
		    break;
		}	
	}
	
	public List<Range> getCodeFragmentRanges() {		
		if(!isFragmentsListReady) {
			prepareCodeFragmentRanges();
			isFragmentsListReady = true;		
		}
		return codeFragmentRange;
	}
	
	public List<ComparableMarkRange> getSortedFragmentMarks() {
		List<ComparableMarkRange> allFragmentMarks = new ArrayList<ComparableMarkRange>();
		allFragmentMarks.addAll(beginFragmentsRange);
		allFragmentMarks.addAll(endFragmentsRange);		
		Collections.sort(allFragmentMarks);
		return allFragmentMarks;
	}
	
	private void prepareCodeFragmentRanges() {
		Collections.sort(beginFragmentsRange);
		Collections.sort(endFragmentsRange);
		
		int size = Math.min(beginFragmentsRange.size(), endFragmentsRange.size());
		codeFragmentRange = new ArrayList<Range>();
		
		for (int i=0; i<size; i++) {
			if(beginFragmentsRange.get(i).getRange().begin.isAfter(endFragmentsRange.get(i).getRange().end)) {
				System.out.println("[ERROR] invalid range");
				return;
			}
			codeFragmentRange.add(new Range(beginFragmentsRange.get(i).getRange().begin, endFragmentsRange.get(i).getRange().end));
		}
	}
	
	public void prepareRangeCoverageHandlers() {
		if(!isFragmentsListReady) {
			prepareCodeFragmentRanges();
			isFragmentsListReady = true;		
		}
		
		rangeCoverageHandlersList = new ArrayList<FragmentCoverageHandler>();
		for(Range r : codeFragmentRange) {
			rangeCoverageHandlersList.add(new FragmentCoverageHandler(r));
		}
	}
	
	public List<FragmentCoverageHandler> getRangeCoverageHandlersList() {
		return rangeCoverageHandlersList;
	}
	
	public VariableInfoHandler getVarebleInfoHandler() {
		return mVarebleInfoHandler;
	}
	
	@Override
	public String toString() {
		String result = "Method name: " +  methodName + System.lineSeparator();
		result += "Method belong to class: " +  methodBelongToClass + System.lineSeparator();
		result += "Method signature: " +  methodSignature + System.lineSeparator();
		result += "Method name: " +  methodName + System.lineSeparator();
		result += "Is abstract: " +  isAbstract + System.lineSeparator();
		result += "Is override: " +  isOverride + System.lineSeparator();
		result += "Is annotated: " +  isAnnotated + System.lineSeparator();
		result += "Is movable: " +  isMovable + System.lineSeparator();
		result += "Is constructor: " +  isConstructor + System.lineSeparator();
		result += "Method return type: " +  methodReturnType + System.lineSeparator();
		result += "is locked on this: " +  isLockedOnThis + System.lineSeparator();
		result += "Note: " +  Note + System.lineSeparator();
		result += "Range: " +  methodRange + System.lineSeparator();
		result += "BodyRange: " +  methodBodyRange + System.lineSeparator();
		return result;
	}
	
	private String methodBody = "";
	private String methodName = "";
	private String methodSignature = "";
	private boolean isAbstract = false;
	private boolean isConstructor = false;
	private boolean isOverride = false;
	private String methodReturnType = "";
	private String methodBelongToClass = "";
	private boolean isAnnotated = false;
	private boolean isMovable = false;
	private boolean isLockedOnThis = false;
	private String Note = "";
	private Range methodRange = null;
	private Range methodBodyRange = null;
	private List<Range> codeFragmentRange = new ArrayList<Range>();
	private int numOfParams;
	
	private List<FragmentCoverageHandler> rangeCoverageHandlersList = null;
	private List<ComparableMarkRange> beginFragmentsRange = new ArrayList<ComparableMarkRange>();
	private List<ComparableMarkRange> endFragmentsRange = new ArrayList<ComparableMarkRange>();
	private boolean isFragmentsListReady = false;	
	private VariableInfoHandler mVarebleInfoHandler = new VariableInfoHandler();

}
