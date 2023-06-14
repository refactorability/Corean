import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeMarkError7 {
	
	@MovableCode(Description = "") 
	void foo(){
		/*@MovableBegin*/
		    int a;
		/*@MovableBegin*/
		    a = 5; 
		/*@MovableEnd*/
		  
		    a++;   
		    
		/*@MovableEnd*/
	}
}