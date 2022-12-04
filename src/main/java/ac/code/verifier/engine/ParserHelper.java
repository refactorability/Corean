package ac.code.verifier.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import ac.code.verifier.engine.data.MethodData;
import ac.code.verifier.engine.visitors.AnnotatedConstructorsInfoCollector;
import ac.code.verifier.engine.visitors.AnnotatedMethodsInfoCollector;
import ac.code.verifier.engine.visitors.SynchronizedOnThisStmtCollector;
import exceptions.FailedVerifyException;

public abstract class ParserHelper {	
	protected String mSourceFilePath;
	protected CompilationUnit mRootCompilationUnit = null;	
	protected String mAnotation;
	protected List<SynchronizedStmt> mSyncOnThisStmtsList;
	List<MethodData> mMethodData;
	private final String MOVABLE_ANOTATION = "MovableMethod";
	
	public String getSourceFilePath() {
		return mSourceFilePath;
	}

	public CompilationUnit getRootCompilationUnit() {
		return mRootCompilationUnit;
	}
	
	public ParserHelper(String pAnotation, String pFilePath) throws IOException, FailedVerifyException {
		init(pAnotation, pFilePath, true);
	}
	
	public ParserHelper(String pAnotation, String pFilePath, boolean pReferToComments) throws IOException, FailedVerifyException{
		init(pAnotation, pFilePath, pReferToComments);	
	}
	
	private void init(String pAnotation, String pFilePath, boolean pReferToComments) throws IOException, FailedVerifyException {
		mSourceFilePath = pFilePath;
        String sourceString = new String(Files.readAllBytes(Paths.get(mSourceFilePath)));
        mAnotation = pAnotation;
        ParserConfiguration parserConfiguration = new ParserConfiguration().setAttributeComments(pReferToComments);
        StaticJavaParser.setConfiguration(parserConfiguration);
        
        try {
        	mRootCompilationUnit = StaticJavaParser.parse(sourceString);
        }
        catch(Exception e) {
        	
        	 if (mRootCompilationUnit == null) 
        	 {
        		 throw new FailedVerifyException(e.getMessage());		 
        	 }
        }

        mSyncOnThisStmtsList = new ArrayList<>();
		VoidVisitor<List<SynchronizedStmt>> synStmtCollector = new SynchronizedOnThisStmtCollector();
		synStmtCollector.visit(mRootCompilationUnit, mSyncOnThisStmtsList);
		
		mMethodData = new ArrayList<>();
		VoidVisitor<List<MethodData>> MethodsInfoCollector = new AnnotatedMethodsInfoCollector(mAnotation, mSyncOnThisStmtsList, MOVABLE_ANOTATION);
		MethodsInfoCollector.visit(mRootCompilationUnit, mMethodData);	
		
		VoidVisitor<List<MethodData>> ConstructorsInfoCollector = new AnnotatedConstructorsInfoCollector(mAnotation, mSyncOnThisStmtsList, MOVABLE_ANOTATION);
		ConstructorsInfoCollector.visit(mRootCompilationUnit, mMethodData);	
	}
	
	protected boolean isAnotatedOrUknown(String pClassName, String pMethodName, int pNumOfParams) {
		boolean find = false;
		for(MethodData md : mMethodData) {
			if(md.getMethodBelongToClass().equals(pClassName) && md.getMethodName().equals(pMethodName) && (md.getNumOfParams() == pNumOfParams)) {
				if(md.isAnnotated()) {
					return true;
				}
				find = true;
			}
		}
		return !find;
	}
	
	protected boolean isAnotatedOrUknown(String pClassName, String pMethodSignature) {
		boolean find = false;
		for(MethodData md : mMethodData) {
			if(md.getMethodBelongToClass().equals(pClassName) && md.getMethodSignature().equals(pMethodSignature)) {
				if(md.isAnnotated()) {
					return true;
				}
				find = true;
			}
		}
		return !find;
	}
	
	protected boolean isMovableOrUknown(String pClassName, String pMethodName, int pNumOfParams) {
		boolean find = false;
		for(MethodData md : mMethodData) {
			if(md.getMethodBelongToClass().equals(pClassName) && md.getMethodName().equals(pMethodName) && (md.getNumOfParams() == pNumOfParams)) {
				if(md.isMovable()) {
					return true;
				}
				find = true;
			}
		}
		return !find;
	}
	
	protected boolean isMovableOrUknown(String pClassName, String pMethodSignature) {
		boolean find = false;
		for(MethodData md : mMethodData) {
			if(md.getMethodBelongToClass().equals(pClassName) && md.getMethodSignature().equals(pMethodSignature)) {
				if(md.isMovable()) {
					return true;
				}
				find = true;
			}
		}
		return !find;
	}	
}
