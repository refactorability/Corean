package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.github.javaparser.Range;
import ac.code.verifier.engine.CodeFragment;
import ac.code.verifier.engine.ComparableMarkRange;
import ac.code.verifier.engine.coverage.handlers.FragmentCoverageHandler;
import ac.code.verifier.engine.var.handlers.VariableInfoHandler;

/**
 * The class MethodData stores an information about a method.
 *
 */
public class MethodData {
	
	/**
	 * Returns the body of the method.
	 * @return
	 */
	public String getMethodBody() {
		return methodBody;
	}
	
	/**
	 * Set the bode of the method.
	 * @param methodBody The new body.
	 */
	public void setMethodBody(String methodBody) {
		this.methodBody = methodBody;
	}
	
	/**
	 * Returns the name of the method.
	 * @return
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Set the name of the method.
	 * @param methodName The new name. 
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	/**
	 * Returns the signature of the method.
	 * @return
	 */
	public String getMethodSignature() {
		return methodSignature;
	}
	
	/**
	 * Set the signature of the method.
	 * @param methodSignature The new signature.
	 */
	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}
	
	/**
	 * Checks whether the method is abstract.
	 * @return
	 */
	public boolean isAbstract() {
		return isAbstract;
	}
	
	/**
	 * Determines whether the method is abstract.
	 * @param isAbstract The new value.
	 */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	
	/**
	 * Checks whether the method is constructor.
	 * @return
	 */
	public boolean isConstructor() {
		return isConstructor;
	}
	
	/**
	 * Determines whether the method is constructor.
	 * @param isConstructor The new value.
	 */
	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}
	
	/**
	 * Checks whether the method overrides a super method.
	 * @return
	 */
	public boolean isOverride() {
		return isOverride;
	}
	
	/**
	 * Determines whether the method overrides a super method.
	 * @param isOverride The new value.
	 */
	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
	}
	
	/**
	 * Returns the number of the parameters.
	 * @return
	 */
	public int getNumOfParams() {
		return numOfParams;
	}
	
	/**
	 * Set the number of the parameters.
	 * @param params The new value.
	 */
	public void setNumOfParams(int params) {
		this.numOfParams = params;
	}
	
	/**
	 * Returns the type of the return value.
	 * @return
	 */
	public String getMethodReturnType() {
		return methodReturnType;
	}
	
	/**
	 * Set the type of the return value.
	 * @param methodReturnType The new value.
	 */
	public void setMethodReturnType(String methodReturnType) {
		this.methodReturnType = methodReturnType;
	}
	
	/**
	 * Returns the name of the class.
	 * @return
	 */
	public String getMethodBelongToClass() {
		return methodBelongToClass;
	}
	
	/**
	 * Set the name of the class.
	 * @param methodBelongToClass The name of the class
	 */
	public void setMethodBelongToClass(String methodBelongToClass) {
		this.methodBelongToClass = methodBelongToClass;
	}
	
	/**
	 * Checks whether the method is annotated with the relevant annotation.
	 * @return
	 */
	public boolean isAnnotated() {
		return isAnnotated;
	}
	
	/**
	 * Determines whether the method is annotated with the relevant annotation.
	 * @param isAnnotated The new value.
	 */
	public void setAnnotated(boolean isAnnotated) {
		this.isAnnotated = isAnnotated;
	}
	
	/**
	 * Checks whether the method is annotated with MovableMethod annotation.
	 * @return
	 */
	public boolean isMovable() {
		return isMovable;
	}
	
	/**
	 * Determines whether the method is annotated with MovableMethod annotation.
	 * @param pIsMovable The new value.
	 */
	public void setMovable(boolean pIsMovable) {
		this.isMovable = pIsMovable;
	}
	
	/**
	 * Checks whether the method is Locked on the current object.
	 * @return
	 */
	public boolean isLockedOnThis() {
		return isLockedOnThis;
	}
	
	/**
	 * Determines whether the method is Locked on the current object.
	 * @param isLockedOnThis The new value.
	 */
	public void setLockedOnThis(boolean isLockedOnThis) {
		this.isLockedOnThis = isLockedOnThis;
	}
	
	/**
	 * Returns the note.
	 * @return
	 */
	public String getNote() {
		return Note;
	}
	
	/**
	 * Set a new note.
	 * @param note The new value.
	 */
	public void setNote(String note) {
		Note = note;
	}
	
	/**
	 * Returns the range of the method.
	 * @return
	 */
	public Range getRange() {
		return methodRange;
	}
	
	/**
	 * Set range of the method.
	 * @param pRange The new value.
	 */
	public void setRange(Range pRange) {
		methodRange = pRange;
	}
	
	/**
	 * Returns the range of the bode of the method.
	 * @return
	 */
	public Range getBodyRange() {
		return methodBodyRange;
	}
	
	/**
	 * Set range of the of the bode of the method..
	 * @param pRange The new value.
	 */
	public void setBodyRange(Range pRange) {
		methodBodyRange = pRange;
	}
	
	/**
	 * Adds mark of code fragment.
	 * @param pCodeFragment The mark.
	 * @param pRange The range of the mark.
	 */
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
	
	/**
	 * Returns the list of fragments.
	 * @return a list of the ranges of the fragments.
	 */
	public List<Range> getCodeFragmentRanges() {		
		if(!isFragmentsListReady) {
			prepareCodeFragmentRanges();
			isFragmentsListReady = true;		
		}
		return codeFragmentRange;
	}
	
	/**
	 * Returns list of a sorted marks
	 * @return
	 */
	public List<ComparableMarkRange> getSortedFragmentMarks() {
		List<ComparableMarkRange> allFragmentMarks = new ArrayList<ComparableMarkRange>();
		allFragmentMarks.addAll(beginFragmentsRange);
		allFragmentMarks.addAll(endFragmentsRange);		
		Collections.sort(allFragmentMarks);
		return allFragmentMarks;
	}
	
	/**
	 * If required - sort the list
	 */
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
	
	/**
	 * Prepare the range coverage handlers
	 */
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
	
	/**
	 * Returns list of range coverage handlers.
	 * @return
	 */
	public List<FragmentCoverageHandler> getRangeCoverageHandlersList() {
		return rangeCoverageHandlersList;
	}
	
	/**
	 * Returns variable information handler.
	 * @return
	 */
	public VariableInfoHandler getVarebleInfoHandler() {
		return mVarebleInfoHandler;
	}
	
	/**
	 * Returns a string with all the information about the method.
	 */
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
