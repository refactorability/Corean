package ac.code.verifier.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.visitor.VoidVisitor;

import ac.code.verifier.engine.data.AssignExprData;
import ac.code.verifier.engine.data.MethodCallFromThisData;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.visitors.AssignExprCollector;
import ac.code.verifier.engine.visitors.MethodCallCollector;
import ac.code.verifier.engine.visitors.UnaryExprCollector;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import ac.exceptions.FailedVerifyException;

/**
 * The MethodPropertiesParser class adds checks related to methods.
 *
 */
public class MethodPropertiesParser extends ParserHelper {

	/**
	 * 
	 * @param pAnotation The relevant annotation
	 * @param pFilePath The path to the file containing the annotation.
	 * @throws IOException
	 * @throws FailedVerifyException
	 */
	public MethodPropertiesParser(String pAnotation, String pFilePath) throws IOException, FailedVerifyException {
		super(pAnotation, pFilePath);
	}
	
	/**
	 * 
	 * @param pAnotation The relevant annotation
	 * @param pFilePath The path to the file containing the annotation.
	 * @param pReferToComments Determines whether to include the comments in AST. 
	 * @throws IOException
	 * @throws FailedVerifyException
	 */
	MethodPropertiesParser(String pAnotation, String pFilePath, boolean pReferToComments) throws IOException, FailedVerifyException {
		super(pAnotation, pFilePath, pReferToComments);
	}

	/**
	 * Checks that the method is not locked on the current object.
	 * @return
	 */
	public VerifyResult checkLockedOnThis() {
		
		for(MethodData md : mMethodData) {
			if(md.isAnnotated() && md.isLockedOnThis()) {
				return new VerifyResult(Result.ERROR, "The method " + md.getMethodName() + " of the class "
						+ md.getMethodBelongToClass() + ","
						+ " is locked on \"this\". This can cause a change in behavior if the method is moved to another class.", md.getMethodSignature(), md.getMethodBelongToClass());
			}	
		}
		
		return new VerifyResult(Result.OK, "", null, null);		
	}
	
	/**
	 * Checks that the method does not override a method of a superclass.
	 * @return
	 */
	public VerifyResult checkOverride() {
		
		for(MethodData md : mMethodData) {
			if(md.isAnnotated() && md.isOverride()) {
				return new VerifyResult(Result.ERROR, "The method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " is an override method of its super class. This can change the behavior of an inherited class.", md.getMethodSignature(), md.getMethodBelongToClass());
			}	
		}
		
		return new VerifyResult(Result.OK, "", null, null);	
	}
	
	/**
	 * Checks that the method does not call a private method that is not marked with @MovableMethod.
	 * @return
	 */
	public VerifyResult checkCalledMethods() {	
		List<MethodCallFromThisData> methodCall = new ArrayList<>();
		VoidVisitor<List<MethodCallFromThisData>> mcv = new MethodCallCollector();
		mcv.visit(mRootCompilationUnit, methodCall);
		
		for(MethodCallFromThisData md : methodCall) {
			if(isAnotatedOrUknown(md.getParentMethodClassName(), md.getParentMethodSignature()) && !isAnotatedOrUknown(md.getParentMethodClassName(), md.getCalledMethodName(), md.getNumOfParams())) {
				return new VerifyResult(Result.ERROR, "The method "+ md.getmParentMethodName() + " of the class " + md.getParentMethodClassName() + "," + " call not movable method " + md.getCalledMethodName() + ".", md.getParentMethodSignature(), md.getParentMethodClassName());
			}		
		}
		
		return new VerifyResult(Result.OK, "", null, null);	
	}
	
	/**
	 * Checks that the method does not assign a value to an instance variable.
	 * @return
	 */
	public VerifyResult checkAssignToInstanceVariables() {

		List<AssignExprData> UnaryAssignExpr = new ArrayList<>();
		VoidVisitor<List<AssignExprData>> ue = new UnaryExprCollector();
		ue.visit(mRootCompilationUnit, UnaryAssignExpr);

		List<AssignExprData> AssignExpr = new ArrayList<>();
		VoidVisitor<List<AssignExprData>> ae = new AssignExprCollector();
		ae.visit(mRootCompilationUnit, AssignExpr);
		
		AssignExpr.addAll(UnaryAssignExpr);
		
		for(AssignExprData aed : AssignExpr) {
			if(isAnotatedOrUknown(aed.getAssignExprClassName(), aed.getAssignExprMethodSignature())){
				return new VerifyResult(Result.ERROR, "The method "+ aed.getAssignExprMethodName() + " of the class " + aed.getAssignExprClassName() + "," + " contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.", aed.getAssignExprMethodSignature(), aed.getAssignExprClassName());
			}
		}

		return new VerifyResult(Result.OK, "", null, null);
	}
}
