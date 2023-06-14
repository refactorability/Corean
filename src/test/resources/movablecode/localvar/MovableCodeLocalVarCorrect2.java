import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarCorrect2 {
	
	@MovableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@MovableBegin*/
	   x++;
	   y++;
	   /*@MovableEnd*/
	   int z = x;
	}
}
