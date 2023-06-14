import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLockCorrect3 {
		
	@MovableCode(Description = "")
	void foo(){
	    
	   /*@MovableBegin*/
	    // code block
	   /*@MovableEnd*/
		
	    synchronized (this) {
		     // code block
		}
	}
}