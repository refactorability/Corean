import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeCorrect3 {
	
	@MovableCode(Description = "") 
	void foo(){
		int x = 5;
		/*@MovableBegin*/
		    x++;
		/*@MovableEnd*/
			    
			x--;
			
	//	/*@MovableBegin*/

	}
}