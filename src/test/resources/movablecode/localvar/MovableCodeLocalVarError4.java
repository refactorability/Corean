import ac.collaborative.refactoring.annotations.MovableCode;

public class MovableCodeLocalVarError4 {
	
	@MovableCode(Description = "")
	void bar(int x , int y){
	   /*@MovableBegin*/
	   x++;
	   y++;
	   /*@MovableEnd*/
	   int z = x + y;
	}
}