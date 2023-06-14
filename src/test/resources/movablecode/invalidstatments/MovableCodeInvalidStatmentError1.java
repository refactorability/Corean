import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInvalidStatmentError1 {
	
	@MovableCode(Description = "") 
	void foo(boolean b){
		/*@MovableBegin*/
		    //some code
		while(b) {
			//some code
		/*@MovableEnd*/
		}

	}
}