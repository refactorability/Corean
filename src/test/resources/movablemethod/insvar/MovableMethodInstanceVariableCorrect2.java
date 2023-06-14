import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableCorrect2 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(int x){
		     x++;
	  }
	  
}
