import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableCorrect3 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(){
		  int x;
		  x = 5;
	  }
	  
}
