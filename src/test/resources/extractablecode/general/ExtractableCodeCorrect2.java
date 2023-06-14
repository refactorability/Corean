import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeCorrect2 {
	
	@ExtractableCode(Description = "") 
	void foo(){
		int x = 5;
		/*@ExtractableBegin*/
		    x++;
		/*@ExtractableEnd*/
			    
			x--;
			
		/*@ExtractableBegin*/
			x--;
	    /*@ExtractableEnd*/
	}
}