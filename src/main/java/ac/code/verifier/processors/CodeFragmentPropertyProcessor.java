package ac.code.verifier.processors;

import javax.lang.model.element.TypeElement;
import ac.code.verifier.engine.CodeFragmentPropertiesVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

/**
 * The class CodeFragmentPropertyProcessor defines the capabilities common to all processors that check
 * the existence of certain properties in the annotated code fragment.
 * In particular, defines the pseudo annotations for the start and end of the fragments and verifies the markup structure in the annotated methods.
 *
 */
public abstract class CodeFragmentPropertyProcessor extends PropertyProcessor {
	
	/**
	 * An abstract method - defines the string for pseudo annotation of "fragment begin"
	 * @return
	 */
	protected abstract String getMarkOfBegin(); 
	
	/**
	 * An abstract method - defines the string for pseudo annotation of "fragment end"
	 * @return
	 */
	protected abstract String getMarkOfEnd();
	
	/**
	 * An abstract method - sets the name of pseudo annotations. 
	 * Used to generate error messages.
	 * @return
	 */
	protected abstract String getMarkName();
	
	/**
	 * An abstract method - sets the action verb. 
	 * Used to generate error messages. 
	 * @return
	 */
	protected abstract String getActionVerb();
	
	/**
	 * An abstract method
	 * @return The main annotation that is handled by this processor.
	 */
	protected abstract String getAnatation();	
	
	
	/**
	 * An abstract method, checks whether the settings for this annotation come from a configuration file.
	 * @return
	 */
	protected abstract boolean IsConfigurationRequired();	
	
	/**
	 * The method verifies the structure of pseudo annotations.
	 * For each annotated method there should be at least one pair of "fragment begin" and "fragment end" pseudo annotation.
	 * Each  pseudo annotation of "fragment begin" should be followed by pseudo annotation of "fragment end".
	 * Each  pseudo annotation of "fragment end" should come after pseudo annotation of "fragment begin".
	 * @param pAnnotation The annotation
	 * @param pSourceFilePath The path to source file
	 * @return
	 */
	
	/**
	 * An abstract method, checks whether the marked code fragment fulfills the desired properties 
	 * @param pAnnotation  The annotation that indicates which properties should be checked
	 * @param pSourceFilePath Path to the source file that contains the annotation.
	 * @return The verification result.
	 */
	protected abstract VerifyResult verifyCodeFragments(String pAnnotation, String pSourceFilePath);

	protected VerifyResult verifyStructureOfMarks(String pAnnotation, String pSourceFilePath) {
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier(getAnatation(), pSourceFilePath, getMarkOfBegin(), getMarkOfEnd(), getMarkName(), getActionVerb(), null);
		return verifier.verifyStructureOfMarks();
	}
	
   /**
    * Implementation of the tests for code fragments. checks whether the marked code fragment fulfills the desired properties.
    * 1) Checks  
    * @param pAnnotation The annotation that indicates which properties should be checked
    * @param pSourceFilePath Path to the source file that contains the annotation.
    * @return The verification result
    */
	@Override
	protected VerifyResult verifyFile(String pAnnotation, String pSourceFilePath) {
		
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