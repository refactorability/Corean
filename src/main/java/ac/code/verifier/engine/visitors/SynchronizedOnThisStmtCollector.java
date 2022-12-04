package ac.code.verifier.engine.visitors;

import java.util.List;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SynchronizedOnThisStmtCollector extends VoidVisitorAdapter<List<SynchronizedStmt>> {

	@Override
	 public void visit(SynchronizedStmt ss, List<SynchronizedStmt> collector) {
		super.visit(ss, collector);
		if(ss.getExpression().toString().equals("this")) {
			collector.add(ss);
		 }
	 }
}
