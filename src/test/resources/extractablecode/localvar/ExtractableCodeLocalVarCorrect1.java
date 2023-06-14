import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarCorrect1 {
	
	@ExtractableCode(Description = "")
	void bar(){
	   int x = 0;
	   int y = 0;
	   /*@ExtractableBegin*/
	   x++;
	   /*@ExtractableEnd*/
	   int z = x;
	}
}
