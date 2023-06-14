import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInstanceVariableCorrect2 {
	
	int x = 0;
	
	@MovableCode(Description = "")
	void foo(){
	   
	   /*@MovableBegin*/
	       // code block
	   /*@MovableEnd*/
	   x = 5;
	}
}