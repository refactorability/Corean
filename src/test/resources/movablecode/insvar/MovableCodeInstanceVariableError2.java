import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInstanceVariableError2 {
	
	int x = 0;
	
	@MovableCode(Description = "")
	void foo(){
	   /*@MovableBegin*/
	       x ++;
	   /*@MovableEnd*/
	}
}