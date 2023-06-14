package ac.code.verifier.helper;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

/**
 * The class MethodComparator provides methods that handle comparing method signatures.
 *
 */
public class MethodComparator {
	
	/**
	 * Convert Element of method type to the signature of the method.
	 * @param pElement The element which should be a method or constructor.
	 * @return The method signature, or null in case the element is not a method.
	 */
	public static String getMethodSignature(Element pElement) {
		   Element el = pElement;
		   while((el.getEnclosingElement() != null) && !(el.getKind().toString().equals("METHOD") || el.getKind().toString().equals("CONSTRUCTOR"))) {
			   el = el.getEnclosingElement();
		   }	    
		   return (el.getKind().toString().equals("METHOD") || el.getKind().toString().equals("CONSTRUCTOR"))  ? el.toString() : null;
	   } 
	  
	/**
	 * Finds the class name that element it belongs to.
	 * @param pElement The element.
	 * @return The Class Name
	 */
	public static String getClassName(Element pElement) {
		   Element el = pElement;   
		   while((el.getEnclosingElement() != null) && !el.getKind().toString().equals("CLASS")) {
			   el = el.getEnclosingElement();
		   }	
		   String classFullName = el.toString();
		   return el.getKind().toString().equals("CLASS") ? classFullName.substring(classFullName.lastIndexOf(".")+1) : null;
	   } 

	/**
	 * Checking whether this is the same method.
	 * @param pElementClassName The class name of the first method.
	 * @param pElementMethodSignature The signature of the first method.
	 * @param pParsedClassName The class name of the second method.
	 * @param pParsedMethodSignature The signature of the second method.
	 * @return
	 */
	public static boolean isSameMethod(String pElementClassName, String pElementMethodSignature, String pParsedClassName, String pParsedMethodSignature) {
		if (!pElementClassName.equals(pParsedClassName) || !getMethodNameFromMethodSignature(pElementMethodSignature).equals(getMethodNameFromMethodSignature(pParsedMethodSignature))) {
			return false;
		}
		List<String> elementParamsList = getParamListFromMethodSignature(pElementMethodSignature);
		List<String> parsedMethodParamsList = getParamListFromMethodSignature(pParsedMethodSignature);
		
		if (elementParamsList.size() != parsedMethodParamsList.size()) {
			return false;
		}
		
		for(int i=0; i<elementParamsList.size(); i++)
		{
		    if(!elementParamsList.get(i).equals(parsedMethodParamsList.get(i))) {
		    	return false;
		    }
		}
		return true;
	}
	
	/**
	 * Extract the list of parameters from the method signature
	 * @param pMethodSignature The method signature
	 * @return A list of strings that contains the types of parameters.
	 */
	private static List<String> getParamListFromMethodSignature(String pMethodSignature){
		List<String> paramsList = new ArrayList<String>();
		
		String paramString = pMethodSignature.substring(pMethodSignature.indexOf("(")+1, pMethodSignature.indexOf(")"));
		String[] params = paramString.split(",");
		for (String p: params) { 
			String param = p;
			if(param.contains("<")) {
				param = param.substring(0, param.indexOf("<"));
			}
			paramsList.add(param.substring(param.lastIndexOf(".")+1).replaceAll("\\s+","")); 
		}		
		return paramsList;
	}
	
	/**
	 * Extract the method name from the method signature.
	 * @param pMethodSignature The method signature
	 * @return
	 */
	private static String getMethodNameFromMethodSignature(String pMethodSignature){
		return pMethodSignature.substring(0, pMethodSignature.indexOf("("));
	}
}
