import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarError5 {
	
	int x = 0;
	int y = 0;
	   
	@MovableCode(Description = "")
	void bar(){
	   /*@MovableBegin*/
	   x++;
	   y++;
	   /*@MovableEnd*/
	   int z = x + y;
	}
}