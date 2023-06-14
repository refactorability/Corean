import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeInvalidStatmentError2 {
	
	@ExtractableCode(Description = "") 
	void foo(boolean b){
		String s;
		if(b){
			s = "aa";
		}/*@ExtractableBegin*/
		else{
			s = "bb";
		}
		/*@ExtractableEnd*/
	}
}