package ac.code.verifier.processors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

class MovableMethodPropertyProcessorTest {
	
	MovableMethodPropertyProcessor processor = new MovableMethodPropertyProcessor();

	@Test
	void testGeneralCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/general/MovableMethodCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumCorrect() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/general/MovableMethodEnumCorrect.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testGeneralEnumError() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/general/MovableMethodEnumError.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method isOrdered of the class Status, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/lock/MovableMethodLockCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/lock/MovableMethodLockCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/lock/MovableMethodLockError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class MovableMethodLockError1, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testLockErrorCase2() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/lock/MovableMethodLockError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class MovableMethodLockError2, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testInstanceVariableCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableCorrectCase3() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableCorrect3.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableCorrectCase4() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableCorrect4.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVariableErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method bar of the class MovableMethodInstanceVariableError1, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testInstanceVariableErrorCase2() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableError2.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method bar of the class MovableMethodInstanceVariableError2, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testInstanceVariableErrorCase3() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/insvar/MovableMethodInstanceVariableError3.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method bar of the class MovableMethodInstanceVariableError3, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testCallMethodCorrectCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/callmethod/MovableMethodCallMethodCorrect1.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodCorrectCase2() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/callmethod/MovableMethodCallMethodCorrect2.java").getAbsolutePath());
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/callmethod/MovableMethodCallMethodError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method bar of the class MovableMethodCallMethodError1, call not movable method baz.";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testOverrideErrorCase1() {

		VerifyResult res = processor.verifyFile("MovableMethod", new File("src/test/resources/movablemethod/override/MovableMethodOverrideError1.java").getAbsolutePath());
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class MovableMethodOverrideError1, is an override method of its super class. This can change the behavior of an inherited class.";
		 
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}

}
