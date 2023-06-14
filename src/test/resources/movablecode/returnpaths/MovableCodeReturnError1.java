import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeReturnCorrect3 {
	
	@MovableCode(Description = "")
	boolean foo(boolean b){
		   /*@MovableBegin*/
		   if(b){
			   
		   }else{
		     return true;
		   }
		   /*@MovableEnd*/
		   return false;
		}
}