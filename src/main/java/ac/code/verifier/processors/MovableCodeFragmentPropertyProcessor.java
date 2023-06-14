package ac.code.verifier.processors;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

import ac.code.verifier.engine.CodeFragmentPropertiesVerifier;
import ac.code.verifier.helper.VerifyResult;

/**
 * The class implements all the abstract methods for "MovableCode" annotation.
 *
 */
@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.MovableCode")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MovableCodeFragmentPropertyProcessor extends CodeFragmentPropertyProcessor{

	/**
	 * The method implement an abstract method - defines the string for pseudo annotation of "fragment begin" for  "movable fragment".
	 */
	@Override
	protected String getMarkOfBegin() {
		return "/*@MovableBegin*/";
	}

	/*
	 * The method implement an abstract method - defines the string for pseudo annotation of "fragment end" for  "movable fragment".
	 */
	@Override
	protected String getMarkOfEnd() {
		return "/*@MovableEnd*/";
	}
	
	/*
	 * The method implement an abstract method - sets the name of pseudo annotations of "movable fragment".
	 */
	@Override
	protected String getMarkName() {
		return "Movable";
	}
	
	/**
	 * The method implement an abstract method - sets the action verb of pseudo annotations of "movable fragment".
	 */
	@Override
	protected String getActionVerb() {
		return "moved";
	}
	
	/*
	 * The method implement an abstract method - get the main annotation that is handled by this processor.
	 */
	@Override
	protected String getAnatation() {
		return "MovableCode";
	}
	
	/**
	 * The method implement an abstract method - checks whether the settings for MovableCode annotation come from a configuration file.
	 */
	@Override
	protected boolean IsConfigurationRequired() {return false;}

	/**
	 * The method implement an abstract method - checks whether the marked code fragment fulfills the properties expected from MovableCode. 
	 */
	@Override
	protected VerifyResult verifyCodeFragments(String pAnnotation, String pSourceFilePath) {
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), null);
		return verifier.isCodeFragmentsExtractableAndMovable();
	}
}
