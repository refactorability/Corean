import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeCorrect1 {
	
	@MovableCode(Description = "") 
	void foo(){
		/*@MovableBegin*/
		    //some code
		/*@MovableEnd*/
	}
}
