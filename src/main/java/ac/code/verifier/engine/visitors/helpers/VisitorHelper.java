package ac.code.verifier.engine.visitors.helpers;

import java.util.List;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import ac.code.verifier.engine.data.MethodData;

public class VisitorHelper {
	
	public static String getContainingClassName(Node pNode) {	 
		 Node node = pNode;	 
		 while(node.getParentNode().isPresent()) {
			 if(node.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")) {
				 ClassOrInterfaceDeclaration cd = ((ClassOrInterfaceDeclaration)node);
				 return cd.getNameAsString();
			 }
			 if(node.getMetaModel().toString().equals("EnumDeclaration")) {
				 EnumDeclaration ed = ((EnumDeclaration)node);
				 return ed.getNameAsString();
			 }
			 node = node.getParentNode().get(); 
		 } 
		 return "";
	 }
	 
	public static String getContainingMethodName(Node pNode) {		 
		 Node node = pNode;		 
		 while(node.getParentNode().isPresent()) {
			 if(node.getMetaModel().toString().equals("MethodDeclaration")) {
				 MethodDeclaration md = ((MethodDeclaration)node);
				 return md.getNameAsString();
			 }
			 if(node.getMetaModel().toString().equals("ConstructorDeclaration")) {
				 ConstructorDeclaration cd = ((ConstructorDeclaration)node);
				 return cd.getNameAsString();
			 }
			 node = node.getParentNode().get(); 
		 } 
		 return "";
	 }
	 
	public static String getContainingMethodSignature(Node pNode) {		 
		 Node node = pNode;		 
		 while(node.getParentNode().isPresent()) {
			 if(node.getMetaModel().toString().equals("MethodDeclaration")) {
				 MethodDeclaration md = ((MethodDeclaration)node); 
				 return md.getSignature().asString();
			 }
			 if(node.getMetaModel().toString().equals("ConstructorDeclaration")) {
				 ConstructorDeclaration cd = ((ConstructorDeclaration)node); 
				 return cd.getSignature().asString();
			 }
			 node = node.getParentNode().get(); 
		 } 
		 return null;
	 }
	
	public static MethodData findMethodData(String pClassName, String pMethodSignature, List<MethodData> pListMethodData) {
		 for(MethodData md : pListMethodData) {
			 if(md.getMethodBelongToClass().equals(pClassName) && md.getMethodSignature().equals(pMethodSignature)) {
				 return md;
			 }
		 }
		 return null; 
	 }
}
