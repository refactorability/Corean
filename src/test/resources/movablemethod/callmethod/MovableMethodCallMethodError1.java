import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodCallMethodError1 {
	
	@MovableMethod(Description = "") 
	void bar(){
		baz();
	}
	  
	void baz(){    
		// code block	
	}
}