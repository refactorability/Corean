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

public abstract class PropertyProcessor extends AbstractProcessor {
	
   RefactorabilitySettings refactorabilitySettings = null;

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
	           	res = verifyFile(annotation, sourceFilePath);
	           	
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
    
   public abstract VerifyResult verifyFile(TypeElement pAnnotation, String pSourceFilePath); 
   
   protected void printError(String msg, Element pAnnotatedElement) {
	   processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg,  pAnnotatedElement);
	 //  processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, msg,  pAnnotatedElement);
     }
   
   protected void printWarning(String msg) {
	   System.out.println("[WARNING] " + msg);
     }
   
   protected void printInfo(String msg) {
	   System.out.println("[INFO] " + msg);  
     }
   
   private Element getRootElement(Element pElement) {
	   Element res = pElement;
	   
	   while(res.getEnclosingElement().getEnclosingElement().getEnclosingElement() != null) {
		   res = res.getEnclosingElement();
	   }	    
	   return res;
   } 
   
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

