import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodInstanceVariableError3 {
	
	  int x;
	  
	  @MovableMethod(Description = "")
	  void bar(){
		  ++x;
	  }
	  
}
