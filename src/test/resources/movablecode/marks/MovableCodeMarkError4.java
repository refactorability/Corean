import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeMarkError4 {
	
	@MovableCode(Description = "") 
	void foo(){
		    int a;
		    /*@MovableEnd*/
	}
}