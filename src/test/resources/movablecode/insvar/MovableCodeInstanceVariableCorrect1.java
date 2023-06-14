import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInstanceVariableCorrect1 {
	
	int x = 0;
	
	@MovableCode(Description = "")
	void foo(){
	   x = 5;
	   /*@MovableBegin*/
	       // code block
	   /*@MovableEnd*/
	}
}
