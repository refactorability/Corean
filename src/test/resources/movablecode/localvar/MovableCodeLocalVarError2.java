import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarError2 {
	
	@MovableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@MovableBegin*/
	   x++;
	   y++;
	   /*@MovableEnd*/
	   x++;
	   int z = x + y;
	}
}