import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocError1 {
		
	@MovableCode(Description = "")
	void foo(){
	   /*@MovableBegin*/
	    synchronized (this) {
		     // code block
		}
	   /*@MovableEnd*/
	}
}

