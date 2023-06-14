package ac.code.verifier.engine.visitors;

import java.util.List;
import java.util.Optional;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.data.MethodData;

/**
 * The class AnnotatedMethodsInfoCollector collects data about methods from AST.
 *
 */
public class AnnotatedMethodsInfoCollector extends VoidVisitorAdapter<List<MethodData>> {
	
	private String mAnnotation;
	private String mMovableAnnotation;
	private List<SynchronizedStmt> mSynchronizedStmts;
	
	/**
	 * Constructor
	 * @param pAnnotation The string of relevant annotation.
	 * @param pSynchronizedStmts The list of synchronized statements.
	 * @param pMovableAnnotation The string of annotation which represents a movable method.
	 */
	public AnnotatedMethodsInfoCollector(String pAnnotation, List<SynchronizedStmt> pSynchronizedStmts, String pMovableAnnotation) {
		mAnnotation = pAnnotation;
		mMovableAnnotation = pMovableAnnotation;
		mSynchronizedStmts = pSynchronizedStmts;
	}
	
	/**
	 * Visits on the MethodDeclaration type nodes.
	 */
	 @Override
	 public void visit(MethodDeclaration md, List<MethodData> collector) {
		 super.visit(md, collector);
		 
		 MethodData methodData = new MethodData();	 
		 Optional<Node> parentNode = md.getParentNode();	
		 
		 
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
		
		 methodData.setConstructor(false);
		 methodData.setAnnotated(md.isAnnotationPresent(mAnnotation)); 
		 methodData.setMovable(md.isAnnotationPresent(mMovableAnnotation)); 
		 methodData.setMethodName(md.getNameAsString());
		 methodData.setAbstract(md.isAbstract());
		 methodData.setOverride(md.isAnnotationPresent("Override"));
		 methodData.setMethodSignature(md.getSignature().asString());	
		 methodData.setMethodReturnType(md.getTypeAsString());
		 methodData.setLockedOnThis(md.isSynchronized() || isContainLockedOnThisBlock(md));	 
		 methodData.setRange(md.getRange().get());
		 methodData.setBodyRange(md.getBody().get().getRange().get());
		 methodData.setNumOfParams(md.getParameters().size());
		 collector.add(methodData);	
	 }
	 
	 /**
	  * Checks whether the node is contained in one of the locked blocks.
	  * @param md The tested node
	  * @return
	  */
	 private boolean isContainLockedOnThisBlock(MethodDeclaration md) {
		 if(!md.getRange().isPresent()) {
			 return false;
		 }
		 for(SynchronizedStmt ss: mSynchronizedStmts) {
			 if(!ss.getRange().isPresent()) {
				 continue;
			 } 
		 	if(md.getRange().get().contains(ss.getRange().get())) {
				 return true;
			 }
		 }
		 return false;
	 }
}
