import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeMarkError6 {
	
	@MovableCode(Description = "") 
	void foo(){
		
		 int a;
		/*@MovableEnd*/
		  
		 a = 5;   
		    
		/*@MovableBegin*/
	}
}