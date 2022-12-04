package ac.code.verifier.processors;

import javax.lang.model.element.TypeElement;
import ac.code.verifier.engine.ExtractableVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.RefactorableCode")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CodeRefactorabilityPropertyProcessor extends CodeFragmentPropertyProcessor{ 
	
	@Override
	protected String getMarkOfBegin() {
		return refactorabilitySettings.getCodeRefactorability().getMarkOfBegin();
	}

	@Override
	protected String getMarkOfEnd() {
		return refactorabilitySettings.getCodeRefactorability().getMarkOfEnd();
	}
	
	@Override
	protected String getMarkName() {
		return refactorabilitySettings.getCodeRefactorability().getAnnotationMeaning();
	}
	
	@Override
	protected String getActionVerb() {
		return refactorabilitySettings.getCodeRefactorability().getAnnotationActionVerb();
	}
	
	@Override
	protected String getAnatation() {
		return "RefactorableCode";
	}
	
	@Override
	protected boolean IsConfigurationRequired() {return true;}	

	@Override
	public VerifyResult verifyCodeFragments(TypeElement pAnnotation, String pSourceFilePath) {
		ExtractableVerifier verifier = new ExtractableVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), refactorabilitySettings.getCodeRefactorability());
		return verifier.isCodeFragmentsExtractable();

	}

}
