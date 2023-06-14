import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeReturnCorrect2 {
	
	@MovableCode(Description = "")
	boolean foo(boolean b){
		   /*@MovableBegin*/
		   if(b){
			   
		   }else{
		     return true;
		   }
		   return false;
		   /*@MovableEnd*/
		}
}