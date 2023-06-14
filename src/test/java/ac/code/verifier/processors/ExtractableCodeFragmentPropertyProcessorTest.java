package ac.code.verifier.processors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

class ExtractableCodeFragmentPropertyProcessorTest {
	
	ExtractableCodeFragmentPropertyProcessor processor = new ExtractableCodeFragmentPropertyProcessor();

	@Test
	void testGeneralCorrectCase1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/general/ExtractableCodeCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralCorrectCase2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/general/ExtractableCodeCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralCorrectCase3() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/general/ExtractableCodeCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumCorrect() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/general/ExtractableCodeEnumCorrect.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumError1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/general/ExtractableCodeEnumError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method isOrdered of the class ExtractableCodeEnumError1, Contains /*@ExtractableBegin*/ without /*@ExtractableEnd*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumError2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/general/ExtractableCodeEnumError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method isOrdered of the class ExtractableCodeEnumError2, contains a return statement but there exists a path without a return statement.";		
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError1, marked as \"ExtractableCode\" but doesn't contain markings of code fragments.";
				
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError2, Contains /*@ExtractableBegin*/ without /*@ExtractableEnd*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError3() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError3, Contains /*@ExtractableBegin*/ without /*@ExtractableEnd*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError4() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError4.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError4, Contains /*@ExtractableEnd*/ without /*@ExtractableBegin*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError5() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError5.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError5, Contains /*@ExtractableEnd*/ without /*@ExtractableBegin*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError6() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError6.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError6, Contains /*@ExtractableEnd*/ without /*@ExtractableBegin*/.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testMarkError7() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/marks/ExtractableCodeMarkError7.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The Method foo of the class ExtractableCodeMarkError7, Contains two /*@ExtractableBegin*/ in a row.";
	
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/invalidstatments/ExtractableCodeInvalidStatmentError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeInvalidStatmentError1, contains a broken \"while\" loop statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/invalidstatments/ExtractableCodeInvalidStatmentError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeInvalidStatmentError2, contains a broken \"if\" statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError3() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/invalidstatments/ExtractableCodeInvalidStatmentError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeInvalidStatmentError3, contains a \"case\" statement but does not contain the surrounding switch.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void InvalidStatmentError4() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/invalidstatments/ExtractableCodeInvalidStatmentError4.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeInvalidStatmentError4, contains a broken \"try\" statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void JumpDestinationError1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/jumpdestination/ExtractableCodeBreakJumpDestinationError.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeBreakJumpDestinationError, contains a \"break\" statement but does not contain the surrounding loop.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void JumpDestinationError2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/jumpdestination/ExtractableCodeContinueJumpDestinationError.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeContinueJumpDestinationError, contains a \"continue\" statement but does not contain the surrounding loop.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testReturnCorrect1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/returnpaths/ExtractableCodeReturnCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testReturnCorrect2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/returnpaths/ExtractableCodeReturnCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}

	@Test
	void testReturnError1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/returnpaths/ExtractableCodeReturnError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted. A code fragment marked as \"Extractable\" in the method foo of the class ExtractableCodeReturnCorrect3, contains a return statement but there exists a path without a return statement.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}	
	
	@Test
	void testLocalVarCorrect1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarCorrect2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarCorrect3() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarCorrect4() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarCorrect4.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError1() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted because there are multiple variables to return. A code fragment marked as \"Extractable\" in the method bar of the class ExtractableCodeLocalVarError1, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError2() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted because there are multiple variables to return. A code fragment marked as \"Extractable\" in the method bar of the class ExtractableCodeLocalVarError2, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError3() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted because there are multiple variables to return. A code fragment marked as \"Extractable\" in the method bar of the class ExtractableCodeLocalVarError3, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLocalVarError4() {

		VerifyResult res = processor.verifyFile("ExtractableCode", new File("src/test/resources/extractablecode/localvar/ExtractableCodeLocalVarError4.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "Code fragment cannot be extracted because there are multiple variables to return. A code fragment marked as \"Extractable\" in the method bar of the class ExtractableCodeLocalVarError4, contains 2 variables (x and y) which are modified in the extractable fragment and are used after this fragment. These variables cannot all be the return value of the extracted method.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}

}

