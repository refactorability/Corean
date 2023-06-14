import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInvalidStatmentError3 {
	
	@MovableCode(Description = "") 
	void foo(boolean b){
		switch(b) {
		/*@MovableBegin*/
        case 1:
		     // code block
        /*@MovableEnd*/
        case 2:
		     // code block
        default:
		     // code block
		}

	}
}