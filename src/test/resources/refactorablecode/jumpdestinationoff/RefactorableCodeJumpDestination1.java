import ac.collaborative.refactoring.annotations.RefactorableCode;

public class RefactorableCodeJumpDestination1 {
	
	@RefactorableCode(Description = "") 
	void foo(int n){
		while(n>0) {
			/*@RefactorableBegin*/
			//some code	
			if(n == 5) {
				break;
			}
			/*@RefactorableEnd*/
		}	
	}
}
