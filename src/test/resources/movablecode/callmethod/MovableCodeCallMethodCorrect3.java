import ac.collaborative.refactoring.annotations.MovableCode;
import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableCodeCallMethodCorrect3 {
	
	@MovableCode(Description = "") 
	void bar(){
		/*@MovableBegin*/
		
	   /*@MovableEnd*/
		baz();
	}
	  
	
	void baz(){    
		// code block	
	}
}