package ac.code.verifier.processors;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

import ac.code.verifier.engine.ExtractableVerifier;
import ac.code.verifier.helper.VerifyResult;

@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.MovableCode")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MovableCodeFragmentPropertyProcessor extends CodeFragmentPropertyProcessor{

	@Override
	protected String getMarkOfBegin() {
		return "/*@MovableBegin*/";
	}

	@Override
	protected String getMarkOfEnd() {
		return "/*@MovableEnd*/";
	}
	
	@Override
	protected String getMarkName() {
		return "Movable";
	}
	
	@Override
	protected String getActionVerb() {
		return "moved";
	}
	
	@Override
	protected String getAnatation() {
		return "MovableCode";
	}
	
	@Override
	protected boolean IsConfigurationRequired() {return false;}

	@Override
	public VerifyResult verifyCodeFragments(TypeElement pAnnotation, String pSourceFilePath) {
		ExtractableVerifier verifier = new ExtractableVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), null);
		return verifier.isCodeFragmentsExtractableAndMovable();
	}
}
