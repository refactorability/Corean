import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeMarkError5 {
	
	@MovableCode(Description = "") 
	void foo(){
		/*@MovableBegin*/
		    int a;
		/*@MovableEnd*/
		  
		 a = 5;   
		    
		/*@MovableEnd*/
	}
}