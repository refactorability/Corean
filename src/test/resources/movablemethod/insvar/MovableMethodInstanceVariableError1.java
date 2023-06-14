import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableError1 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(){
		  x = 5;
	  }
	  
}