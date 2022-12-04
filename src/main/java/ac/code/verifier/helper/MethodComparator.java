package ac.code.verifier.helper;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

public class MethodComparator {
	
	public static String getMethodSignature(Element pElement) {
		   Element el = pElement;
		   while((el.getEnclosingElement() != null) && !(el.getKind().toString().equals("METHOD") || el.getKind().toString().equals("CONSTRUCTOR"))) {
			   el = el.getEnclosingElement();
		   }	    
		   return (el.getKind().toString().equals("METHOD") || el.getKind().toString().equals("CONSTRUCTOR"))  ? el.toString() : null;
	   } 
	   
	public static String getClassName(Element pElement) {
		   Element el = pElement;   
		   while((el.getEnclosingElement() != null) && !el.getKind().toString().equals("CLASS")) {
			   el = el.getEnclosingElement();
		   }	
		   String classFullName = el.toString();
		   return el.getKind().toString().equals("CLASS") ? classFullName.substring(classFullName.lastIndexOf(".")+1) : null;
	   } 

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
	
	private static String getMethodNameFromMethodSignature(String pMethodSignature){
		return pMethodSignature.substring(0, pMethodSignature.indexOf("("));
	}
}
