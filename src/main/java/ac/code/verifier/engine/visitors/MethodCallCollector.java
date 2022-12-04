package ac.code.verifier.engine.visitors;

import java.util.List;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.data.MethodCallFromThisData;
import ac.code.verifier.engine.visitors.helpers.VisitorHelper;

public class MethodCallCollector extends VoidVisitorAdapter<List<MethodCallFromThisData>> {
	

	 @Override
	 public void visit(MethodCallExpr mfe, List<MethodCallFromThisData> collector) {
		 super.visit(mfe, collector);
		 if(isMethodCallFromCurrentClass(mfe)) {
			 MethodCallFromThisData data = new MethodCallFromThisData(mfe.getNameAsString(), VisitorHelper.getContainingMethodName(mfe),
					 VisitorHelper.getContainingClassName(mfe), mfe.getRange().isPresent() ? mfe.getRange().get():null, VisitorHelper.getContainingMethodSignature(mfe), mfe.getArguments().size());
			 collector.add(data);
		 }
	 }
	 
	 boolean isMethodCallFromCurrentClass(MethodCallExpr pMethodCallExpr){
		 Node n = pMethodCallExpr.getChildNodes().get(0);
		 if(n.getMetaModel().toString().equals("NameExpr") || n.getMetaModel().toString().equals("FieldAccessExpr")) {
			 return false;
		 }		 
		 return true;
	 } 
}
