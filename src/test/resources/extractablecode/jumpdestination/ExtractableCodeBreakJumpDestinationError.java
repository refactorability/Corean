import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeBreakJumpDestinationError {
	
	@ExtractableCode(Description = "") 
	void foo(int n){
		while(n>0) {
			/*@ExtractableBegin*/
			//some code	
			if(n == 5) {
				break;
			}
			/*@ExtractableEnd*/
		}	
	}
}
