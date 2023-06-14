import ac.collaborative.refactoring.annotations.MovableCode;
import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableCodeCallMethodCorrect1 {
	
	@MovableCode(Description = "") 
	void bar(){
		/*@MovableBegin*/
		baz();
	   /*@MovableEnd*/
	}
	  
	@MovableMethod(Description = "") 
	void baz(){    
		// code block	
	}
}
