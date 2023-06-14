import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarCorrect4 {
	
	int x = 0;
	int y = 0;
	   
	@ExtractableCode(Description = "")
	void bar(){
	   /*@ExtractableBegin*/
	   x++;
	   y++;
	   /*@ExtractableEnd*/
	   int z = x + y;
	}
}