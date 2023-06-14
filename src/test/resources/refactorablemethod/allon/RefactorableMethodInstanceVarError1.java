import ac.collaborative.refactoring.annotations.RefactorableMethod;

public class RefactorableMethodInstanceVarError1 {
	int x;
	
	@RefactorableMethod(Description = "")
    void foo(){
		x = 5;
	}

}