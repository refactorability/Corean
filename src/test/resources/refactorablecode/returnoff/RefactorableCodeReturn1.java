import ac.collaborative.refactoring.annotations.RefactorableCode;

public class RefactorableCodeReturn1 {
	
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