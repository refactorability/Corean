import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeReturnCorrect2 {
	
	@ExtractableCode(Description = "")
	boolean foo(boolean b){
		   /*@ExtractableBegin*/
		   if(b){
			   
		   }else{
		     return true;
		   }
		   return false;
		   /*@ExtractableEnd*/
		}
}