package ac.code.verifier.processors;

import javax.lang.model.element.TypeElement;
import ac.code.verifier.engine.CodeFragmentPropertiesVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import com.google.auto.service.AutoService;

/**
 * The class implements all the abstract methods for configurable "RefactorableCode" annotation.
 *
 */
@SupportedAnnotationTypes("ac.collaborative.refactoring.annotations.RefactorableCode")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CodeRefactorabilityPropertyProcessor extends CodeFragmentPropertyProcessor{ 
	/**
	 * The method implement an abstract method - return, from the configuration file, the string for pseudo annotation of "fragment begin" for  "refactorable fragment".
	 */
	@Override
	protected String getMarkOfBegin() {
		return refactorabilitySettings.getCodeRefactorability().getMarkOfBegin();
	}

	/**
	 * The method implement an abstract method - return, from the configuration file, the string for pseudo annotation of "fragment end" for  "refactorable fragment".
	 */
	@Override
	protected String getMarkOfEnd() {
		return refactorabilitySettings.getCodeRefactorability().getMarkOfEnd();
	}
	
	/**
	 * The method implement an abstract method - return, from the configuration file, the name of pseudo annotations of "refactorable fragment".
	 */
	@Override
	protected String getMarkName() {
		return refactorabilitySettings.getCodeRefactorability().getAnnotationMeaning();
	}
	
	/**
	 * The method implement an abstract method - return, from the configuration file, the action verb of pseudo annotations of "refactorable fragment".
	 */
	@Override
	protected String getActionVerb() {
		return refactorabilitySettings.getCodeRefactorability().getAnnotationActionVerb();
	}
	
	/**
	 * The method implement an abstract method - get the main annotation that is handled by this processor.
	 */
	@Override
	protected String getAnatation() {
		return "RefactorableCode";
	}
	
	/**
	 * The method implement an abstract method - confirms that settings for RefactorableCode annotation come from the configuration file.
	 */
	@Override
	protected boolean IsConfigurationRequired() {return true;}	

	/**
	 * The method implement an abstract method - checks whether the marked code fragment fulfills the properties defined in the configuration file. 
	 */
	@Override
	protected VerifyResult verifyCodeFragments(String pAnnotation, String pSourceFilePath) {
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), refactorabilitySettings.getCodeRefactorability());
		return verifier.isCodeFragmentsExtractable();

	}

}
