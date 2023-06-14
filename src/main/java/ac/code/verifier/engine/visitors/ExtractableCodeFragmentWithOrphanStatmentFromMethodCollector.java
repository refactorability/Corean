package ac.code.verifier.engine.visitors;

import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.CodeFragmentResult;
import ac.code.verifier.engine.CodeFragmentResultEnum;
import ac.code.verifier.engine.data.ExtractableCodeFragmentProblemData;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.visitors.helpers.VisitorCodeFragmentHelper;

/**
 * The class ExtractableCodeFragmentWithOrphanStatmentFromConstructorCollector collects data about orphan statements in methods.
 *
 */
public class ExtractableCodeFragmentWithOrphanStatmentFromMethodCollector extends VoidVisitorAdapter<List<ExtractableCodeFragmentProblemData>> {
	
	private List<MethodData> mListMethodData;
	
	/**
	 * Constructor
	 * @param pListMethodData The list with information about methods.
	 */
	public ExtractableCodeFragmentWithOrphanStatmentFromMethodCollector(List<MethodData> pListMethodData) {
		mListMethodData = pListMethodData;
	}
	
	/**
	 * Visits on the MethodDeclaration type nodes.
	 */
	 @Override
	 public void visit(MethodDeclaration md, List<ExtractableCodeFragmentProblemData> collector) {
		 super.visit(md, collector);
		 for(MethodData m : mListMethodData) {
			 if(m.isAnnotated() && m.getRange().equals(md.getRange().get())) {
				 for(Range r : m.getCodeFragmentRanges()) {
					 CodeFragmentResult result = VisitorCodeFragmentHelper.checkOrphanStatement(md, r);
					 if(result.getResult() != CodeFragmentResultEnum.OK) {
						 collector.add(new ExtractableCodeFragmentProblemData(m, result));
					 }
				 } 
			 }
		 }	 
	 }
}

