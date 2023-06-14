import ac.collaborative.refactoring.annotations.RefactorableCode;

public class RefactorableCodeInvalidStatmentError1 {
	
	@RefactorableCode(Description = "") 
	void foo(boolean b){
		/*@RefactorableBegin*/
		    //some code
		while(b) {
			//some code
		/*@RefactorableEnd*/
		}

	}
}
