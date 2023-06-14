import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeCorrect3 {
	
	@ExtractableCode(Description = "") 
	void foo(){
		int x = 5;
		/*@ExtractableBegin*/
		    x++;
		/*@ExtractableEnd*/
			    
			x--;
			
	//	/*@ExtractableBegin*/

	}
}