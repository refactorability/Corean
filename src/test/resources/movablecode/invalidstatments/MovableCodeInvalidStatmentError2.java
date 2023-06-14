import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeInvalidStatmentError2 {
	
	@MovableCode(Description = "") 
	void foo(boolean b){
		String s;
		if(b){
			s = "aa";
		}/*@MovableBegin*/
		else{
			s = "bb";
		}
		/*@MovableEnd*/
	}
}