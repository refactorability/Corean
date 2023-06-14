package ac.code.verifier.engine;

import java.io.IOException;

import com.github.javaparser.ParseProblemException;

import ac.code.verifier.configuration.CodeRefactorabilitySettings;
import ac.code.verifier.configuration.MethodRefactorabilitySettings;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import ac.exceptions.FailedVerifyException;

/**
 * The CodeFragmentPropertiesVerifier class is responsible for all checks related to code fragments.
 *
 */
public class CodeFragmentPropertiesVerifier {
	private String mAnotation;
	private String mSourceFilePath;
	private String mMarkOfBegin;
	private String mMarkOfEnd;
	private String mMarkName;
	private String mActionVerb;
	CodeFragmentPropertiesParser mExtractableParser = null;
	private CodeRefactorabilitySettings mCodeRefSet;
	
	/**
	 * Constructor
	 * @param pAnotation The annotation we want to test.
	 * @param pSourceFilePath Path to the file containing the annotation.
	 * @param pMarkOfBegin The mark of the beginning of a code fragment.
	 * @param pMarkOfEnd The mark of the end of a code fragment.
	 * @param pMarkName The name of the mark.
	 * @param pActionVerb The verb of the annotation.
	 * @param pCodeRefSet The configurations.
	 */
	public CodeFragmentPropertiesVerifier(String pAnotation, String pSourceFilePath, String pMarkOfBegin, String pMarkOfEnd, String pMarkName, String pActionVerb, CodeRefactorabilitySettings pCodeRefSet) {
		mSourceFilePath = pSourceFilePath;
		mMarkOfBegin = pMarkOfBegin;
		mMarkOfEnd = pMarkOfEnd;
		mAnotation = pAnotation;
		mMarkName = pMarkName; 
		mActionVerb = pActionVerb;
		mCodeRefSet = pCodeRefSet;
		try 
		{
			mExtractableParser = new CodeFragmentPropertiesParser(mSourceFilePath, mMarkOfBegin, mMarkOfEnd, mAnotation, mMarkName, mActionVerb);
		} 
		catch (ParseProblemException | IOException | FailedVerifyException e) 
		{
			mExtractableParser = null;
		}
	}
	
	/**
	 * Checks that the method contains a valid code fragment marking structure.
	 * @return
	 */
	public VerifyResult verifyStructureOfMarks() {
		if(mExtractableParser == null) {
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}

		return mExtractableParser.checkStructureOfMarks();
	}
	
	/**
	 * Checks that the code fragment does not violate the expected properties from the extractable code.
	 * @return
	 */
	public VerifyResult isCodeFragmentsExtractable() {
		if(mExtractableParser == null) {
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}
		
		return mExtractableParser.checkExtractableCodeFragments(mCodeRefSet);
	}
	
	/**
	 * Checks that the code fragment does not violate the expected properties from the movable code.
	 * @return
	 */
	public VerifyResult isCodeFragmentsExtractableAndMovable() {
		if(mExtractableParser == null) {
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}
		
		return mExtractableParser.checkMovableCodeFragments();
	}

}
