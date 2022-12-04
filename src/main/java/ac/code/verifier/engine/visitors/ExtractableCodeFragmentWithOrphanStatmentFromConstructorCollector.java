package ac.code.verifier.engine.visitors;

import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.CodeFragmentResult;
import ac.code.verifier.engine.CodeFragmentResultEnum;
import ac.code.verifier.engine.data.ExtractableCodeFragmentProblemData;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.visitors.helpers.VisitorCodeFragmentHelper;

public class ExtractableCodeFragmentWithOrphanStatmentFromConstructorCollector extends VoidVisitorAdapter<List<ExtractableCodeFragmentProblemData>> {
	
	private List<MethodData> mListMethodData;
	
	public ExtractableCodeFragmentWithOrphanStatmentFromConstructorCollector(List<MethodData> pListMethodData) {
		mListMethodData = pListMethodData;
	}
	
	 @Override
	 public void visit(ConstructorDeclaration cd, List<ExtractableCodeFragmentProblemData> collector) {
		 super.visit(cd, collector);
		 for(MethodData m : mListMethodData) {
			 if(m.isAnnotated() && m.getRange().equals(cd.getRange().get())) {
				 for(Range r : m.getCodeFragmentRanges()) {
					 CodeFragmentResult result = VisitorCodeFragmentHelper.checkOrphanStatement(cd, r);
					 if(result.getResult() != CodeFragmentResultEnum.OK) {
						 collector.add(new ExtractableCodeFragmentProblemData(m, result));
					 }
				 } 
			 }
		 }	 
	 }
}
