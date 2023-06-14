package ac.code.verifier.processors;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import ac.code.verifier.configuration.RefactorabilitySettings;
import ac.code.verifier.helper.MethodComparator;
import ac.code.verifier.helper.PathHelper;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The class PropertyProcessor defines the capabilities common to all processors that check
 * the existence of certain properties in the annotated code.
 *
 */
public abstract class PropertyProcessor extends AbstractProcessor {
	
   RefactorabilitySettings refactorabilitySettings = null;

   /**
    * The method checks whether the code contains an annotation.
    * If so, call the abstract method verifyFile (which checks whether the marked code fulfills the desired properties) 
    * and pass as parameters the annotation and the path of the source code.
    * If a violation of the properties is detected - reports a compilation error.
    * @param annotations The annotation types requested to be processed
    * @param roundEnv Environment for information about the current and prior round
    * @return Whether or not the set of annotation types are claimed by this processor
    */
   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
   
	Set<String> sourceFileSet = new HashSet<String> (); 
	boolean isErrorDetected = false;
	VerifyResult res = null;
	
   	for (TypeElement annotation : annotations) {
           Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
           for (Element annotatedElement : annotatedElements) {
                String sourceFilePath = PathHelper.getSourceFullPath(processingEnv, getRootElement(annotatedElement));
                if(refactorabilitySettings == null) {
                    File configurationFile = PathHelper.getRefactorabilityConfigurationFullPath(processingEnv, getRootElement(annotatedElement));
                    if(configurationFile.exists()){
                    	ObjectMapper mapper = new ObjectMapper();
                    	 try {
							RefactorabilitySettings tmp = mapper.readValue(configurationFile, RefactorabilitySettings.class);
							refactorabilitySettings = tmp;
						} catch (Exception e) {
						    System.out.println("Failed to read configuration file from:: " + configurationFile.toString());
						}
							
                    }
                }
                              
                if((sourceFileSet.contains(sourceFilePath) && !isErrorDetected) || (!sourceFileSet.contains(sourceFilePath) && isErrorDetected)){
                	continue;
                } 
                
                if(sourceFileSet.contains(sourceFilePath) && isErrorDetected){
                	if(MethodComparator.isSameMethod(MethodComparator.getClassName(annotatedElement), MethodComparator.getMethodSignature(annotatedElement), res.getClassName(), res.getMethodSignature())) {
                		handleResult(res, annotatedElement, sourceFilePath);
                	}
                	continue;
                } 
                sourceFileSet.add(sourceFilePath);
	           	res = verifyFile(annotation.getSimpleName().toString(), sourceFilePath);
	           	
	           	if(res.getResult() == Result.ERROR) {
	           		isErrorDetected = true;
	           		if(res.isGeneralError()) {
	           			processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, res.getMessage());
	           		}
	           		if(MethodComparator.isSameMethod(MethodComparator.getClassName(annotatedElement), MethodComparator.getMethodSignature(annotatedElement), res.getClassName(), res.getMethodSignature())) {
	           			handleResult(res, annotatedElement, sourceFilePath);
                	}
	           	}	
           }
       }
       return true;
   }
    
   /**
    * An abstract method, checks whether the marked code fulfills the desired properties.
    * @param pAnnotation The annotation that indicates which properties should be checked
    * @param pSourceFilePath Path to the source file that contains the annotation.
    * @return The verification result
    */
   protected abstract VerifyResult verifyFile(String pAnnotation, String pSourceFilePath); 
   
   /**
    * Reports a compilation error.
    * @param msg The error message
    * @param pAnnotatedElement The element in the code that caused the error
    */
   protected void printError(String msg, Element pAnnotatedElement) {
	   processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg,  pAnnotatedElement);
	 //  processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, msg,  pAnnotatedElement);
     }
   
   /**
    * Prints a compilation warning
    * @param msg The message
    */
   protected void printWarning(String msg) {
	   System.out.println("[WARNING] " + msg);
     }
   
   /**
    * Prints a compilation info
    * @param msg The message
    */
   protected void printInfo(String msg) {
	   System.out.println("[INFO] " + msg);  
     }
   
   /**
    * Finds the root of a given Element
    * @param pElement The element
    * @return
    */
   private Element getRootElement(Element pElement) {
	   Element res = pElement;
	   
	   while(res.getEnclosingElement().getEnclosingElement().getEnclosingElement() != null) {
		   res = res.getEnclosingElement();
	   }	    
	   return res;
   } 
   
   /**
    * Print the result
    * @param pResult The result
    * @param pAnnotatedElement - The annotated element
    * @param pSourceFilePath The path to source file
    */
   private void handleResult(VerifyResult pResult, Element pAnnotatedElement, String pSourceFilePath) {
		switch (pResult.getResult()) {
        case OK:        	
        	printInfo("The property of file " + pSourceFilePath + " verified");
            break;
        case WARNING:
        	printInfo("The file " + pSourceFilePath);
        	printWarning(pResult.getMessage());
            break;
        case ERROR:    	
        	printInfo("The file " + pSourceFilePath);
        	printError(pResult.getMessage(), pAnnotatedElement);
    }	   
   }
}

