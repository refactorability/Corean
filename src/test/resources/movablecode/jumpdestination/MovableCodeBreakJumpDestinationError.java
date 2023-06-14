import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeBreakJumpDestinationError {
	
	@MovableCode(Description = "") 
	void foo(int n){
		while(n>0) {
			/*@MovableBegin*/
			//some code	
			if(n == 5) {
				break;
			}
			/*@MovableEnd*/
		}	
	}
}
