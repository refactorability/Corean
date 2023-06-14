package ac.code.verifier.processors;

import javax.lang.model.element.TypeElement;
import ac.code.verifier.engine.CodeFragmentPropertiesVerifier;
import ac.code.verifier.helper.VerifyResult;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import com.google.auto.service.AutoService;

/**
 * The class implements all the abstract methods for "ExtractableCode" annotation.
 *
 */
@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.ExtractableCode")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class ExtractableCodeFragmentPropertyProcessor extends CodeFragmentPropertyProcessor{

	/**
	 * The method implement an abstract method - defines the string for pseudo annotation of "fragment begin" for  "extractable fragment".
	 */
	@Override
	protected String getMarkOfBegin() {
		return "/*@ExtractableBegin*/";
	}

	/**
	 * The method implement an abstract method - defines the string for pseudo annotation of "fragment end" for  "extractable fragment".
	 */
	@Override
	protected String getMarkOfEnd() {
		return "/*@ExtractableEnd*/";
	}
	
	/**
	 * The method implement an abstract method - sets the name of pseudo annotations of "extractable fragment".
	 */
	@Override
	protected String getMarkName() {
		return "Extractable";
	}
	
	/**
	 * The method implement an abstract method - sets the action verb of pseudo annotations of "extractable fragment".
	 */
	@Override
	protected String getActionVerb() {
		return "extracted";
	}
	
	/**
	 * The method implement an abstract method - get the main annotation that is handled by this processor.
	 */
	@Override
	protected String getAnatation() {
		return "ExtractableCode";
	}
	
	/**
	 * The method implement an abstract method - checks whether the settings for ExtractableCode annotation come from a configuration file.
	 */
	@Override
	protected boolean IsConfigurationRequired() {return false;}

	/**
	 * The method implement an abstract method - checks whether the marked code fragment fulfills the properties expected from ExtractableCode.  
	 */
	@Override
	protected VerifyResult verifyCodeFragments(String pAnnotation, String pSourceFilePath) {
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), null);
		return verifier.isCodeFragmentsExtractable();

	}
}
