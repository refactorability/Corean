package ac.code.verifier.processors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

class MovableCodeFragmentPropertyProcessorTest {
	
	MovableCodeFragmentPropertyProcessor processor = new MovableCodeFragmentPropertyProcessor();

	@Test
	void testGeneralCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/general/MovableCodeCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/general/MovableCodeCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralCorrectCase3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/general/MovableCodeCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumCorrect() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/general/MovableCodeEnumCorrect.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumError1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/general/MovableCodeEnumError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method isOrdered of the class MovableCodeEnumError1, Contains /*@MovableBegin*/ without /*@MovableEnd*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumError2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/general/MovableCodeEnumError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method isOrdered of the class MovableCodeEnumError2, contains a return statement but there exists a path without a return statement.";		

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError1, marked as \"MovableCode\" but doesn't contain markings of code fragments.";
				
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError2, Contains /*@MovableBegin*/ without /*@MovableEnd*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError3, Contains /*@MovableBegin*/ without /*@MovableEnd*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError4() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError4.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError4, Contains /*@MovableEnd*/ without /*@MovableBegin*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError5() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError5.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError5, Contains /*@MovableEnd*/ without /*@MovableBegin*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError6() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError6.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError6, Contains /*@MovableEnd*/ without /*@MovableBegin*/.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError7() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/marks/MovableCodeMarkError7.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class MovableCodeMarkError7, Contains two /*@MovableBegin*/ in a row.";
	
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/invalidstatments/MovableCodeInvalidStatmentError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInvalidStatmentError1, contains a broken \"while\" loop statement.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/invalidstatments/MovableCodeInvalidStatmentError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInvalidStatmentError2, contains a broken \"if\" statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/invalidstatments/MovableCodeInvalidStatmentError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInvalidStatmentError3, contains a \"case\" statement but does not contain the surrounding switch.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError4() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/invalidstatments/MovableCodeInvalidStatmentError4.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInvalidStatmentError4, contains a broken \"try\" statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void JumpDestinationError1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/jumpdestination/MovableCodeBreakJumpDestinationError.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeBreakJumpDestinationError, contains a \"break\" statement but does not contain the surrounding loop.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void JumpDestinationError2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/jumpdestination/MovableCodeContinueJumpDestinationError.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeContinueJumpDestinationError, contains a \"continue\" statement but does not contain the surrounding loop.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testReturnCorrect1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/returnpaths/MovableCodeReturnCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testReturnCorrect2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/returnpaths/MovableCodeReturnCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}

	@Test
	void testReturnError1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/returnpaths/MovableCodeReturnError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeReturnCorrect3, contains a return statement but there exists a path without a return statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testLocalVarCorrect1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarCorrect2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarCorrect3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Movable\" in the method bar of the class MovableCodeLocalVarError1, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value from the extracted method.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Movable\" in the method bar of the class MovableCodeLocalVarError2, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value from the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Movable\" in the method bar of the class MovableCodeLocalVarError3, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value from the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError4() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarError4.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Movable\" in the method bar of the class MovableCodeLocalVarError4, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value from the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError5() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/localvar/MovableCodeLocalVarError5.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted and then moved. A code fragment marked as \"Movable\" in the method bar of the class MovableCodeLocalVarError5, contains an assignment to instance variables (x and y). This hinders the ability to move this code fragment to another class.";
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/insvar/MovableCodeInstanceVariableCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/insvar/MovableCodeInstanceVariableCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableCorrectCase3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/insvar/MovableCodeInstanceVariableCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/insvar/MovableCodeInstanceVariableError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted and then moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInstanceVariableError1, contains an assignment to instance variable (x). This hinders the ability to move this code fragment to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testInstanceVariableErrorCase2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/insvar/MovableCodeInstanceVariableError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted and then moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInstanceVariableError2, contains an assignment to instance variable (x). This hinders the ability to move this code fragment to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testInstanceVariableErrorCase3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/insvar/MovableCodeInstanceVariableError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted and then moved. A code fragment marked as \"Movable\" in the method foo of the class MovableCodeInstanceVariableError3, contains an assignment to instance variable (x). This hinders the ability to move this code fragment to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testLockCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/lock/MovableCodeLockCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/lock/MovableCodeLockCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockCorrectCase3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/lock/MovableCodeLockCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/lock/MovableCodeLockError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "A code fragment marked as \"Movable\" in the method foo of the class MovableCodeLocError1, is locked on \"this\". This can cause a change in behavior if the code fragment is moved to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testCallMethodCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/callmethod/MovableCodeCallMethodCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/callmethod/MovableCodeCallMethodCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodCorrectCase3() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/callmethod/MovableCodeCallMethodCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableCode", new File("src/test/resources/movablecode/callmethod/MovableCodeCallMethodError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "A code fragment marked as \"Movable\" in the method bar of the class MovableCodeCallMethodError1, call not movable method baz.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}

}

