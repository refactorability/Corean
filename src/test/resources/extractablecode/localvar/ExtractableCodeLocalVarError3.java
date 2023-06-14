import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarError3 {
	
	@ExtractableCode(Description = "")
	void bar(){
	   /*@ExtractableBegin*/
	   int x = 0;
	   int y = 0;
	   /*@ExtractableEnd*/
	   int z = x + y;
	}
}