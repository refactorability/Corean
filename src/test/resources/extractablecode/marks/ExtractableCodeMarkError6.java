import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeMarkError6 {
	
	@ExtractableCode(Description = "") 
	void foo(){
		
		 int a;
		/*@ExtractableEnd*/
		  
		 a = 5;   
		    
		/*@ExtractableBegin*/
	}
}