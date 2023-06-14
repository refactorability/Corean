package ac.code.verifier.engine.visitors.helpers;

import java.util.List;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import ac.code.verifier.engine.data.MethodData;

/**
 * The class VisitorHelper provides methods that helping to get information from AST.
 *
 */
public class VisitorHelper {
	
	/**
	 * Finds the name of the class that contains this node.
	 * @param pNode The node
	 * @return
	 */
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
	 
	/**
	 * Finds the name of the method that contains this node.
	 * @param pNode The node
	 * @return
	 */
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
	 
	/**
	 * Finds the signature of the method that contains this node.
	 * @param pNode The node
	 * @return
	 */
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
	
	/**
	 * Finds MethodData object by class name and method signature.
	 * @param pClassName Name of the class to which the method belongs.
	 * @param pMethodSignature The signature of the method.
	 * @param pListMethodData The MethodData list of all methods. 
	 * @return
	 */
	public static MethodData findMethodData(String pClassName, String pMethodSignature, List<MethodData> pListMethodData) {
		 for(MethodData md : pListMethodData) {
			 if(md.getMethodBelongToClass().equals(pClassName) && md.getMethodSignature().equals(pMethodSignature)) {
				 return md;
			 }
		 }
		 return null; 
	 }
}
