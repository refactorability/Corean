import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeCorrect1 {
	
	@ExtractableCode(Description = "") 
	void foo(){
		/*@ExtractableBegin*/
		    //some code
		/*@ExtractableEnd*/
	}
}
