import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableError2 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(){
		  x++;
	  }
	  
}