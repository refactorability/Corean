package ac.code.verifier.engine;

import java.io.IOException;
import com.github.javaparser.ParseProblemException;

import ac.code.verifier.configuration.MethodRefactorabilitySettings;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import ac.exceptions.FailedVerifyException;

/**
 * The MethodPropertiesVerifier class is responsible for all checks related to method.
 *
 */
public class MethodPropertiesVerifier {
	
	private String mAnnotation;
	private String mSourceFilePath;
	private MethodRefactorabilitySettings mMethodRefSet;
	
	/**
	 * Constructor
	 * @param pAnnotation The annotation we want to test.
	 * @param pSourceFilePath Path to the file containing the annotation.
	 * @param pMethodRefSet The configurations. 
	 */
	public MethodPropertiesVerifier(String pAnnotation, String pSourceFilePath, MethodRefactorabilitySettings pMethodRefSet) {	
		mAnnotation = pAnnotation;
		mSourceFilePath = pSourceFilePath;
		mMethodRefSet = pMethodRefSet;
	}
	
	/**
	 * Checks that the annotated method does not violate the expected properties from the movable method.
	 * @return
	 */
	public VerifyResult verify() {
		VerifyResult result;
		MethodPropertiesParser movebleParser;
		try 
		{
			movebleParser = new MethodPropertiesParser(mAnnotation, mSourceFilePath);
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
			
		result = movebleParser.checkAssignToInstanceVariables();
		if((result.getResult() == Result.ERROR) && ((mMethodRefSet==null) || mMethodRefSet.isInstanceVariableTest())) {
			return result;
		}
		
		return new VerifyResult(Result.OK, "", null, null);
	}
}
