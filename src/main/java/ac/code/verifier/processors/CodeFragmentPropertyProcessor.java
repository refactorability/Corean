package ac.code.verifier.processors;

import javax.lang.model.element.TypeElement;
import ac.code.verifier.engine.ExtractableVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

public abstract class CodeFragmentPropertyProcessor extends PropertyProcessor {
	
	protected abstract String getMarkOfBegin(); 	
	protected abstract String getMarkOfEnd();	
	protected abstract String getMarkName();	
	protected abstract String getActionVerb();
	protected abstract String getAnatation();	
	protected abstract VerifyResult verifyCodeFragments(TypeElement pAnnotation, String pSourceFilePath);
	protected abstract boolean IsConfigurationRequired();	
	
	public VerifyResult verifyStructureOfMarks(TypeElement pAnnotation, String pSourceFilePath) {
		ExtractableVerifier verifier = new ExtractableVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), null);
		return verifier.verifyStructureOfMarks();
	}
	
	@Override
	public VerifyResult verifyFile(TypeElement pAnnotation, String pSourceFilePath) {
		
		if((IsConfigurationRequired()) && ((refactorabilitySettings == null) || (refactorabilitySettings.getCodeRefactorability() == null))) {
			VerifyResult vr = new VerifyResult(Result.ERROR, "Failed to load configuration file.", null, null);
			vr.setGeneralError(true);
			return vr;
		}	
		VerifyResult result = verifyStructureOfMarks(pAnnotation, pSourceFilePath);
					
		if (result.getResult() == Result.ERROR) {
			return result;
		}		
		return verifyCodeFragments(pAnnotation, pSourceFilePath);
	}
}