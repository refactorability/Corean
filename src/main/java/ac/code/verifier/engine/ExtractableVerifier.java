package ac.code.verifier.engine;

import java.io.IOException;

import com.github.javaparser.ParseProblemException;

import ac.code.verifier.configuration.CodeRefactorabilitySettings;
import ac.code.verifier.configuration.MethodRefactorabilitySettings;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import exceptions.FailedVerifyException;

public class ExtractableVerifier {
	private String mAnotation;
	private String mSourceFilePath;
	private String mMarkOfBegin;
	private String mMarkOfEnd;
	private String mMarkName;
	private String mActionVerb;
	ExtractableParser mExtractableParser = null;
	private CodeRefactorabilitySettings mCodeRefSet;
	
	public ExtractableVerifier(String pAnotation, String pSourceFilePath, String pMarkOfBegin, String pMarkOfEnd, String pMarkName, String pActionVerb, CodeRefactorabilitySettings pCodeRefSet) {
		mSourceFilePath = pSourceFilePath;
		mMarkOfBegin = pMarkOfBegin;
		mMarkOfEnd = pMarkOfEnd;
		mAnotation = pAnotation;
		mMarkName = pMarkName; 
		mActionVerb = pActionVerb;
		mCodeRefSet = pCodeRefSet;
		try 
		{
			mExtractableParser = new ExtractableParser(mSourceFilePath, mMarkOfBegin, mMarkOfEnd, mAnotation, mMarkName, mActionVerb);
		} 
		catch (ParseProblemException | IOException | FailedVerifyException e) 
		{
			mExtractableParser = null;
		}
	}
	
	public VerifyResult verifyStructureOfMarks() {
		if(mExtractableParser == null) {
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}

		return mExtractableParser.checkStructureOfMarks();
	}
	
	public VerifyResult isCodeFragmentsExtractable() {
		if(mExtractableParser == null) {
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}
		
		return mExtractableParser.checkExtractableCodeFragments(mCodeRefSet);
	}
	
	public VerifyResult isCodeFragmentsExtractableAndMovable() {
		if(mExtractableParser == null) {
			return new  VerifyResult(Result.WARNING, "Failed to parse the file.", null, null);
		}
		
		return mExtractableParser.checkMovableCodeFragments();
	}

}
