import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodLockCorrect1 {

	@MovableMethod(Description = "") 
	 void foo(Object o){
		     synchronized (o) {
		       // code block
	     }
	 }
	
}
