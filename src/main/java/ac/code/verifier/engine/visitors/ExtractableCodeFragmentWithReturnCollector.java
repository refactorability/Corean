package ac.code.verifier.engine.visitors;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.coverage.handlers.FragmentCoverageHandler;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.visitors.helpers.VisitorHelper;

/**
 * The class ExtractableCodeFragmentWithReturnCollector collects data about return statements.
 *
 */
public class ExtractableCodeFragmentWithReturnCollector  extends VoidVisitorAdapter<Void> {

	private List<MethodData> mListMethodData;
	
	/**
	 * Constructor
	 * @param pListMethodData The list with information about methods.
	 */
	public ExtractableCodeFragmentWithReturnCollector(List<MethodData> pListMethodData) {
		mListMethodData = pListMethodData;
	}
	/**
	 * Visits on the ReturnStmt type nodes.
	 */
	 @Override
	 public void visit(ReturnStmt rs, Void arg) {
		 super.visit(rs, arg);
		
		 Node node = rs;
		 
		 String className = VisitorHelper.getContainingClassName(node);
		 String methodSignature = VisitorHelper.getContainingMethodSignature(node);
		 MethodData curentMethodData = VisitorHelper.findMethodData(className, methodSignature, mListMethodData);
		 
		 if(!curentMethodData.isAnnotated() || curentMethodData.isConstructor() || curentMethodData.getMethodReturnType().equals("void")) {
			 return;
		 }
		 
		 List<FragmentCoverageHandler> RangeCoverageHandlersList = curentMethodData.getRangeCoverageHandlersList();
		 
		 //List of ranges which contains this return
		 List<FragmentCoverageHandler> CurentRCHList = new ArrayList<FragmentCoverageHandler>();
		 
		 for(FragmentCoverageHandler rch : RangeCoverageHandlersList) {
			 if(rch.getRange().contains(node.getRange().get())) {
				 rch.setExaminedExpressionExists();
				 CurentRCHList.add(rch);
			 }
		 }
		 
		 if(CurentRCHList.isEmpty()) {
			 return;
		 }
		
		 ///go up with return and update
		 Node prevNode;
		 boolean canContinue = true;
		 while(canContinue && node.getParentNode().isPresent() && !node.getMetaModel().toString().equals("MethodDeclaration") && !node.getMetaModel().toString().equals("ConstructorDeclaration")) {
			 prevNode = node;
			 node = node.getParentNode().get();
			 
			 if(node.getMetaModel().toString().equals("IfStmt")) {
				 canContinue = false;
				 for(FragmentCoverageHandler curentRCH : CurentRCHList) {
					 if(curentRCH.isCovered()) {
						 continue;
					 }
					 curentRCH.coverChildOfIf((IfStmt)node, prevNode.getRange().get());
					 if(curentRCH.isIfCovered((IfStmt)node)) {					 
						 canContinue = true; 
					 }
				 } 
			 } 
			 else if(node.getMetaModel().toString().equals("SwitchStmt")) {	
					 canContinue = false;
					 for(FragmentCoverageHandler curentRCH : CurentRCHList) {
						 if(curentRCH.isCovered()) {
							 continue;
						 }
						 curentRCH.coverChildOfSwitch((SwitchStmt)node, prevNode.getRange().get());
						 if(curentRCH.isSwitchCovered((SwitchStmt)node)) {					 
							 canContinue = true; 
						 }
					 } 
				 }
			 else if(node.getMetaModel().toString().equals("TryStmt")) {	
				 canContinue = false;
				 for(FragmentCoverageHandler curentRCH : CurentRCHList) {
					 if(curentRCH.isCovered()) {
						 continue;
					 }
					 curentRCH.coverChildOfTry((TryStmt)node, prevNode.getRange().get());
					 if(curentRCH.isTryCovered((TryStmt)node)) {					 
						 canContinue = true; 
					 }
				 } 
			 }			
			 else {
				 for(FragmentCoverageHandler curentRCH : CurentRCHList) {
					 if(node.getRange().get().contains(curentRCH.getRange())) {
						 curentRCH.setCovered(); 
					 }
				 }
			 }			 
		 }
	 }
}
