package ac.code.verifier.engine.visitors;

import java.util.List;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import ac.code.verifier.engine.data.AssignExprData;
import ac.code.verifier.engine.visitors.helpers.VisitorHelper;

/**
 * The class UnaryExprCollector collects data about unary expressions.
 */
public class UnaryExprCollector extends AssignExprCollector{
	
	/**
	 * Visits on the UnaryExpr type nodes.
	 */
	 @Override
	 public void visit(UnaryExpr ue, List<AssignExprData> collector) {
		 super.visit(ue, collector);
		 
		 Node firstNode = ue.getChildNodes().get(0);
		 
		 String varName = "";
		 	 
		 if(firstNode.getMetaModel().toString().equals("FieldAccessExpr")) {
			 varName = getVarName((FieldAccessExpr)firstNode);
		 } else if(firstNode.getMetaModel().toString().equals("NameExpr")) {
			 varName = firstNode.toString();
		 } else {
			 return;
		 }
		 
		 Node node = ue; 
		 if(isClassFieldMember(node, varName, ue.getRange().get())) {
			 collector.add(new AssignExprData(varName, VisitorHelper.getContainingClassName(node), VisitorHelper.getContainingMethodName(node), ue.getRange().get(), VisitorHelper.getContainingMethodSignature(node)));
		 }	
	 }
}
