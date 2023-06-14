import ac.collaborative.refactoring.annotations.RefactorableCode;

public class RefactorableCodeInvalidStatment1 {
	
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
