import ac.collaborative.refactoring.annotations.MovableCode;
import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableCodeCallMethodCorrect2 {
	
	@MovableCode(Description = "") 
	void bar(){
		baz();
		/*@MovableBegin*/
		
	   /*@MovableEnd*/
	}
	  
	
	void baz(){    
		// code block	
	}
}