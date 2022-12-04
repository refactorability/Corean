package ac.code.verifier.engine;

import java.io.IOException;
import com.github.javaparser.ParseProblemException;

import ac.code.verifier.configuration.MethodRefactorabilitySettings;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import exceptions.FailedVerifyException;

public class MovableVerifier {
	
	private String mAnnotation;
	private String mSourceFilePath;
	private MethodRefactorabilitySettings mMethodRefSet;
	
	public MovableVerifier(String pAnnotation, String pSourceFilePath, MethodRefactorabilitySettings pMethodRefSet) {	
		mAnnotation = pAnnotation;
		mSourceFilePath = pSourceFilePath;
		mMethodRefSet = pMethodRefSet;
	}
	
	public VerifyResult verify() {
		VerifyResult result;
		MovebleParser movebleParser;
		try 
		{
			movebleParser = new MovebleParser(mAnnotation, mSourceFilePath);
		} 
		catch (ParseProblemException | IOException | FailedVerifyException e) 
		{
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}
		result = movebleParser.checkOverride();
		if((result.getResult() == Result.ERROR) && ((mMethodRefSet==null) || mMethodRefSet.isOverrideTest())) {
			return result;
		}
		
		result = movebleParser.checkLockedOnThis();
		if((result.getResult() == Result.ERROR) && ((mMethodRefSet==null) || mMethodRefSet.isLockTest())) {
			return result;
		}
		
		result = movebleParser.checkCalledMethods();
		if((result.getResult() == Result.ERROR) && ((mMethodRefSet==null) || mMethodRefSet.isCallNotMoveableMethodTest())) {
			return result;
		}
			
		result = movebleParser.checkAssignToMembers();
		if((result.getResult() == Result.ERROR) && ((mMethodRefSet==null) || mMethodRefSet.isInstanceVariableTest())) {
			return result;
		}
		
		return new VerifyResult(Result.OK, "", null, null);
	}
}
