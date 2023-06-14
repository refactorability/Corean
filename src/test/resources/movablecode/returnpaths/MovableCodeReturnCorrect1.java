import ac.collaborative.refactoring.annotations.MovableCode;

public class Movable {
	
	@MovableCode(Description = "")
	boolean foo(boolean b){
		   /*@MovableBegin*/
		   if(b){
			   return false;
		   }else{
		     return true;
		   }
		   /*@MovableEnd*/
		}

}
