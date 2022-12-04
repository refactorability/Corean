package ac.code.verifier.engine.visitors;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import ac.code.verifier.engine.data.MethodData;

public class AnnotatedConstructorsInfoCollector extends VoidVisitorAdapter<List<MethodData>> {
	
	private String mAnnotation;
	private String mMovableAnnotation;
	private List<SynchronizedStmt> mSynchronizedStmts;
	
	public AnnotatedConstructorsInfoCollector(String pAnnotation, List<SynchronizedStmt> pSynchronizedStmts, String pMovableAnnotation) {
		mAnnotation = pAnnotation;
		mMovableAnnotation = pMovableAnnotation;
		mSynchronizedStmts = pSynchronizedStmts;
	}
	
	 @Override
	 public void visit(ConstructorDeclaration cd, List<MethodData> collector) {
		 super.visit(cd, collector);
		 
		 MethodData methodData = new MethodData();
		 
		 Optional<Node> parentNode = cd.getParentNode();	 
		 if (parentNode.isPresent()){
			 Node pn = parentNode.get();
			 if(pn.getMetaModel().toString().equals("EnumDeclaration")) {
				 EnumDeclaration ed = (EnumDeclaration)pn;
				 methodData.setMethodBelongToClass(ed.getNameAsString());
			 }else {
				 ClassOrInterfaceDeclaration CurentClass = (ClassOrInterfaceDeclaration)pn;
				 methodData.setMethodBelongToClass(CurentClass.getNameAsString());
			 }			
		}
		
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
	 
	 
	 boolean isContainLockedOnThisBlock(Node pNode) {
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
