import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarCorrect3 {
	
	@MovableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@MovableBegin*/
	   x++;
	   y++;
	   /*@MovableEnd*/
	   x = 5;
	   int z = x + y;
	}
}
