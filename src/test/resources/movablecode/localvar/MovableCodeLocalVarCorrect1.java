import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarCorrect1 {
	
	@MovableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@MovableBegin*/
	   x++;
	   /*@MovableEnd*/
	   int z = x;
	}
}
