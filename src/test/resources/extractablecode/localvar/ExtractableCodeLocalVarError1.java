import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarError1 {
	
	@ExtractableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@ExtractableBegin*/
	   x++;
	   y++;
	   /*@ExtractableEnd*/
	   int z = x + y;
	}
}
