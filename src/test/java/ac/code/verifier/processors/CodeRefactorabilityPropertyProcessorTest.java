package ac.code.verifier.processors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ac.code.verifier.configuration.CodeRefactorabilitySettings;
import ac.code.verifier.configuration.RefactorabilitySettings;
import ac.code.verifier.engine.CodeFragmentPropertiesVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

class CodeRefactorabilityPropertyProcessorTest {
	
	CodeRefactorabilitySettings settings;
	
	CodeRefactorabilityPropertyProcessor processor = new CodeRefactorabilityPropertyProcessor();
	
	RefactorabilitySettings refactorabilitySettings = new RefactorabilitySettings();
	
	@BeforeEach
    public void init() {
    	settings = new CodeRefactorabilitySettings();
		settings.setListOfStatementsTest(true);
		settings.setContinueBreakTest(true);
		settings.setReturnTest(true);
		settings.setLocalVariableTest(true);
		settings.setMarkOfBegin("/*@RefactorableBegin*/");
		settings.setMarkOfEnd("/*@RefactorableEnd*/");
		settings.setAnnotationActionVerb("refactored");
		settings.setAnnotationMeaning("Refactorable");
    }
	
	@Test
	void testMarkCorrectCase1() {	
		
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;

		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/marks/RefactorableCodeMarkCorrect1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.verifyStructureOfMarks();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkErrorCase1() {	
		
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/marks/RefactorableCodeMarkError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.verifyStructureOfMarks();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class RefactorableCodeMarkError1, Contains /*@RefactorableBegin*/ without /*@RefactorableEnd*/.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInvalidStatmentErrorCase1() {
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/allon/RefactorableCodeInvalidStatmentError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeInvalidStatmentError1, contains a broken \"while\" loop statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testJumpDestinationErrorCase1() {
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/allon/RefactorableCodeJumpDestinationError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeJumpDestinationError1, contains a \"break\" statement but does not contain the surrounding loop.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testLocalVarErrorCase1() {
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/allon/RefactorableCodeLocalVarError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored because there are multiple variables to return. A code fragment marked as \"Refactorable\" in the method bar of the class RefactorableCodeLocalVarError1, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testReturnErrorCase1() {
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/allon/RefactorableCodeReturnError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeReturnError1, contains a return statement but there exists a path without a return statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInvalidStatmentOffInvalidStatmentCase1() {
		settings.setListOfStatementsTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/invalidstatmentoff/RefactorableCodeInvalidStatment1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();

		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInvalidStatmentOffJumpDestinationErrorCase1() {
		settings.setListOfStatementsTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/invalidstatmentoff/RefactorableCodeJumpDestinationError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeJumpDestinationError1, contains a \"break\" statement but does not contain the surrounding loop.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testInvalidStatmentOffLocalVarErrorCase1() {
		settings.setListOfStatementsTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/invalidstatmentoff/RefactorableCodeLocalVarError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored because there are multiple variables to return. A code fragment marked as \"Refactorable\" in the method bar of the class RefactorableCodeLocalVarError1, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testInvalidStatmentOffReturnErrorCase1() {
		settings.setListOfStatementsTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/invalidstatmentoff/RefactorableCodeReturnError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeReturnError1, contains a return statement but there exists a path without a return statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testJumpDestinationOffInvalidStatmentErrorCase1() {
		settings.setContinueBreakTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/jumpdestinationoff/RefactorableCodeInvalidStatmentError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();

		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeInvalidStatmentError1, contains a broken \"while\" loop statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testJumpDestinationOffJumpDestinationCase1() {
		settings.setContinueBreakTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/jumpdestinationoff/RefactorableCodeJumpDestination1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testJumpDestinationOffLocalVarErrorCase1() {
		settings.setContinueBreakTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/jumpdestinationoff/RefactorableCodeLocalVarError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored because there are multiple variables to return. A code fragment marked as \"Refactorable\" in the method bar of the class RefactorableCodeLocalVarError1, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testJumpDestinationOffReturnErrorCase1() {
		settings.setContinueBreakTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/jumpdestinationoff/RefactorableCodeReturnError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeReturnError1, contains a return statement but there exists a path without a return statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarOffInvalidStatmentErrorCase1() {
		settings.setLocalVariableTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/localvaroff/RefactorableCodeInvalidStatmentError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();

		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeInvalidStatmentError1, contains a broken \"while\" loop statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarOffJumpDestinationErrorCase1() {
		settings.setLocalVariableTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/localvaroff/RefactorableCodeJumpDestinationError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();

		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeJumpDestinationError1, contains a \"break\" statement but does not contain the surrounding loop.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testLocalVarOffLocalVarCase1() {
		settings.setLocalVariableTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/localvaroff/RefactorableCodeLocalVar1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testLocalVarOffReturnErrorCase1() {
		settings.setLocalVariableTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/localvaroff/RefactorableCodeReturnError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();

		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeReturnError1, contains a return statement but there exists a path without a return statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testReturnOffInvalidStatmentErrorCase1() {
		settings.setReturnTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/returnoff/RefactorableCodeInvalidStatmentError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();

		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeInvalidStatmentError1, contains a broken \"while\" loop statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testReturnOffJumpDestinationErrorCase1() {
		settings.setReturnTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/returnoff/RefactorableCodeJumpDestinationError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored. A code fragment marked as \"Refactorable\" in the method foo of the class RefactorableCodeJumpDestinationError1, contains a \"break\" statement but does not contain the surrounding loop.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testReturnOffLocalVarErrorCase1() {
		settings.setReturnTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/returnoff/RefactorableCodeLocalVarError1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be refactored because there are multiple variables to return. A code fragment marked as \"Refactorable\" in the method bar of the class RefactorableCodeLocalVarError1, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testReturnOffReturnCase1() {
		settings.setReturnTest(false);
		refactorabilitySettings.setCodeRefactorability(settings);
		processor.refactorabilitySettings = refactorabilitySettings;
		CodeFragmentPropertiesVerifier verifier = new CodeFragmentPropertiesVerifier("RefactorableCode", new File("src/test/resources/refactorablecode/returnoff/RefactorableCodeReturn1.java").getAbsolutePath(), processor.getMarkOfBegin(), processor.getMarkOfEnd(), processor.getMarkName(), processor.getActionVerb(), settings);
		
		VerifyResult res = verifier.isCodeFragmentsExtractable();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
}
