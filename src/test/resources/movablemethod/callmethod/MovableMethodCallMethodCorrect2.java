import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodCallMethodCorrect2 {
	
	@MovableMethod(Description = "") 
	void bar(){
	//	baz();
	}
	  
	void baz(){    
		// code block	
	}
}