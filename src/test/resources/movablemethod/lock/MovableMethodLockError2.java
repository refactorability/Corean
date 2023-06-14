import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodLockError2 {
	
	@MovableMethod(Description = "")
	void foo(){
		 synchronized (this) {
		   // code block
		 }
	}

}
