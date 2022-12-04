package ac.code.verifier.engine.data;

import java.util.ArrayList;
import java.util.List;

public class MethodsOfClassData {
	
	private String className = "";
	private List<MethodBasicInfo> methodsBasicInfoList = null;
	
	public MethodsOfClassData(String pClassName){
		className = pClassName;
		methodsBasicInfoList = new ArrayList<MethodBasicInfo>();
	}
	
	public String getClassName() {
		return className;
	}

	public List<MethodBasicInfo> getMethodsBasicInfoList() {
		return methodsBasicInfoList;
	}
	
	public void addMethodBasicInfo(MethodBasicInfo pMethodBasicInfo) {
		methodsBasicInfoList.add(pMethodBasicInfo);
	}
}
