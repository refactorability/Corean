import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInstanceVariableError3 {
	
	int x = 0;
	
	@MovableCode(Description = "")
	void foo(){
	   /*@MovableBegin*/
	       ++x;
	   /*@MovableEnd*/
	}
}