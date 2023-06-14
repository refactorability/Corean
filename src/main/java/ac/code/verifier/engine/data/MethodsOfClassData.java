package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The class MethodData stores an information about methods that belong to a certain class.
 *
 */
public class MethodsOfClassData {
	
	private String className = "";
	private List<MethodBasicInfo> methodsBasicInfoList = null;
	
	/**
	 * Constructor.
	 * @param pClassName The name of the class
	 */
	public MethodsOfClassData(String pClassName){
		className = pClassName;
		methodsBasicInfoList = new ArrayList<MethodBasicInfo>();
	}
	
	/**
	 * Returns the name of the class.
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Returns a list with the information about the methods.
	 * @return
	 */
	public List<MethodBasicInfo> getMethodsBasicInfoList() {
		return methodsBasicInfoList;
	}
	
	/**
	 * Adds method information.
	 * @param pMethodBasicInfo The method information.
	 */
	public void addMethodBasicInfo(MethodBasicInfo pMethodBasicInfo) {
		methodsBasicInfoList.add(pMethodBasicInfo);
	}
}
