import ac.collaborative.refactoring.annotations.ExtractableCode;

public class ExtractableCodeLocalVarError4 {
	
	@ExtractableCode(Description = "")
	void bar(int x , int y){
	   /*@ExtractableBegin*/
	   x++;
	   y++;
	   /*@ExtractableEnd*/
	   int z = x + y;
	}
}