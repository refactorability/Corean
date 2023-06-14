import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeReturnCorrect1 {
	
	@ExtractableCode(Description = "")
	boolean foo(boolean b){
		   /*@ExtractableBegin*/
		   if(b){
			   return false;
		   }else{
		     return true;
		   }
		   /*@ExtractableEnd*/
		}

}
