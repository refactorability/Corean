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
import ac.exceptions.FailedVerifyException;

public abstract class ParserHelper {	
	protected String mSourceFilePath;
	protected CompilationUnit mRootCompilationUnit = null;	
	protected String mAnotation;
	protected List<SynchronizedStmt> mSyncOnThisStmtsList;
	List<MethodData> mMethodData;
	private final String MOVABLE_ANOTATION = "MovableMethod";
	
	/**
	 * Returns the path to the parsed file.
	 * @return
	 */
	public String getSourceFilePath() {
		return mSourceFilePath;
	}

	/**
	 * Returns the compilation unit the parsed file.
	 * @return
	 */
	public CompilationUnit getRootCompilationUnit() {
		return mRootCompilationUnit;
	}
	
	/**
	 * Constructor 
	 * @param pAnotation The annotation we want to test.
	 * @param pFilePath The path to the file containing the annotation.
	 * @throws IOException
	 * @throws FailedVerifyException
	 */
	public ParserHelper(String pAnotation, String pFilePath) throws IOException, FailedVerifyException {
		init(pAnotation, pFilePath, true);
	}
	
	/**
	 * Constructor 
	 * @param pAnnotation The annotation we want to test
	 * @param pFilePath The path to the file containing the annotation
	 * @param pReferToComments Determines whether to parse the comments in the code as well.
	 * @throws IOException
	 * @throws FailedVerifyException
	 */
	public ParserHelper(String pAnnotation, String pFilePath, boolean pReferToComments) throws IOException, FailedVerifyException{
		init(pAnnotation, pFilePath, pReferToComments);	
	}
	
	/**
	 * Initialization method
	 * @param pAnotation The annotation we want to test
	 * @param pFilePath The path to the file containing the annotation
	 * @param pReferToComments Determines whether to parse the comments in the code as well.
	 * @throws IOException
	 * @throws FailedVerifyException
	 */
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
	
    /**
     * Checks whether the method is marked by the annotation being tested.
     * @param pClassName The name of the class that contains the method 
     * @param pMethodName The name of method.
     * @param pNumOfParams The number of parameters in the method.
     * @return
     */
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
	
	/**
	 * Checks whether the method is marked by the annotation being tested.
     * @param pClassName The name of the class that contains the method 
	 * @param pMethodSignature The full signature of the method.
	 * @return
	 */
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
	
	/**
	 * Checks whether the method is marked by "MovableMethod" annotation.
     * @param pClassName The name of the class that contains the method 
     * @param pMethodName The name of method.
     * @param pNumOfParams The number of parameters in the method.
	 * @return
	 */
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
	
	/**
	 * Checks whether the method is marked by "MovableMethod" annotation.
     * @param pClassName The name of the class that contains the method 
	 * @param pMethodSignature The full signature of the method.
	 * @return
	 */
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
