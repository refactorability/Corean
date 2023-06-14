package ac.code.verifier.processors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ac.code.verifier.configuration.MethodRefactorabilitySettings;
import ac.code.verifier.engine.MethodPropertiesVerifier;
import ac.code.verifier.helper.Result;
import ac.code.verifier.helper.VerifyResult;

class MethodRefactorabilityPropertyProcessorTest {
	
	MethodRefactorabilitySettings settings;
	
	@BeforeEach
    public void init() {
    	settings = new MethodRefactorabilitySettings();
		settings.setCallNotMoveableMethodTest(true);
		settings.setInstanceVariableTest(true);
		settings.setLockTest(true);
		settings.setOverrideTest(true);
    }
	
	@Test
	void testGeneralCorrectCase1() {		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/allon/RefactorableMethodCorrect1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockErrorCase1() {
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/allon/RefactorableMethodLockError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodLockError1, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodErrorCase1() {
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/allon/RefactorableMethodCallMethodError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodCallMethodError1, call not movable method baz.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVarErrorCase1() {
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/allon/RefactorableMethodInstanceVarError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodInstanceVarError1, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testOverideErrorCase1() {
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/allon/RefactorableMethodOverideError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodOverideError1, is an override method of its super class. This can change the behavior of an inherited class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockOffLockCase1() {
		settings.setLockTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/lockoff/RefactorableMethodLock1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testLockOffCallMethodErrorCase1() {
		settings.setLockTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/lockoff/RefactorableMethodCallMethodError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodCallMethodError1, call not movable method baz.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockOffInstanceVarErrorCase1() {
		settings.setLockTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/lockoff/RefactorableMethodInstanceVarError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodInstanceVarError1, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testLockOffOverideErrorCase1() {
		settings.setLockTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/lockoff/RefactorableMethodOverideError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodOverideError1, is an override method of its super class. This can change the behavior of an inherited class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodOffLockErrorCase1() {
		settings.setCallNotMoveableMethodTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/callmethodoff/RefactorableMethodLockError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodLock1, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";
		
		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testCallMethodOffCallMethodCase1() {
		settings.setCallNotMoveableMethodTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/callmethodoff/RefactorableMethodCallMethod1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodOffInstanceVarErrorCase1() {
		settings.setCallNotMoveableMethodTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/callmethodoff/RefactorableMethodInstanceVarError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodInstanceVarError1, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testCallMethodOffOverideErrorCase1() {
		settings.setCallNotMoveableMethodTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/callmethodoff/RefactorableMethodOverideError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodOverideError1, is an override method of its super class. This can change the behavior of an inherited class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVarOffLockErrorCase1() {
		settings.setInstanceVariableTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/insvaroff/RefactorableMethodLockError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodLockError1, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testInstanceVarOffCallMethodErrorCase1() {
		settings.setInstanceVariableTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/insvaroff/RefactorableMethodCallMethodError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodCallMethodError1, call not movable method baz.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVarOffInstanceVarCase1() {
		settings.setInstanceVariableTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/insvaroff/RefactorableMethodInstanceVar1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testInstanceVarOffOverideErrorCase1() {
		settings.setInstanceVariableTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/insvaroff/RefactorableMethodOverideError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodOverideError1, is an override method of its super class. This can change the behavior of an inherited class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testOverrideOffLockErrorCase1() {
		settings.setOverrideTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/overrideoff/RefactorableMethodLockError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodLockError1, is locked on \"this\". This can cause a change in behavior if the method is moved to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());		
	}
	
	@Test
	void testOverrideOffCallMethodErrorCase1() {
		settings.setOverrideTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/overrideoff/RefactorableMethodCallMethodError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodCallMethodError1, call not movable method baz.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testOverrideOffInstanceVarErrorCase1() {
		settings.setOverrideTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/overrideoff/RefactorableMethodInstanceVarError1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.ERROR;
		String expectedMessage = "The method foo of the class RefactorableMethodInstanceVarError1, contains assignment to instanceinstance variable variable. This hinders the ability to move this method to another class.";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
	@Test
	void testOverrideOffOverideCase1() {
		settings.setOverrideTest(false);
		
		MethodPropertiesVerifier verifier = new MethodPropertiesVerifier("RefactorableMethod", new File("src/test/resources/refactorablemethod/overrideoff/RefactorableMethodOverride1.java").getAbsolutePath(), settings); 

		VerifyResult res = verifier.verify();
		
		Result expectedResult = Result.OK;
		String expectedMessage = "";

		assertEquals(expectedResult, res.getResult());
		assertEquals(expectedMessage, res.getMessage());			
	}
	
}
