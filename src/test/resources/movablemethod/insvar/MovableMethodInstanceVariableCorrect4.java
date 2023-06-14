import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableCorrect4 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(){
		 // x = 5;
		/* x = 5; */
	  }
	  
}