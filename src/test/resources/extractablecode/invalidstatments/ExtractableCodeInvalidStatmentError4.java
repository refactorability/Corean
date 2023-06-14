import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeInvalidStatmentError4 {
	
	@ExtractableCode(Description = "") 
	void foo(boolean b){
		try {
			  //  Block of code to try
		}/*@ExtractableBegin*/
		catch(Exception e) {
		  //  Block of code to handle errors
		}
		/*@ExtractableEnd*/
	}
}