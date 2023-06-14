import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeInvalidStatmentError3 {
	
	@ExtractableCode(Description = "") 
	void foo(boolean b){
		switch(b) {
		/*@ExtractableBegin*/
        case 1:
		     // code block
        /*@ExtractableEnd*/
        case 2:
		     // code block
        default:
		     // code block
		}

	}
}