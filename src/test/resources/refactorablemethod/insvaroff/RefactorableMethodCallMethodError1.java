import ac.collaborative.refactoring.annotations.RefactorableMethod;

public class RefactorableMethodCallMethodError1 {
	
	@RefactorableMethod(Description = "")
    void foo(){
		baz();
	}
	
	void baz(){    
		// code block	
	} 

}


