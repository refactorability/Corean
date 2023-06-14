import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodCallMethodCorrect1 {
	
	@MovableMethod(Description = "") 
	void bar(){
		baz();
	}
	  
	@MovableMethod(Description = "") 
	void baz(){    
		// code block	
	}
}
