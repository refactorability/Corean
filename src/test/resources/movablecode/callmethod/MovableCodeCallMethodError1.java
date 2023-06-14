import ac.collaborative.refactoring.annotations.MovableCode;
import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableCodeCallMethodError1 {
	
	@MovableCode(Description = "") 
	void bar(){
		/*@MovableBegin*/
		baz();
	   /*@MovableEnd*/
	}
	  
	void baz(){    
		// code block	
	}
}
