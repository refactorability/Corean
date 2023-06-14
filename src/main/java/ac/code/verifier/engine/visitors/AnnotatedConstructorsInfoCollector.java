package ac.code.verifier.engine.visitors;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import ac.code.verifier.engine.data.MethodData;

/**
 * The class AnnotatedConstructorsInfoCollector collects data about constructors from AST.
 *
 */
public class AnnotatedConstructorsInfoCollector extends VoidVisitorAdapter<List<MethodData>> {
	
	private String mAnnotation;
	private String mMovableAnnotation;
	private List<SynchronizedStmt> mSynchronizedStmts;
	
	/**
	 * Constructor
	 * @param pAnnotation The string of relevant annotation.
	 * @param pSynchronizedStmts The list of synchronized statements.
	 * @param pMovableAnnotation The string of annotation which represents a movable method.
	 */
	public AnnotatedConstructorsInfoCollector(String pAnnotation, List<SynchronizedStmt> pSynchronizedStmts, String pMovableAnnotation) {
		mAnnotation = pAnnotation;
		mMovableAnnotation = pMovableAnnotation;
		mSynchronizedStmts = pSynchronizedStmts;
	}
	
	/**
	 * Visits on the ConstructorDeclaration type nodes. 
	 */
	 @Override
	 public void visit(ConstructorDeclaration cd, List<MethodData> collector) {
		 super.visit(cd, collector);
		 
		 MethodData methodData = new MethodData();
		 
		 Optional<Node> parentNode = cd.getParentNode();	
		 
		 String className = "";
		 
		 while(parentNode.isPresent()) {
				 Node pn = parentNode.get();
				 if(pn.getMetaModel().toString().equals("EnumDeclaration")) {
					 EnumDeclaration ed = (EnumDeclaration)pn;
					 className = ed.getNameAsString();
					 break;
				 } 
				 else if(pn.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")) {
					 ClassOrInterfaceDeclaration CurentClass = (ClassOrInterfaceDeclaration)pn;
					 className = CurentClass.getNameAsString();
					 break;
				 }	
				 parentNode = pn.getParentNode();
		 }
		 methodData.setMethodBelongToClass(className);
		 	
		 methodData.setAnnotated(cd.isAnnotationPresent(mAnnotation)); 
		 methodData.setMovable(cd.isAnnotationPresent(mMovableAnnotation)); 
		 methodData.setMethodName(cd.getNameAsString());
		 methodData.setAbstract(cd.isAbstract());
		 methodData.setOverride(cd.isAnnotationPresent("Override"));
		 methodData.setMethodSignature(cd.getSignature().asString());	
		 methodData.setMethodReturnType("");
		 methodData.setLockedOnThis(isContainLockedOnThisBlock(cd));	 
		 methodData.setRange(cd.getRange().get());
		 methodData.setBodyRange(cd.getBody().getRange().get());
		 methodData.setNumOfParams(cd.getParameters().size());
		 collector.add(methodData);
		
	 }
	 
	 /**
	  * Checks whether the node is contained in one of the locked blocks.
	  * @param pNode The tested node
	  * @return
	  */
	 private boolean isContainLockedOnThisBlock(Node pNode) {
		 if(!pNode.getRange().isPresent()) {
			 return false;
		 }
		 for(SynchronizedStmt ss: mSynchronizedStmts) {
			 if(!ss.getRange().isPresent()) {
				 continue;
			 } 
		 	if(pNode.getRange().get().contains(ss.getRange().get())) {
				 return true;
			 }
		 }
		 return false;
	 }
}
