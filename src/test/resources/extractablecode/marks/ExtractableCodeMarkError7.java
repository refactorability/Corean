import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeMarkError7 {
	
	@ExtractableCode(Description = "") 
	void foo(){
		/*@ExtractableBegin*/
		    int a;
		/*@ExtractableBegin*/
		    a = 5; 
		/*@ExtractableEnd*/
		  
		    a++;   
		    
		/*@ExtractableEnd*/
	}
}