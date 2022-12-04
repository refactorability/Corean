package ac.code.verifier.processors;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.google.auto.service.AutoService;
import ac.code.verifier.engine.MovableVerifier;
import ac.code.verifier.helper.VerifyResult;

@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.MovableMethod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MovableMethodPropertyProcessor extends PropertyProcessor {
	
	@Override
	public VerifyResult verifyFile(TypeElement pAnnotation, String pSourceFilePath) {
		MovableVerifier verifier = new MovableVerifier(pAnnotation.getSimpleName().toString(), pSourceFilePath, null); 
		return verifier.verify();
	}
}
