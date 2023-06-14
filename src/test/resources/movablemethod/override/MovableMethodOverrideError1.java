import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodOverrideError1 {
	
	@MovableMethod(Description = "") 
	@Override
	void foo(){
		       // code block
	}

}
