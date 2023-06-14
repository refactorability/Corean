import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarCorrect3 {
	
	@ExtractableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@ExtractableBegin*/
	   x++;
	   y++;
	   /*@ExtractableEnd*/
	   x = 5;
	   int z = x + y;
	}
}
