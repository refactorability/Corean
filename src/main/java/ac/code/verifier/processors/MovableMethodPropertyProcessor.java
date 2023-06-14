package ac.code.verifier.processors;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.google.auto.service.AutoService;
import ac.code.verifier.engine.MethodPropertiesVerifier;
import ac.code.verifier.helper.VerifyResult;

/**
 * The class implements all the abstract methods for "MovableMethod" annotation.
 *
 */
@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.MovableMethod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MovableMethodPropertyProcessor extends PropertyProcessor {
	
	/**
	 * The method implement an abstract method - checks whether the annotated method fulfills the properties expected from MovableMethod. 
	 */
	@Override
	protected VerifyResult verifyFile(String pAnnotation, String pSourceFilePath) {
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier(pAnnotation, pSourceFilePath, null); 
		return verifier.verify();
	}
}
