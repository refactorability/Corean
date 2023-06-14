import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLockCorrect1 {
		
	@MovableCode(Description = "")
	void foo(Object o){
	   /*@MovableBegin*/
	    synchronized (o) {
		     // code block
		}
	   /*@MovableEnd*/
	}
}