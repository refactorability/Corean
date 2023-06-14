package ac.code.verifier.engine.visitors;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.data.VariableDeclarationInfo;
import ac.code.verifier.engine.data.VariableType;
import ac.code.verifier.engine.var.handlers.VariableDeclarationHandler;
import ac.code.verifier.engine.var.handlers.VariableInfoHandler;
import ac.code.verifier.engine.visitors.helpers.VisitorHelper;

/**
 * The class UnaryExprCollector collects data about local variable declared in methods.
 *
 */
public class VariableInfoFromMethodCollector extends VoidVisitorAdapter<Void> {

	private List<MethodData> mListMethodData;
	
	/**
	 * Constructor
	 * @param pListMethodData The list with information about methods.
	 */
	public VariableInfoFromMethodCollector(List<MethodData> pListMethodData) {
		mListMethodData = pListMethodData;
	}
	
	/**
	 * Visits on the MethodDeclaration type nodes.
	 */
	 @Override
	 public void visit(MethodDeclaration md, Void arg) {
		 super.visit(md, arg);		 
		 
		 String className = VisitorHelper.getContainingClassName(md);
		 String methodSignature = VisitorHelper.getContainingMethodSignature(md);		 
		 MethodData curentMethodData = VisitorHelper.findMethodData(className, methodSignature, mListMethodData);
		 
		 if(!curentMethodData.isAnnotated()) {
			 return;
		 }
		 
		 VariableInfoHandler varInfoHandler = curentMethodData.getVarebleInfoHandler();
		 List<VariableDeclarationInfo> listOfKnownVariablesInScope = new ArrayList<VariableDeclarationInfo>();	 
		 addInstanceVariables(md, varInfoHandler, listOfKnownVariablesInScope);		
		 addParameters(md, varInfoHandler, listOfKnownVariablesInScope);		 
		 BlockStmt bs = getBlockStmtChild(md);
		 
		 if (bs == null) {
			 return;
		 }	 
		 scanLevel(bs, varInfoHandler, listOfKnownVariablesInScope);
	 }

	 /**
	  * Collects information about variable  from the current level
	  * @param pNode The tested node
	  * @param pVarInfoHandler The variable handler.
	  * @param pListOfKnownVariablesInScope The list of variables that already known in the current scope
	  */
	 private void scanLevel(Node pNode, VariableInfoHandler pVarInfoHandler, List<VariableDeclarationInfo> pListOfKnownVariablesInScope) {
		 
		 List<Node> childList = pNode.getChildNodes();
		 if(childList.isEmpty()) {
			 return;
		 }		 
		 
		 for(Node n : childList) {
			//Variable declaration
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {	
				 VariableDeclarator vd = (VariableDeclarator)n;
				 pVarInfoHandler.addVariableByDeclaration(vd.getName().asString(), vd.getRange().get(), VariableType.LOCAL_VARIABLE);
				 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(vd.getName().asString(), vd.getRange().get(), pListOfKnownVariablesInScope);
				 if(isVariableDeclaratorContainsAssign(vd)) {
					 pVarInfoHandler.addVariableChangeVal(vd.getName().asString(), vd.getRange().get(), vd.getRange().get(), false); 
				 }		 
			 }
			 if(n.getMetaModel().toString().equals("SimpleName") && isParameter((SimpleName)n)) {	
				 SimpleName sn = (SimpleName)n;
				 pVarInfoHandler.addVariableByDeclaration(sn.asString(), sn.getRange().get(), VariableType.PARAMETER);
				 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(sn.asString(), sn.getRange().get(), pListOfKnownVariablesInScope);
			 }			 
			 //Variable use
			 if(n.getMetaModel().toString().equals("SimpleName") && isVarName((SimpleName)n)) {
				 SimpleName sn = (SimpleName)n;
				 VariableDeclarationInfo VarDeclarationInfo = VariableDeclarationHandler.findDeclarationByName(sn.asString(), pListOfKnownVariablesInScope);
				 Range varDeclarationPlace = VarDeclarationInfo != null ? VarDeclarationInfo.getVarDeclarationPlace() : null;			 
				 pVarInfoHandler.addVariableUse(sn.asString(), varDeclarationPlace, sn.getRange().get());
			 }
			 if(n.getMetaModel().toString().equals("FieldAccessExpr")) {
				 FieldAccessExpr fae = (FieldAccessExpr)n;
				 String varName = getVarNameFromFieldAccessExpr (fae);
				 
				 if(isVarNameFromFieldAccessExprIsInstanceVariable (fae)) {
					 pVarInfoHandler.addInstanceVariableUse(varName, fae.getRange().get()); 
				 }else {
					 VariableDeclarationInfo VarDeclarationInfo = VariableDeclarationHandler.findDeclarationByName(varName, pListOfKnownVariablesInScope);
					 Range varDeclarationPlace = VarDeclarationInfo != null ? VarDeclarationInfo.getVarDeclarationPlace() : null;			 
					 pVarInfoHandler.addVariableUse(varName, varDeclarationPlace, fae.getRange().get());
				 } 
			 }		 
			//check change 
			 if(n.getMetaModel().toString().equals("UnaryExpr")) {
				 UnaryExpr ue = (UnaryExpr)n;			 
				 String varName = getVarNameFromUnaryExpr(ue);				 
				 VariableDeclarationInfo VarDeclarationInfo = VariableDeclarationHandler.findDeclarationByName(varName, pListOfKnownVariablesInScope);
				 Range varDeclarationPlace = VarDeclarationInfo != null ? VarDeclarationInfo.getVarDeclarationPlace() : null;			 
				 pVarInfoHandler.addVariableChangeVal(varName, varDeclarationPlace, ue.getRange().get(), true);
			 }
			 if(n.getMetaModel().toString().equals("AssignExpr")) {
				 AssignExpr ae = (AssignExpr)n;			 
				 String varName = getVarNameFromAssignExpr(ae);				
				 if(isVarNameFromAssignExprIsInstanceVariable (ae)) {
					 pVarInfoHandler.addInstanceVariableChangeVal(varName, ae.getRange().get(), false); 
				 }else {
					 VariableDeclarationInfo VarDeclarationInfo = VariableDeclarationHandler.findDeclarationByName(varName, pListOfKnownVariablesInScope);
					 Range varDeclarationPlace = VarDeclarationInfo != null ? VarDeclarationInfo.getVarDeclarationPlace() : null;			 
					 pVarInfoHandler.addVariableChangeVal(varName, varDeclarationPlace, ae.getRange().get(), false); 
				 } 
			 }	 
			 
			 //check if need clone
			 List<VariableDeclarationInfo> listOfKnownVariablesForChildScope = pListOfKnownVariablesInScope;
			 if(isCloneOfListOfKnownVariablesRequired(n)) {
				 listOfKnownVariablesForChildScope = VariableDeclarationHandler.clone(pListOfKnownVariablesInScope); 
			 }			 
			 //recursive call
			 scanLevel(n, pVarInfoHandler, listOfKnownVariablesForChildScope);
		 }
	 }
	 
	 /**
	  * Checks whether variable declaration contains assignment.
	  * @param pVariableDeclarator The tested declaration
	  * @return
	  */
	 private boolean isVariableDeclaratorContainsAssign(VariableDeclarator pVariableDeclarator) {
		return pVariableDeclarator.toString().contains("=");
	 }
	 
	 /**
	  * Checks whether the list of variables known in the current scope changes and therefore needs to be cloned.
	  * @param pNode The tested node
	  * @return
	  */
	 private boolean isCloneOfListOfKnownVariablesRequired(Node pNode) {
		 String nodeMetaModel = pNode.getMetaModel().toString();
		 if(nodeMetaModel.equals("BlockStmt")
			|| nodeMetaModel.equals("CatchClause")
			|| nodeMetaModel.equals("ClassOrInterfaceDeclaration")
			|| nodeMetaModel.equals("ConditionalExpr")
			|| nodeMetaModel.equals("DoStmt")
			|| nodeMetaModel.equals("EnumConstantDeclaration")
			|| nodeMetaModel.equals("EnumDeclaration")
			|| nodeMetaModel.equals("ForeachStmt")
			|| nodeMetaModel.equals("ForEachStmt")
			|| nodeMetaModel.equals("ForStmt")
			|| nodeMetaModel.equals("IfStmt")
			|| nodeMetaModel.equals("LocalClassDeclarationStmt")
			|| nodeMetaModel.equals("SwitchEntryStmt")
			|| nodeMetaModel.equals("SwitchStmt")
			|| nodeMetaModel.equals("SynchronizedStmt")
			|| nodeMetaModel.equals("TryStmt")
			|| nodeMetaModel.equals("WhileStmt")) {
					 return true;
				 }				 
			 return false;	
	 }
	 
	 /**
	  * Checks whether this assign expression contains an instance variable 
	  * @param pAssignExpr The tested assign expression
	  * @return
	  */
	 private boolean isVarNameFromAssignExprIsInstanceVariable (AssignExpr pAssignExpr) {
		 Node firstNode = pAssignExpr.getChildNodes().get(0);		 
		 if(firstNode.getMetaModel().toString().equals("FieldAccessExpr")) {
			 return isVarNameFromFieldAccessExprIsInstanceVariable((FieldAccessExpr)firstNode);
		 }
		 return false;
	}

	 /**
	  * Extracts variable name from assign expression.
	  * @param pAssignExpr The assign expression.
	  * @return
	  */
	 private String getVarNameFromAssignExpr (AssignExpr pAssignExpr) {
		 Node firstNode = pAssignExpr.getChildNodes().get(0);		 
		 String varName = "";
		 if(firstNode.getMetaModel().toString().equals("FieldAccessExpr")) {
			 varName = getVarNameFromFieldAccessExpr((FieldAccessExpr)firstNode);
		 } else if(firstNode.getMetaModel().toString().equals("NameExpr")) {
			 varName = firstNode.toString();
		 } 
		 return varName;
	}
	 
	 /**
	  * Extracts variable name from unary expression.
	  * @param pUnaryExpr The unary expression.
	  * @return
	  */
	 private String getVarNameFromUnaryExpr(UnaryExpr pUnaryExpr) {
		 Node firstNode = pUnaryExpr.getChildNodes().get(0);
		 
		 String varName = "";
		 	 
		 if(firstNode.getMetaModel().toString().equals("FieldAccessExpr")) {
			 varName = getVarNameFromFieldAccessExpr((FieldAccessExpr)firstNode);
		 } else if(firstNode.getMetaModel().toString().equals("NameExpr")) {
			 varName = firstNode.toString();
		 } 		 
		 return varName;
	 }
	 
	 /**
	  * Checks whether this field access expression contains an instance variable 
	  * @param pFieldAccessExpr The tested field access expression.
	  * @return
	  */
	 private boolean isVarNameFromFieldAccessExprIsInstanceVariable (FieldAccessExpr pFieldAccessExpr) {
		 return pFieldAccessExpr.getChildNodes().get(0).getMetaModel().toString().equals("ThisExpr");	 
	 }
	 	 
	 /**
	  * Extracts variable name from field access expression.
	  * @param pFieldAccessExpr The field access expression.
	  * @return
	  */
	 private String getVarNameFromFieldAccessExpr (FieldAccessExpr pFieldAccessExpr) {
		 String exp = pFieldAccessExpr.toString();		 
		 String res = exp.substring(0, exp.indexOf("."));	
		 if (res.equals("this")){
			 exp = exp.substring(exp.indexOf(".")+1);
			 res = (exp.indexOf(".") == -1) ? exp : exp.substring(0, exp.indexOf("."));
		 }
		 return res;
	}
	 
	 /**
	  * Checks whether this simple name is a parameter.
	  * @param pSimpleName The tested simple name.
	  * @return
	  */
	 private boolean isParameter(SimpleName pSimpleName) {	 
		 Node parent = pSimpleName.getParentNode().get();		
		 return parent.getMetaModel().toString().equals("Parameter");	 
	 }
	 
	 /**
	  * Checks whether this simple name is a variable name.
	  * @param pSimpleName The tested simple name.
	  * @return
	  */
	 private boolean isVarName(SimpleName pSimpleName) {	 
		 Node parent = pSimpleName.getParentNode().get();
		 
		 if(parent.getMetaModel().toString().equals("ClassOrInterfaceType")
			|| parent.getMetaModel().toString().equals("MethodCallExpr")
			|| parent.getMetaModel().toString().equals("FieldAccessExpr")) {
			 return false;
		 }
		 if(parent.getMetaModel().toString().equals("NameExpr") && parent.getParentNode().get().getMetaModel().toString().equals("FieldAccessExpr")) {
					 return false;
			 }
		 return true;		 
	 }
	 
	 /**
	  * Returns the block of the method. 
	  * @param md Method declaration
	  * @return
	  */
	 private BlockStmt getBlockStmtChild(MethodDeclaration md) {
		 List<Node> childList = md.getChildNodes();
		 if(childList.isEmpty()) {
			 return null;
		 }	 
		 for(Node n : childList) {
			 if(n.getMetaModel().toString().equals("BlockStmt")) {	
				 BlockStmt result = (BlockStmt)n;
				 return result;
			 }
		 }
		 return null;
	 }
	 
	 /**
	  * Adds method parameters.
	  * @param md The method.
	  * @param pVarInfoHandler The variable handler.
	  * @param pListOfKnownVariablesInScope The list of variables that already known in the current scope.
	  */
	 private void addParameters(MethodDeclaration md, VariableInfoHandler pVarInfoHandler, List<VariableDeclarationInfo> pListOfKnownVariablesInScope) {
		 
		 NodeList<Parameter> parameters = md.getParameters();	
		 for(Parameter p : parameters) {
			 pVarInfoHandler.addVariableByDeclaration(p.getName().asString(), p.getRange().get(), VariableType.METHOD_PARAMETER);
			 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(p.getName().asString(), p.getRange().get(), pListOfKnownVariablesInScope);
		 }
	 }
	 
	 /**
	  * Adds instance variables.
	  * @param md The method.
	  * @param pVarInfoHandler The variable handler.
	  * @param pListOfKnownVariablesInScope The list of variables that already known in the current scope.
	  */
	 private void addInstanceVariables(MethodDeclaration md, VariableInfoHandler pVarInfoHandler, List<VariableDeclarationInfo> pListOfKnownVariablesInScope) {
		
		 Node node = md;
		 while(!node.getMetaModel().toString().equals("ClassOrInterfaceDeclaration") && node.getParentNode().isPresent()) {
			 node = node.getParentNode().get();
		 }
		 
		 if(!node.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")) {
			 return;
		 }
		 
		 List<VariableDeclarationInfo> newInstanceVariablesList;
		 
		 for(Node n : node.getChildNodes()) {
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {	
				 VariableDeclarator vd = (VariableDeclarator)n;
				 pVarInfoHandler.addVariableByDeclaration(vd.getName().asString(), vd.getRange().get(), VariableType.INSTANCE_VARIABLE);
				 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(vd.getName().asString(), vd.getRange().get(), pListOfKnownVariablesInScope);
			 }
			 if(n.getMetaModel().toString().equals("VariableDeclarationExpr")) {
				 newInstanceVariablesList = getDeclaredVarsFromVariableDeclarationExpr((VariableDeclarationExpr)n);
				 for(VariableDeclarationInfo vdi : newInstanceVariablesList) {
					 pVarInfoHandler.addVariableByDeclaration(vdi.getVarName(), vdi.getVarDeclarationPlace(), VariableType.INSTANCE_VARIABLE);
					 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(vdi.getVarName(), vdi.getVarDeclarationPlace(), pListOfKnownVariablesInScope); 
				 }			 
			 }
			 if(n.getMetaModel().toString().equals("ExpressionStmt")) {
				 newInstanceVariablesList = getDeclaredVarsFromExpressionStmt((ExpressionStmt)n);
				 for(VariableDeclarationInfo vdi : newInstanceVariablesList) {
					 pVarInfoHandler.addVariableByDeclaration(vdi.getVarName(), vdi.getVarDeclarationPlace(), VariableType.INSTANCE_VARIABLE);
					 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(vdi.getVarName(), vdi.getVarDeclarationPlace(), pListOfKnownVariablesInScope); 
				 }
			 }
			 if(n.getMetaModel().toString().equals("FieldDeclaration")) {
				 newInstanceVariablesList = getDeclaredVarsFromFieldDeclaration((FieldDeclaration)n);
				 for(VariableDeclarationInfo vdi : newInstanceVariablesList) {
					 pVarInfoHandler.addVariableByDeclaration(vdi.getVarName(), vdi.getVarDeclarationPlace(), VariableType.INSTANCE_VARIABLE);
					 VariableDeclarationHandler.addVaebleDeclarationRemoveOldIfExist(vdi.getVarName(), vdi.getVarDeclarationPlace(), pListOfKnownVariablesInScope); 
				 }
			 }
		 } 
	 }
	 
     /**
      * Extracts declared variables from expression statement.
      * @param pExpressionStmt The tested expression statement.
      * @return
      */
	 private List<VariableDeclarationInfo> getDeclaredVarsFromExpressionStmt(ExpressionStmt pExpressionStmt){
		 List<VariableDeclarationInfo> declaredVars = new ArrayList<VariableDeclarationInfo>();	 
		 
		 for(Node n : pExpressionStmt.getChildNodes()) {
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {
				 VariableDeclarator vd = (VariableDeclarator)n;	 
				 declaredVars.add(new VariableDeclarationInfo(vd.getNameAsString(), vd.getRange().get())) ;
			 }
			 if(n.getMetaModel().toString().equals("VariableDeclarationExpr")) {
				 declaredVars.addAll(getDeclaredVarsFromVariableDeclarationExpr((VariableDeclarationExpr)n));
			 }
		 }
		 return declaredVars;		 
	 }
	 
	 /**
	  * Extracts declared variables from field declaration.
	  * @param pFieldDeclaration The tested field declaration.
	  * @return
	  */
	 private List<VariableDeclarationInfo> getDeclaredVarsFromFieldDeclaration(FieldDeclaration pFieldDeclaration){
		 List<VariableDeclarationInfo> declaredVars = new ArrayList<VariableDeclarationInfo>();	
		 for(VariableDeclarator vd : pFieldDeclaration.getVariables()) {
			 declaredVars.add(new VariableDeclarationInfo(vd.getName().toString(), vd.getRange().get()));
		 }
		 return declaredVars;
	 }
	  
	 /**
	  * Extracts declared variables from variable declaration expression.
	  * @param pVariableDeclarationExpr The tested variable declaration expression.
	  * @return
	  */
	 private List<VariableDeclarationInfo> getDeclaredVarsFromVariableDeclarationExpr(VariableDeclarationExpr pVariableDeclarationExpr){
		 List<VariableDeclarationInfo> declaredVars = new ArrayList<VariableDeclarationInfo>();
		 
		 for(Node n : pVariableDeclarationExpr.getChildNodes()) {
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {
				 VariableDeclarator vd = (VariableDeclarator)n;	 
				 declaredVars.add(new VariableDeclarationInfo(vd.getNameAsString(), vd.getRange().get())) ;
			 }
		 }
		 return declaredVars;
	 }
}
