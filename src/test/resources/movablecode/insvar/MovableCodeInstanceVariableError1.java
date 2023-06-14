import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInstanceVariableError1 {
	
	int x = 0;
	
	@MovableCode(Description = "")
	void foo(){
	   /*@MovableBegin*/
	       x = 5;
	   /*@MovableEnd*/
	}
}