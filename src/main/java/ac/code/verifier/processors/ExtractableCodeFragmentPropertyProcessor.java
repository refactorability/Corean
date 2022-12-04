package ac.code.verifier.processors;

import javax.lang.model.element.TypeElement;
import ac.code.verifier.engine.ExtractableVerifier;
import ac.code.verifier.helper.VerifyResult;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.ExtractableCode")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class ExtractableCodeFragmentPropertyProcessor extends CodeFragmentPropertyProcessor{

	@Override
	protected String getMarkOfBegin() {
		return "/*@ExtractableBegin*/";
	}

	@Override
	protected String getMarkOfEnd() {
		return "/*@ExtractableEnd*/";
	}
	
	@Override
	protected String getMarkName() {
		return "Extractable";
	}
	
	@Override
	protected String getActionVerb() {
		return "extracted";
	}
	
	@Override
	protected String getAnatation() {
		return "ExtractableCode";
	}
	
	@Override
	protected boolean IsConfigurationRequired() {return false;}

	@Override
	public VerifyResult verifyCodeFragments(TypeElement pAnnotation, String pSourceFilePath) {
		ExtractableVerifier verifier = new ExtractableVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), null);
		return verifier.isCodeFragmentsExtractable();

	}
}
