package ac.code.verifier.engine.visitors.helpers;

import java.util.List;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import ac.code.verifier.engine.CodeFragmentResult;
import ac.code.verifier.engine.CodeFragmentResultEnum;

public class VisitorCodeFragmentHelper {
	public static CodeFragmentResult checkBrokenStatement(Node pNode, Range pFragmentRange) {
		CodeFragmentResult res = verifyListOfStatements(pNode, pFragmentRange);
		if(res != null) {
			return res;
		}		
		return new CodeFragmentResult(CodeFragmentResultEnum.OK, "");		
	}
	
	public static CodeFragmentResult checkOrphanStatement(Node pNode, Range pFragmentRange) {		
		CodeFragmentResult res = verifyRequiredParents(pNode, pFragmentRange);
		if(res != null) {
			return res;
		}	
		return new CodeFragmentResult(CodeFragmentResultEnum.OK, "");		
	}
	
	private static CodeFragmentResult verifyListOfStatements(Node pNode, Range pFragmentRange) {
		
		if(pNode.getRange().get().begin.isBeforeOrEqual(pFragmentRange.begin) && pNode.getRange().get().end.isAfterOrEqual(pFragmentRange.end)) {
			List<Node> list = pNode.getChildNodes();
			CodeFragmentResult res;
			for(Node n : list) {
				res = verifyListOfStatements(n, pFragmentRange);
				if (res!=null) {
					return res;
				}
			}
		}
				
		if(pNode.getRange().get().begin.isBefore(pFragmentRange.begin) && pNode.getRange().get().end.isAfter(pFragmentRange.begin) 
				&& pNode.getRange().get().end.isBefore(pFragmentRange.end)){
				return new CodeFragmentResult(CodeFragmentResultEnum.BROKEN_STATEMENT, pNode.getMetaModel().toString());
			}
			
			if(pNode.getRange().get().begin.isAfter(pFragmentRange.begin) && pNode.getRange().get().begin.isBefore(pFragmentRange.end) 
					&& pNode.getRange().get().end.isAfter(pFragmentRange.end)){
					return new CodeFragmentResult(CodeFragmentResultEnum.BROKEN_STATEMENT, pNode.getMetaModel().toString());
				}
		
		return null;
		
		}
	
	private static CodeFragmentResult verifyRequiredParents(Node pNode, Range pFragmentRange) {
		
		if((pNode.getMetaModel().toString().equals("SwitchEntry")) && pFragmentRange.contains(pNode.getRange().get())){
			if(!isParentOfSwitchEntryExists(pNode, pFragmentRange)) {
				return new CodeFragmentResult(CodeFragmentResultEnum.ORPHAN_CASE, pNode.getMetaModel().toString());
			}
		}
		if((pNode.getMetaModel().toString().equals("ContinueStmt")) && pFragmentRange.contains(pNode.getRange().get())){
			if(!isLoopParentExists(pNode, pFragmentRange)) {
				return new CodeFragmentResult(CodeFragmentResultEnum.ORPHAN_CONTINUE, pNode.getMetaModel().toString());
			}
		}
		if((pNode.getMetaModel().toString().equals("BreakStmt")) && pFragmentRange.contains(pNode.getRange().get())){
			if(!isParentOfBreakEntryExists(pNode, pFragmentRange)) {
				return new CodeFragmentResult(CodeFragmentResultEnum.ORPHAN_BREAK, pNode.getMetaModel().toString());
			}
		}
		if((pNode.getMetaModel().toString().equals("BlockStmt")) && pFragmentRange.contains(pNode.getRange().get())){
			if(!isParentOfBlockExists(pNode, pFragmentRange)) {
				return new CodeFragmentResult(CodeFragmentResultEnum.ORPHAN_BLOCK, pNode.getMetaModel().toString());
			}
		}

		List<Node> list = pNode.getChildNodes();
		CodeFragmentResult res;
		for(Node n : list) {
			res = verifyRequiredParents(n, pFragmentRange);
			if (res!=null) {
				return res;
			}
		}
		return null;		
	}
	
	private static boolean isParentOfSwitchEntryExists(Node pNode, Range pFragmentRange) {
		Node node = pNode;
		while(!(node.getMetaModel().toString().equals("MethodDeclaration") || node.getMetaModel().toString().equals("ConstructorDeclaration")) && node.getParentNode().isPresent()) {
			if(node.getRange().get().strictlyContains(pFragmentRange)){
				return false;
			}
			if(node.getMetaModel().toString().equals("SwitchStmt")) {
				return true;
			}
			node = node.getParentNode().get();
		}
		return false;
	}
	
	private static boolean isParentOfBreakEntryExists(Node pNode, Range pFragmentRange) {
		Node node = pNode;
		while(!(node.getMetaModel().toString().equals("MethodDeclaration") || node.getMetaModel().toString().equals("ConstructorDeclaration")) && node.getParentNode().isPresent()) {		
			if(node.getRange().get().strictlyContains(pFragmentRange)){
				return false;
			}
			if(node.getMetaModel().toString().equals("WhileStmt") || node.getMetaModel().toString().equals("DoStmt") 
					|| node.getMetaModel().toString().equals("ForStmt") || node.getMetaModel().toString().equals("ForeachStmt") 
					|| node.getMetaModel().toString().equals("ForEachStmt")
					|| node.getMetaModel().toString().equals("SwitchStmt")) {
				return true;
			}
			node = node.getParentNode().get();
		}
		return false;
	}
	
	private static boolean isLoopParentExists(Node pNode, Range pFragmentRange) {
		Node node = pNode;
		while(!(node.getMetaModel().toString().equals("MethodDeclaration") || node.getMetaModel().toString().equals("ConstructorDeclaration")) && node.getParentNode().isPresent()) {
			if(node.getRange().get().strictlyContains(pFragmentRange)){
				return false;
			}
			if(node.getMetaModel().toString().equals("WhileStmt") || node.getMetaModel().toString().equals("DoStmt") 
					|| node.getMetaModel().toString().equals("ForStmt") || node.getMetaModel().toString().equals("ForeachStmt")
					|| node.getMetaModel().toString().equals("ForEachStmt")) {
				return true;
			}
			node = node.getParentNode().get();
		}
		return false;
	}
	
	private static boolean isParentOfBlockExists(Node pNode, Range pFragmentRange) {
		Node node = pNode; 
		while(!node.getMetaModel().toString().equals("MethodDeclaration")){ //|| node.getMetaModel().toString().equals("ConstructorDeclaration")) && node.getParentNode().isPresent()) {
			if(node.getRange().get().strictlyContains(pFragmentRange)){
				return false;
			}
			if(node.getMetaModel().toString().equals("AnnotationDeclaration") || node.getMetaModel().toString().equals("ArrayInitializerExpr") 
					|| node.getMetaModel().toString().equals("CatchClause") || node.getMetaModel().toString().equals("ClassOrInterfaceDeclaration")
					|| node.getMetaModel().toString().equals("DoStmt") || node.getMetaModel().toString().equals("EnumConstantDeclaration")
					|| node.getMetaModel().toString().equals("EnumDeclaration") || node.getMetaModel().toString().equals("ForeachStmt") || node.getMetaModel().toString().equals("ForEachStmt")
					|| node.getMetaModel().toString().equals("ForStmt") || node.getMetaModel().toString().equals("IfStmt")
					|| node.getMetaModel().toString().equals("InitializerDeclaration") || node.getMetaModel().toString().equals("LocalClassDeclarationStmt")
					|| node.getMetaModel().toString().equals("MethodDeclaration") || node.getMetaModel().toString().equals("SwitchStmt")
					|| node.getMetaModel().toString().equals("SynchronizedStmt") || node.getMetaModel().toString().equals("TryStmt")
					|| node.getMetaModel().toString().equals("TypeParameter") || node.getMetaModel().toString().equals("VoidType")
					|| node.getMetaModel().toString().equals("WhileStmt")) {
				return true;
			}
			node = node.getParentNode().get();
		}
		return false;
	}
}
