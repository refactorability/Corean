import ac.collaborative.refactoring.annotations.RefactorableCode;

public class RefactorableCodeReturnError1 {
	
	@RefactorableCode(Description = "")
	boolean foo(boolean b){
		   /*@RefactorableBegin*/
		   if(b){
			   
		   }else{
		     return true;
		   }
		   /*@RefactorableEnd*/
		   return false;
		}
}