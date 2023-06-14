import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeMarkError5 {
	
	@ExtractableCode(Description = "") 
	void foo(){
		/*@ExtractableBegin*/
		    int a;
		/*@ExtractableEnd*/
		  
		 a = 5;   
		    
		/*@ExtractableEnd*/
	}
}