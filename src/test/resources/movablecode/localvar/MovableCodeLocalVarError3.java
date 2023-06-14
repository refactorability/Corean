import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarError3 {
	
	@MovableCode(Description = "")
	void bar(){
	   /*@MovableBegin*/
	   int x = 0;
	   int y = 0;
	   /*@MovableEnd*/
	   int z = x + y;
	}
}