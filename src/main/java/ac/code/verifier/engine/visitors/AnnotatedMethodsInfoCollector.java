package ac.code.verifier.engine.visitors;

import java.util.List;
import java.util.Optional;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.data.MethodData;

public class AnnotatedMethodsInfoCollector extends VoidVisitorAdapter<List<MethodData>> {
	
	private String mAnnotation;
	private String mMovableAnnotation;
	private List<SynchronizedStmt> mSynchronizedStmts;
	
	public AnnotatedMethodsInfoCollector(String pAnnotation, List<SynchronizedStmt> pSynchronizedStmts, String pMovableAnnotation) {
		mAnnotation = pAnnotation;
		mMovableAnnotation = pMovableAnnotation;
		mSynchronizedStmts = pSynchronizedStmts;
	}
	
	 @Override
	 public void visit(MethodDeclaration md, List<MethodData> collector) {
		 super.visit(md, collector);
		 
		 MethodData methodData = new MethodData();	 
		 Optional<Node> parentNode = md.getParentNode();	 
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
	 
	 
	 boolean isContainLockedOnThisBlock(MethodDeclaration md) {
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
