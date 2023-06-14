import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarError2 {
	
	@ExtractableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@ExtractableBegin*/
	   x++;
	   y++;
	   /*@ExtractableEnd*/
	   x++;
	   int z = x + y;
	}
}