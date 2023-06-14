import ac.collaborative.refactoring.annotations.RefactorableCode;

public class RefactorableCodeLocalVarError1 {
	
	@RefactorableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@RefactorableBegin*/
	   x++;
	   y++;
	   /*@RefactorableEnd*/
	   int z = x + y;
	}
}

