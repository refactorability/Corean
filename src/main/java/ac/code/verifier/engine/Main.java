/*package ac.code.verifier.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import ac.code.verifier.engine.data.AssignExprData;
import ac.code.verifier.engine.visitors.AnnotatedMethodsInfoCollector;
import ac.code.verifier.engine.visitors.AssignExprCollector;
import ac.code.verifier.engine.visitors.MethodCallCollector;
import ac.code.verifier.engine.visitors.SynchronizedOnThisStmtCollector;
import ac.code.verifier.helper.VerifyResult;
import exceptions.FailedVerifyException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String file = "C:\\Users\\97254\\þþeclipse-workspace_code verifier\\code-verifier\\src\\test\\java\\ac\\test3\\Test3b.java";
		
		MoveableVerifier verifier = new MoveableVerifier("Moveable", file);
		VerifyResult res = verifier.verify();
		System.out.println(res.getResult() + "  " + res.getMessage());
		
	try {
			ParserHelper ph = new MovebleParser("Moveable", file);
			
			List<AssignExprData> AssignExpr = new ArrayList<>();
			VoidVisitor<List<AssignExprData>> ae = new AssignExprCollector();
			ae.visit(ph.getRootCompilationUnit(), AssignExpr);
			AssignExpr.forEach(n -> System.out.println("///////////////////////////////////AE: " + System.lineSeparator() + n.toString()));
			
					
		}catch (ParseProblemException | IOException | FailedVerifyException e) {
			
			e.printStackTrace();
		}
		
		
	/*	try {
			ParserHelper ph = new ParserHelper(file);
			
			/*List<String> methodNames = new ArrayList<>();
			VoidVisitor<List<String>> methodNameCollector = new MethodCollector();
			methodNameCollector.visit(ph.getRootCompilationUnit(), methodNames);
			methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
			
			
			List<com.github.javaparser.ast.comments.Comment> comments = ph.getRootCompilationUnit().getAllContainedComments();
			comments.forEach(System.out::println);*/
			
		/*	List<AssignExprData> AssignExpr = new ArrayList<>();
			VoidVisitor<List<AssignExprData>> ae = new AssignExprCollector();
			ae.visit(ph.getRootCompilationUnit(), AssignExpr);
			AssignExpr.forEach(n -> System.out.println("///////////////////////////////////AE: " + System.lineSeparator() + n.toString()));
			
			
			
			List<MethodCallFromThisData> methodCall = new ArrayList<>();
			VoidVisitor<List<MethodCallFromThisData>> mm = new MethodCallCollector();
			mm.visit(ph.getRootCompilationUnit(), methodCall);
			methodCall.forEach(n -> System.out.println("///////////////////////////////////T: " + System.lineSeparator() + n.toString()));
			
			
			
			
			
		/*	List<String> methodNames2 = new ArrayList<>();
			VoidVisitor<List<String>> mm = new MethodCallCollector();
			mm.visit(ph.getRootCompilationUnit(), methodNames2);
			System.out.println("///////////////////////////////////T: " + methodNames2.size());
			methodNames2.forEach(n -> System.out.println("///////////////////////////////////T: " + System.lineSeparator() + n.toString()));
			*/
			
		/*	List<SynchronizedStmt> synchronizedStmtsList = new ArrayList<>();
			VoidVisitor<List<SynchronizedStmt>> ssc = new SynchronizedStmtCollector();
			ssc.visit(ph.getRootCompilationUnit(), synchronizedStmtsList);
			//synchronizedStmtsList.forEach(n -> System.out.println("///////////////////////////////////: " + System.lineSeparator() + n.toString()));
			
			
			
			List<MethodData> methodNames = new ArrayList<>();
			VoidVisitor<List<MethodData>> methodNameCollector = new AnnotatedMethodsInfoCollector("Moveable", synchronizedStmtsList);
			methodNameCollector.visit(ph.getRootCompilationUnit(), methodNames);
			methodNames.forEach(n -> System.out.println("///////////////////////////////////: " + System.lineSeparator() + n.toString()));
			
			
			//List<com.github.javaparser.ast.comments.Comment> comments = ph.getRootCompilationUnit().getAllContainedComments();
			//comments.forEach(System.out::println);
					
			

		} catch (ParseProblemException | IOException e) {
			
			e.printStackTrace();
		}
		


	}

}*/
