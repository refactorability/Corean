import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLockCorrect2 {
		
	@MovableCode(Description = "")
	void foo(){

	    synchronized (this) {
		     // code block
		}
	    
	   /*@MovableBegin*/
	    // code block
	   /*@MovableEnd*/
	}
}