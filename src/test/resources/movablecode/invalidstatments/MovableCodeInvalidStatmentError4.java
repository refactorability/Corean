import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInvalidStatmentError4 {
	
	@MovableCode(Description = "") 
	void foo(boolean b){
		try {
			  //  Block of code to try
		}/*@MovableBegin*/
		catch(Exception e) {
		  //  Block of code to handle errors
		}
		/*@MovableEnd*/
	}
}