import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeContinueJumpDestinationError {
	
	@ExtractableCode(Description = "") 
	void foo(int n){
		for(int i=0; i<n; i++) {
			/*@ExtractableBegin*/				
			if(n == 5) {
				continue;
			}
			//some code
			/*@ExtractableEnd*/
		}	
	}
}
