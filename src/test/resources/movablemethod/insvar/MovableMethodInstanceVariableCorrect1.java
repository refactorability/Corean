import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableCorrect1 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(){
	     int b = x;
	  }
	  
}
