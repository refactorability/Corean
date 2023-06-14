package ac.code.verifier.processors;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.google.auto.service.AutoService;
import ac.code.verifier.engine.MethodPropertiesVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

/**
 * The class implements all the abstract methods for configurable "RefactorableMethod" annotation.
 *
 */
@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.RefactorableMethod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MethodRefactorabilityPropertyProcessor extends PropertyProcessor {
	
	/**
	 * The method implement an abstract method - checks whether the annotated method fulfills the properties defined in the configuration file. 
	 */
	@Override
	protected VerifyResult verifyFile(String pAnnotation, String pSourceFilePath) {
		if(refactorabilitySettings == null){
		}
		if((refactorabilitySettings == null) || (refactorabilitySettings.getMethodRefactorability() == null)) {
			VerifyResult vr = new VerifyResult(Result.ERROR, "Failed to load configuration file.", null, null);
			vr.setGeneralError(true);
			return vr;
		}
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier(pAnnotation, pSourceFilePath, refactorabilitySettings.getMethodRefactorability()); 
		return verifier.verify();
	}
}

