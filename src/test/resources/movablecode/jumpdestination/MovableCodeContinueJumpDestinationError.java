import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeContinueJumpDestinationError {
	
	@MovableCode(Description = "") 
	void foo(int n){
		for(int i=0; i<n; i++) {
			/*@MovableBegin*/				
			if(n == 5) {
				continue;
			}
			//some code
			/*@MovableEnd*/
		}	
	}
}
