package ac.code.verifier.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.Range;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

import ac.code.verifier.configuration.CodeRefactorabilitySettings;
import ac.code.verifier.engine.coverage.handlers.FragmentCoverageHandler;
import ac.code.verifier.engine.data.ChangeValInfo;
import ac.code.verifier.engine.data.ExtractableCodeFragmentProblemData;
import ac.code.verifier.engine.data.MethodCallFromThisData;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.data.VarebleInfo;
import ac.code.verifier.engine.data.VariableType;
import ac.code.verifier.engine.var.handlers.VariableInfoHandler;
import ac.code.verifier.engine.visitors.ExtractableCodeFragmentWithBrokenStatementFromConstructorCollector;
import ac.code.verifier.engine.visitors.ExtractableCodeFragmentWithBrokenStatementFromMethodCollector;
import ac.code.verifier.engine.visitors.ExtractableCodeFragmentWithOrphanStatmentFromConstructorCollector;
import ac.code.verifier.engine.visitors.ExtractableCodeFragmentWithOrphanStatmentFromMethodCollector;
import ac.code.verifier.engine.visitors.ExtractableCodeFragmentWithReturnCollector;
import ac.code.verifier.engine.visitors.ExtractableCodeFragmentWithThrowCollector;
import ac.code.verifier.engine.visitors.MethodCallCollector;
import ac.code.verifier.engine.visitors.VariableInfoFromConstructorCollector;
import ac.code.verifier.engine.visitors.VariableInfoFromMethodCollector;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;
import exceptions.FailedVerifyException;

public class ExtractableParser extends ParserHelper {

	private String mMarkOfBegin;
	private String mMarkOfEnd;
	private Map<String, String> mStateNameMap=new HashMap<String, String>(); 
	private String mFragmentName;
	private String mActionVerb;

	public ExtractableParser(String pFilePath, String pMarkOfBegin, String pMarkOfEnd, String pAnotation, String pFragmentName, String pActionVerb) throws IOException, FailedVerifyException {
		super(pAnotation, pFilePath);
		mMarkOfBegin = pMarkOfBegin;
		mMarkOfEnd = pMarkOfEnd;
		mFragmentName = pFragmentName;
		mActionVerb = pActionVerb;
		mStateNameMap.put("AnnotationDeclaration", "\"annotation declaration\"");
		mStateNameMap.put("AnnotationMemberDeclaration", "\"annotation member declaration\"");
		mStateNameMap.put("ArrayAccessExpr", "\"array access\"");
		mStateNameMap.put("ArrayCreationExpr", "\"array creation\"");
		mStateNameMap.put("ArrayCreationLevel", "\"array creation\"");
		mStateNameMap.put("ArrayInitializerExpr", "\"array initializer\"");
		mStateNameMap.put("ArrayType", "\"array type\"");
		mStateNameMap.put("AssertStmt", "\"assert\"");
		mStateNameMap.put("AssignExpr", "\"assign expression\"");
		mStateNameMap.put("BinaryExpr", "\"binary expression\"");
		mStateNameMap.put("BlockStmt", "\"block\"");
		mStateNameMap.put("BooleanLiteralExpr", "\"boolean expression\"");
		mStateNameMap.put("BreakStmt", "\"break\"");
		mStateNameMap.put("CastExpr", "\"cast expression\"");
		mStateNameMap.put("CatchClause", "\"catch\"");
		mStateNameMap.put("CharLiteralExpr", "\"char literal\"");
		mStateNameMap.put("ClassExpr", "\"class expression\"");
		mStateNameMap.put("ClassOrInterfaceDeclaration", "\"class declaration\"");
		mStateNameMap.put("ClassOrInterfaceType", "\"class type\"");
		mStateNameMap.put("CompilationUnit", "\"compilation unit\"");
		mStateNameMap.put("ConditionalExpr", "\"conditional expression\"");
		mStateNameMap.put("ConstructorDeclaration", "\"constructor declaration\"");
		mStateNameMap.put("ContinueStmt", "\"continue\"");
		mStateNameMap.put("DoStmt", "\"do-while\" loop");
		mStateNameMap.put("DoubleLiteralExpr", "\"double literal\"");
		mStateNameMap.put("EmptyMemberDeclarationclass", "\"empty member declaration\"");
		mStateNameMap.put("EnclosedExpr", "\"enclosed expression\"");
		mStateNameMap.put("EnumConstantDeclaration", "\"enum declaration\"");
		mStateNameMap.put("EnumDeclaration", "\"enum declaration\"");
		mStateNameMap.put("ExplicitConstructorInvocationStmt", "\"explicit constructor invocation\"");
		mStateNameMap.put("ExpressionStmt", "\"expression\"");
		mStateNameMap.put("FieldAccessExpr", "\"field access expression\"");
		mStateNameMap.put("FieldDeclaration", "\"field declaration\"");
		mStateNameMap.put("ForeachStmt", "\"foreach\"");
		mStateNameMap.put("ForEachStmt", "\"foreach\"");
		mStateNameMap.put("ForStmt", "\"for\"");
		mStateNameMap.put("IfStmt", "\"if\"");
		mStateNameMap.put("ImportDeclaration", "\"import declaration\"");
		mStateNameMap.put("InitializerDeclaration", "\"initializer declaration\"");
		mStateNameMap.put("InstanceOfExpr", "\"instance of expression\"");
		mStateNameMap.put("IntegerLiteralExpr", "\"integer literal expression\"");
		mStateNameMap.put("IntersectionType", "\"intersection type\"");
		mStateNameMap.put("JavadocComment", "\"javadoc comment\"");
		mStateNameMap.put("LabeledStmt", "\"labeled\"");
		mStateNameMap.put("LambdaExpr", "\"lambda expression\"");
		mStateNameMap.put("LineComment", "\"line commente\"");
		mStateNameMap.put("LocalClassDeclarationStmt", "\"local class declaration\"");
		mStateNameMap.put("LongLiteralExpr", "\"long literal expression\"");
		mStateNameMap.put("MarkerAnnotationExpr", "\"marker annotation expression\"");
		mStateNameMap.put("MemberValuePair", "\"member value pair\"");
		mStateNameMap.put("MethodCallExpr", "\"method call expression\"");
		mStateNameMap.put("MethodDeclaration", "\"method declaration\"");
		mStateNameMap.put("MethodReferenceExpr", "\"method reference expression\"");
		mStateNameMap.put("Name", "\"name\"");
		mStateNameMap.put("NameExpr", "\"name expression\"");
		mStateNameMap.put("NodeList", "\"a list of nodes\"");
		mStateNameMap.put("NormalAnnotationExpr", "\"annotation expression\"");
		mStateNameMap.put("NullLiteralExpr", "\"null\"");
		mStateNameMap.put("ObjectCreationExpr", "\"object creation expression\"");
		mStateNameMap.put("PackageDeclaration", "\"package declaration\"");
		mStateNameMap.put("Parameter", "\"parameter\"");
		mStateNameMap.put("PrimitiveType", "\"primitive type\"");
		mStateNameMap.put("ReturnStmt", "\"return\"");
		mStateNameMap.put("SimpleName", "\"name\"");
		mStateNameMap.put("SingleMemberAnnotationExpr", "\"member annotation expression\"");
		mStateNameMap.put("StringLiteralExpr", "\"string literal expression\"");
		mStateNameMap.put("SuperExpr", "\"super expression\"");
		mStateNameMap.put("SwitchStmt", "\"switch\"");
		mStateNameMap.put("SwitchEntryStmt", "\"case\"");
		mStateNameMap.put("SynchronizedStmt", "\"synchronized\"");
		mStateNameMap.put("ThisExpr", "\"this expression\"");
		mStateNameMap.put("ThrowStmt", "\"throw\"");
		mStateNameMap.put("TryStmt", "\"try\"");
		mStateNameMap.put("TypeExpr", "\"type expression\"");
		mStateNameMap.put("TypeParameter", "\"type parameter\"");
		mStateNameMap.put("UnaryExpr", "\"unary expression\"");
		mStateNameMap.put("UnionType", "\"union type\"");
		mStateNameMap.put("UnknownType", "");
		mStateNameMap.put("VariableDeclarationExpr", "\"variable declaration expression\"");
		mStateNameMap.put("VariableDeclarator", "\"variable declaration\"");
		mStateNameMap.put("VoidType", "\"void type\"");
		mStateNameMap.put("WhileStmt", "\"while\" loop");
		mStateNameMap.put("WildcardType", "\"wildcard type\"");
	
		initComments();	
	}
	
	ExtractableParser(String pFilePath, boolean pReferToComments, String pMarkOfBegin, String pMarkOfEnd, String pAnotation, String pFragmentName) throws IOException, FailedVerifyException {
		super(pAnotation, pFilePath, pReferToComments);
		mMarkOfBegin = pMarkOfBegin;
		mMarkOfEnd = pMarkOfEnd;	
		mFragmentName = pFragmentName;
		initComments();
	}
	
	private void initComments() {	
		List<Comment> comments = mRootCompilationUnit.getAllContainedComments();
		for(Comment c : comments) {
			if(c.toString().trim().equals(mMarkOfBegin.trim())) {
				for(MethodData md : mMethodData) {
					if(md.isAnnotated() && md.getBodyRange().contains(c.getRange().get())) {
						md.addCodeFragmentMark(CodeFragment.BEGIN, c.getRange().get());
					}
				}	
			}
			if(c.toString().trim().equals(mMarkOfEnd.trim())) {
				for(MethodData md : mMethodData) {
					if(md.isAnnotated() && md.getBodyRange().contains(c.getRange().get())) {
						md.addCodeFragmentMark(CodeFragment.END, c.getRange().get());
					}
				}
			}
		}
	}
	
	public VerifyResult checkStructureOfMarks() {
		
		for(MethodData md : mMethodData) {
			if(!md.isAnnotated()) {
				continue;
			}
			List<ComparableMarkRange> marksList = md.getSortedFragmentMarks();
			if(marksList.isEmpty()){
				return new VerifyResult(Result.ERROR, "The Method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " marked as \"" + mAnotation + "\" but doesn't contain markings of code fragments.", md.getMethodSignature(), md.getMethodBelongToClass());
			}
			
			boolean afterBegin = false;
			for(ComparableMarkRange mark : marksList) {
				switch (mark.getCodeFragmentType()) {
			    case BEGIN:
			    	if(afterBegin) {
			    		return new VerifyResult(Result.ERROR, "The Method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " Contains two " + mMarkOfBegin + " in a row.", md.getMethodSignature(), md.getMethodBelongToClass());
			    	}
			    	afterBegin = true;
			    	break;
			    case END:
			    	if(!afterBegin) {
			    		return new VerifyResult(Result.ERROR, "The Method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " Contains " + mMarkOfEnd + " without " + mMarkOfBegin + ".", md.getMethodSignature(), md.getMethodBelongToClass());
			    	}
			    	afterBegin = false;
				    break;
				}
			}
			if(afterBegin) {
	    		return new VerifyResult(Result.ERROR, "The Method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " Contains " + mMarkOfBegin + " without " + mMarkOfEnd + ".", md.getMethodSignature(), md.getMethodBelongToClass());
	    	}
		}

		return new VerifyResult(Result.OK, "", null, null); 
	}
	
	private VerifyResult verifyStatementsAndOrphan() {
		List<ExtractableCodeFragmentProblemData> CodeFragmentProblemData = new ArrayList<>();
		VoidVisitor<List<ExtractableCodeFragmentProblemData>> cfdp = new ExtractableCodeFragmentWithBrokenStatementFromMethodCollector(mMethodData);
		cfdp.visit(mRootCompilationUnit, CodeFragmentProblemData);
		if(CodeFragmentProblemData.isEmpty()) {
			cfdp = new ExtractableCodeFragmentWithBrokenStatementFromConstructorCollector(mMethodData);
			cfdp.visit(mRootCompilationUnit, CodeFragmentProblemData);
		}
		
		if(CodeFragmentProblemData.isEmpty()) {
			cfdp = new ExtractableCodeFragmentWithOrphanStatmentFromMethodCollector(mMethodData);
			cfdp.visit(mRootCompilationUnit, CodeFragmentProblemData);
		}
		
		if(CodeFragmentProblemData.isEmpty()) {
			cfdp = new ExtractableCodeFragmentWithOrphanStatmentFromConstructorCollector(mMethodData);
			cfdp.visit(mRootCompilationUnit, CodeFragmentProblemData);
		}
			
		for(ExtractableCodeFragmentProblemData problem : CodeFragmentProblemData) {
			switch (problem.getCodeFragmentResult().getResult()) {
		    case BROKEN_STATEMENT:
		    	String statmentName = mStateNameMap.containsKey(problem.getCodeFragmentResult().getType()) ? mStateNameMap.get(problem.getCodeFragmentResult().getType()) : problem.getCodeFragmentResult().getType();
		    	return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb + " because there are multiple variables to return. " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ problem.getMethodData().getMethodName() + " of the class " + problem.getMethodData().getMethodBelongToClass() + "," + " contains a broken " + statmentName +" statement.", problem.getMethodData().getMethodSignature(), problem.getMethodData().getMethodBelongToClass());
			case ORPHAN_CASE:
				return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + ". " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ problem.getMethodData().getMethodName() + " of the class " + problem.getMethodData().getMethodBelongToClass() + "," + " contains a \"case\" statement but does not contain the surrounding switch.", problem.getMethodData().getMethodSignature(), problem.getMethodData().getMethodBelongToClass());
			case ORPHAN_BREAK:
				return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + ". " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ problem.getMethodData().getMethodName() + " of the class " + problem.getMethodData().getMethodBelongToClass() + "," + " contains a \"break\" statement but does not contain the surrounding loop.", problem.getMethodData().getMethodSignature(), problem.getMethodData().getMethodBelongToClass());
			case ORPHAN_CONTINUE:
				return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + ". " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ problem.getMethodData().getMethodName() + " of the class " + problem.getMethodData().getMethodBelongToClass() + "," + " contains a \"continue\" statement but does not contain the surrounding loop.", problem.getMethodData().getMethodSignature(), problem.getMethodData().getMethodBelongToClass());
			case ORPHAN_BLOCK:
				return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + ". " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ problem.getMethodData().getMethodName() + " of the class " + problem.getMethodData().getMethodBelongToClass() + "," + " contains an \"orphan {}\".", problem.getMethodData().getMethodSignature(), problem.getMethodData().getMethodBelongToClass());
			case OK:
				return null;
			}
		}
		return null;
	}
	
	public VerifyResult checkExtractableCodeFragments(CodeRefactorabilitySettings pCodeRefSet) {
		VerifyResult result = verifyStatementsAndOrphan();
		if(result!=null) {
			boolean isBrocken = result.getMessage().contains(" contains broken ");
			if((pCodeRefSet!=null) && isBrocken && !pCodeRefSet.isListOfStatementsTest()) {
				result = null;
			}
			if((pCodeRefSet!=null) && !isBrocken && !pCodeRefSet.isContinueBreakTest()) {
				result = null;
			}
		}
		
		if(result!=null) {
			return result;
		}
		for(MethodData md : mMethodData) {
			if(!md.isAnnotated()) {
				continue;
			}
			md.prepareRangeCoverageHandlers();
		}
		
		VoidVisitor<Void> returnVisitor = new ExtractableCodeFragmentWithReturnCollector(mMethodData);
		returnVisitor.visit(mRootCompilationUnit, null);
		
		VoidVisitor<Void> throwVisitor = new ExtractableCodeFragmentWithThrowCollector(mMethodData);
		throwVisitor.visit(mRootCompilationUnit, null);
		
		VoidVisitor<Void> variableInfoVisitor = new VariableInfoFromMethodCollector(mMethodData);
		variableInfoVisitor.visit(mRootCompilationUnit, null);
		
		VoidVisitor<Void> variableInfoVisitorForConstructors = new VariableInfoFromConstructorCollector(mMethodData);
		variableInfoVisitorForConstructors.visit(mRootCompilationUnit, null);
		
		for(MethodData md : mMethodData) {
			if(!md.isAnnotated()) {
				continue;
			}
			List<FragmentCoverageHandler> rangeCoverageHandlersList = md.getRangeCoverageHandlersList();
			
			for(FragmentCoverageHandler rch : rangeCoverageHandlersList) {
				if((rch.isExaminedExpressionExists() && !rch.isCovered()) && ((pCodeRefSet==null) || pCodeRefSet.isReturnTest())) {
					return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + ". " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " contains a return statement but there exists a path without a return statement.", md.getMethodSignature(), md.getMethodBelongToClass());
				}
				if(rch.isExaminedExpressionExists()) {
					continue;
				}
				List<String> varsForReturn = findVarShouldBeReturnValsFromExtractedRange(rch.getRange(), md.getVarebleInfoHandler());
				boolean a = ((pCodeRefSet==null) || pCodeRefSet.isLocalVariableTest());
				if((varsForReturn.size()>1) && ((pCodeRefSet==null) || pCodeRefSet.isLocalVariableTest())) {
					String varNames = "(";
					for(int i = 0; i< varsForReturn.size()-2; i++) {
						varNames += varsForReturn.get(i) + ", ";
					}
					varNames += varsForReturn.get(varsForReturn.size()-2) + " and " + varsForReturn.get(varsForReturn.size()-1) + ")";
					
					String secondAction = " ";
					if(mActionVerb.equals("moved")) {
						secondAction = " and moved ";
					}
					
					return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + " because there are multiple variables to return. " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " contains " + varsForReturn.size() + " variables " + varNames + " which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted" + secondAction +  "method.", md.getMethodSignature(), md.getMethodBelongToClass());
				}
			}
		}
		return new VerifyResult(Result.OK, "", "", "");
	}
	
	
	public VerifyResult checkMovableCodeFragments() {
		
		VerifyResult result = verifyStatementsAndOrphan();
		if(result!=null) {
			return result;
		}

		for(MethodData md : mMethodData) {
			if(!md.isAnnotated()) {
				continue;
			}
			md.prepareRangeCoverageHandlers();
		}
		
		VoidVisitor<Void> returnVisitor = new ExtractableCodeFragmentWithReturnCollector(mMethodData);
		returnVisitor.visit(mRootCompilationUnit, null);
		
		VoidVisitor<Void> throwVisitor = new ExtractableCodeFragmentWithThrowCollector(mMethodData);
		throwVisitor.visit(mRootCompilationUnit, null);
		
		VoidVisitor<Void> variableInfoVisitor = new VariableInfoFromMethodCollector(mMethodData);
		variableInfoVisitor.visit(mRootCompilationUnit, null);
		
		VoidVisitor<Void> variableInfoVisitorForConstructors = new VariableInfoFromConstructorCollector(mMethodData);
		variableInfoVisitorForConstructors.visit(mRootCompilationUnit, null);
		
		List<MethodCallFromThisData> methodCall = new ArrayList<>();
		VoidVisitor<List<MethodCallFromThisData>> mcv = new MethodCallCollector();
		mcv.visit(mRootCompilationUnit, methodCall);
		
		for(MethodData md : mMethodData) {
			if(!md.isAnnotated()) {
				continue;
			}
			List<FragmentCoverageHandler> rangeCoverageHandlersList = md.getRangeCoverageHandlersList();
			
			for(FragmentCoverageHandler rch : rangeCoverageHandlersList) {
				if(rch.isExaminedExpressionExists() && !rch.isCovered()) {
					return new VerifyResult(Result.ERROR, "Code fragment cannot be " + mActionVerb  + ". " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " contains a return statement but there exists a path without a return statement.", md.getMethodSignature(), md.getMethodBelongToClass());
				}
				if(rch.isExaminedExpressionExists()) {
					continue;
				}
				
				List<String> instanceVariables = findInstanceVariables(rch.getRange(), md.getVarebleInfoHandler());
				if(instanceVariables.size()>0) {
					String instanceVariableNames;
					if(instanceVariables.size()>1) {
						instanceVariableNames = "s (";
						for(int i = 0; i< instanceVariables.size()-2; i++) {
							instanceVariableNames += instanceVariables.get(i) + ", ";
						}
						instanceVariableNames += instanceVariables.get(instanceVariables.size()-2) + " and " + instanceVariables.get(instanceVariables.size()-1) + ")"; 
					}
					else {
						instanceVariableNames = " (" + instanceVariables.get(0) + ")";
					}
					
					return new VerifyResult(Result.ERROR, "Code fragment cannot be extracted and then moved. " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " contains an assignment to instance variable" + instanceVariableNames + ". This hinders the ability to move this code fragment to another class.", md.getMethodSignature(), md.getMethodBelongToClass());
				}

				List<String> varsForReturn = findVarShouldBeReturnValsFromMovableRange(rch.getRange(), md.getVarebleInfoHandler());
				if(varsForReturn.size()>1) {
					String varNames = "(";
					for(int i = 0; i< varsForReturn.size()-2; i++) {
						varNames += varsForReturn.get(i) + ", ";
					}
					varNames += varsForReturn.get(varsForReturn.size()-2) + " and " + varsForReturn.get(varsForReturn.size()-1) + ")";
					
					return new VerifyResult(Result.ERROR, "Code fragment cannot be extracted. " + "A code fragment marked as \"" + mFragmentName + "\" in the method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " contains " + varsForReturn.size() + " variables " + varNames + " which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value from the extracted method.", md.getMethodSignature(), md.getMethodBelongToClass()); 
				}
				
				for(SynchronizedStmt ss : mSyncOnThisStmtsList) {
					if(rch.getRange().contains(ss.getRange().get())) {
						return new VerifyResult(Result.ERROR, "A code fragment marked as \"" + mFragmentName + "\" in the method "+ md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " is locked on \"this\". This can cause a change in behavior if the code fragment is moved to another class.", md.getMethodSignature(), md.getMethodBelongToClass());
					}
				}
				
				for(MethodCallFromThisData mc : methodCall) {
					if((rch.getRange().contains(mc.getCalledMethodRange())) && !isMovableOrUknown(mc.getParentMethodClassName(), mc.getCalledMethodName(), mc.getNumOfParams())) {
						return new VerifyResult(Result.ERROR, "A code fragment marked as \"" + mFragmentName + "\" in the method " + md.getMethodName() + " of the class " + md.getMethodBelongToClass() + "," + " call not movable method " + mc.getCalledMethodName() + ".", md.getMethodSignature(), md.getMethodBelongToClass());
					}
				}
			}
		}
		
		return new VerifyResult(Result.OK, "", "", "");
	}
	
	private List<String> findInstanceVariables (Range pRange, VariableInfoHandler pVariableInfoHandler){
		List<String> result = new ArrayList<String>();
		
		List<VarebleInfo> varebleInfoList = pVariableInfoHandler.getVarebleInfoList();
		
		for(VarebleInfo vi : varebleInfoList) {
			if((vi.getVarType() ==  VariableType.INSTANCE_VARIABLE)  &&  isChangedInRange(pRange, vi)) {
				result.add(vi.getVarName());
			}
		}	
		return result;
	}
	
	private List<String> findVarShouldBeReturnValsFromMovableRange(Range pRange, VariableInfoHandler pVariableInfoHandler){
		List<String> result = new ArrayList<String>();
		
		List<VarebleInfo> varebleInfoList = pVariableInfoHandler.getVarebleInfoList();
		
		for(VarebleInfo vi : varebleInfoList) {
			if(((vi.getVarType() ==  VariableType.LOCAL_VARIABLE) || (vi.getVarType() ==  VariableType.METHOD_PARAMETER) || (vi.getVarType() ==  VariableType.PARAMETER)) &&  isChangedInRange(pRange, vi) && isUsedAfterRangeBeforeChange(pRange, vi)) {
				result.add(vi.getVarName());
			}
			if((vi.getVarType() ==  VariableType.INSTANCE_VARIABLE)  &&  isChangedInRange(pRange, vi)) {
				result.add(vi.getVarName());
			}
		}
		
		return result;
	}
	
	private List<String> findVarShouldBeReturnValsFromExtractedRange(Range pRange, VariableInfoHandler pVariableInfoHandler){
		List<String> result = new ArrayList<String>();
		
		List<VarebleInfo> varebleInfoList = pVariableInfoHandler.getVarebleInfoList();
		
		for(VarebleInfo vi : varebleInfoList) {
			if(((vi.getVarType() ==  VariableType.LOCAL_VARIABLE) || (vi.getVarType() ==  VariableType.METHOD_PARAMETER) || (vi.getVarType() ==  VariableType.PARAMETER)) &&  isChangedInRange(pRange, vi) && isUsedAfterRangeBeforeChange(pRange, vi)) {
				result.add(vi.getVarName());
			}
		}
		
		return result;
	}
	
	private boolean isChangedInRange(Range pRange, VarebleInfo pVarebleInfo) {
		List<ChangeValInfo> ChangeValPlacesList = pVarebleInfo.getChangeValPlaces();
		for(ChangeValInfo r : ChangeValPlacesList) {
			if(pRange.contains(r.getChangePlace())) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isUsedAfterRangeBeforeChange(Range pRange, VarebleInfo pVarebleInfo) {
		List<Range> UsedPlacesList = pVarebleInfo.getUsesPlaces();
		ChangeValInfo firstChangeAfterRange = findFirstChangeAfterRange(pRange, pVarebleInfo);
		
		if((firstChangeAfterRange != null) && firstChangeAfterRange.isUnaryChange()) {
			return true;
		}
		for(Range r : UsedPlacesList) {
			if(pRange.end.isBefore(r.begin) && ((firstChangeAfterRange == null) || (r.begin.isBefore(firstChangeAfterRange.getChangePlace().begin)) || ((firstChangeAfterRange.getChangePlace().begin.isBefore(r.begin)) && r.end.isBeforeOrEqual(firstChangeAfterRange.getChangePlace().end)))) {
				return true;
			}
		}
		return false;
	}
	
	private ChangeValInfo findFirstChangeAfterRange(Range pRange, VarebleInfo pVarebleInfo) {
		List<ChangeValInfo> ChangeValPlacesList = pVarebleInfo.getChangeValPlaces();
		ChangeValInfo result = null;
		for(ChangeValInfo ChangeRange : ChangeValPlacesList) {
			if(pRange.end.isBefore(ChangeRange.getChangePlace().begin)) {
				if(result == null) {
					result = ChangeRange;
				}else if(ChangeRange.getChangePlace().end.isBefore(result.getChangePlace().begin)) {
					result = ChangeRange;
				}
			}
		}
		
		return result;
	}


}
