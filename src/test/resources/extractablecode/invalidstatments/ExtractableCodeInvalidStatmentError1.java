import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeInvalidStatmentError1 {
	
	@ExtractableCode(Description = "") 
	void foo(boolean b){
		/*@ExtractableBegin*/
		    //some code
		while(b) {
			//some code
		/*@ExtractableEnd*/
		}

	}
}