package ac.code.verifier.engine.visitors;

import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ac.code.verifier.engine.data.AssignExprData;
import ac.code.verifier.engine.visitors.helpers.VisitorHelper;

public class AssignExprCollector extends VoidVisitorAdapter<List<AssignExprData>> {

	 @Override
	 public void visit(AssignExpr ae, List<AssignExprData> collector) {
		 super.visit(ae, collector);
		 
		 Node firstNode = ae.getChildNodes().get(0);		 
		 String varName = "";	
		 
		 if(firstNode.getMetaModel().toString().equals("FieldAccessExpr")) {
			 varName = getVarName((FieldAccessExpr)firstNode);
		 } else if(firstNode.getMetaModel().toString().equals("NameExpr")) {
			 varName = firstNode.toString();
		 } else {
			 return;
		 }
		 
		 Node node = ae;		 
		 if(isClassFieldMember(node, varName, ae.getRange().get())) {
			 collector.add(new AssignExprData(varName, VisitorHelper.getContainingClassName(node), VisitorHelper.getContainingMethodName(node), ae.getRange().get(), VisitorHelper.getContainingMethodSignature(node)));
		 }	
	 }
	 
	 protected boolean isClassFieldMember(Node pCurentNode, String pVarName, Range pRange) {
		 Node node = pCurentNode;
		 
		 while(node.getMetaModel().toString().equals("AssignExpr") || node.getMetaModel().toString().equals("ExpressionStmt")) {
			 node = node.getParentNode().get();
		 }
		 
		 if(isVarDeclaredInFirstLevel(node, pVarName, pRange)) {	
				return false;
		 }
		 
		 if(isVarDeclaredInParameters(node, pVarName)) {
			 return false;
		 }
		 
		 node = node.getParentNode().get();		 
		 while((!node.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")) && node.getParentNode().isPresent()) {
			if(isVarDeclaredInCurentLevel(node, pVarName)) {	
				return false;
			}
			node = node.getParentNode().get();
		 }
		 
		 if(node.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")) {
			 return isVarDeclaredInClassLevel(node, pVarName);
		 }
		 return false;
	 }
	 	 
	 protected boolean isVarDeclaredInClassLevel(Node pCurentNode, String pVarName) {
		
		 for(Node n : pCurentNode.getChildNodes()) {
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {				 
				 if(n.toString().equals(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("VariableDeclarationExpr")) {
				 if(getDeclaredVarsFromVariableDeclarationExpr((VariableDeclarationExpr)n, null).contains(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("ExpressionStmt")) {
				 if(getDeclaredVarsFromExpressionStmt((ExpressionStmt)n, null).contains(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("FieldDeclaration")) {
				 if(getDeclaredVarsFromFieldDeclaration((FieldDeclaration)n).contains(pVarName)){
					 return true;
				 }
			 }
		 } 
			return false;
	 }
	 
	 protected boolean isVarDeclaredInParameters(Node pCurentNode, String pVarName) {
		 Node node = pCurentNode; 
		 
		 while(node.getParentNode().isPresent()) {
			 node = node.getParentNode().get();
			 if(node.getMetaModel().toString().equals("MethodDeclaration")) {	
				 MethodDeclaration md = (MethodDeclaration)node;
				 for(Parameter p : md.getParameters()) {
					 if(p.getNameAsString().equals(pVarName)) {
						 return true;
					 }
				 }
				 return false;
			}
		 
			 if(node.getMetaModel().toString().equals("ConstructorDeclaration")) {	
				 ConstructorDeclaration cd = (ConstructorDeclaration)node;
				 for(Parameter p : cd.getParameters()) {
					 if(p.getNameAsString().equals(pVarName)) {
						 return true;
					 }
				 }
				 return false;
			} 
			 
			 if(node.getMetaModel().toString().equals("CatchClause")) {	
				 CatchClause cc = (CatchClause)node;		
				 if(cc.getParameter().getNameAsString().equals(pVarName)) {
					 return true;
				 }
			} 	 		 
		 }
		 
		 return false;
	 }
	 
	 //Check if declared in current level "before" the use 
	 protected boolean isVarDeclaredInFirstLevel(Node pCurentNode, String pVarName, Range pRange) {
		  
		 for(Node n : pCurentNode.getChildNodes()) {
			 
			 if(n.getRange().get().isAfter(pRange.begin)) {//Relevant for the first level only.
				continue; 
			 }  
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {				 
				 if(n.toString().equals(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("VariableDeclarationExpr")) {
				 if(getDeclaredVarsFromVariableDeclarationExpr((VariableDeclarationExpr)n, pRange).contains(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("ExpressionStmt")) {
				 if(getDeclaredVarsFromExpressionStmt((ExpressionStmt)n, pRange).contains(pVarName)){
					 return true;
				 }
			 }
		 } 
			return false;
	 }
	 
	 
	 protected boolean isVarDeclaredInCurentLevel(Node pCurentNode, String pVarName) {
		  
		 for(Node n : pCurentNode.getChildNodes()) {		  
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {				 
				 if(n.toString().equals(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("VariableDeclarationExpr")) {
				 if(getDeclaredVarsFromVariableDeclarationExpr((VariableDeclarationExpr)n, null).contains(pVarName)){
					 return true;
				 }
			 }
			 if(n.getMetaModel().toString().equals("ExpressionStmt")) {
				 if(getDeclaredVarsFromExpressionStmt((ExpressionStmt)n, null).contains(pVarName)){
					 return true;
				 }
			 }
		 } 
			return false;
	 }

	 
	 protected String getVarName(FieldAccessExpr pFieldAccessExpr) {
		 String exp = pFieldAccessExpr.toString();		 
		 String res = exp.substring(0, exp.indexOf("."));

		 if (res.equals("this")){
			 exp = exp.substring(exp.indexOf(".")+1);
			 res = (exp.indexOf(".") == -1) ? exp : exp.substring(0, exp.indexOf("."));
		 }
		 return res;
	}
	 	 
	 protected List<String> getDeclaredVarsFromExpressionStmt(ExpressionStmt pExpressionStmt, Range pRange){
		 List<String> declaredVars = new ArrayList<String>();	 
		 
		 for(Node n : pExpressionStmt.getChildNodes()) {
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {
				 if(pRange == null || n.getRange().get().isBefore(pRange.begin)) {
					 declaredVars.add(n.toString());
				 }
			 }
			 if(n.getMetaModel().toString().equals("VariableDeclarationExpr")) {
				 declaredVars.addAll(getDeclaredVarsFromVariableDeclarationExpr((VariableDeclarationExpr)n, pRange));
			 }
		 }
		 return declaredVars;		 
	 }
	 	 
	 protected List<String> getDeclaredVarsFromFieldDeclaration(FieldDeclaration pFieldDeclaration){
		 List<String> declaredVars = new ArrayList<String>();	
		 for(VariableDeclarator vd : pFieldDeclaration.getVariables()) {
			 declaredVars.add(vd.getName().toString());
		 }
		 return declaredVars;
	 }
	 	 
	 protected List<String> getDeclaredVarsFromVariableDeclarationExpr(VariableDeclarationExpr pVariableDeclarationExpr, Range pRange){
		 List<String> declaredVars = new ArrayList<String>();
		 
		 for(Node n : pVariableDeclarationExpr.getChildNodes()) {
			 if(n.getMetaModel().toString().equals("VariableDeclarator")) {
				 if(pRange == null || n.getRange().get().isBefore(pRange.begin)) {
					 declaredVars.add(((VariableDeclarator)n).getNameAsString()); 
				 }
			 }
		 }
		 return declaredVars;
	 }	
}
