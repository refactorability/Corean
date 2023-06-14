import ac.collaborative.refactoring.annotations.RefactorableMethod;

public class RefactorableMethodOverideError1 {
	int x;
	
	@RefactorableMethod(Description = "")
	@Override
	void foo(){
		       // code block
	}

}
