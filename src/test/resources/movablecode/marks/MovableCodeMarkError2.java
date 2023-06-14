import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeMarkError2 {
	
	@MovableCode(Description = "") 
	void foo(){
		/*@MovableBegin*/
		    int a;
	}
}
