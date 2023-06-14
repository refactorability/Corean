import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeReturnCorrect3 {
	
	@ExtractableCode(Description = "")
	boolean foo(boolean b){
		   /*@ExtractableBegin*/
		   if(b){
			   
		   }else{
		     return true;
		   }
		   /*@ExtractableEnd*/
		   return false;
		}
}