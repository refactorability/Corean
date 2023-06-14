import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInstanceVariableCorrect3 {
	
	int x = 0;
	
	@MovableCode(Description = "")
	void foo(){
		int x;
	   /*@MovableBegin*/
	       x = 5;
	   /*@MovableEnd*/
	}
}