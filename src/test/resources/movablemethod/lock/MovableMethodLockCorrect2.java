import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodLockCorrect2 {
	
	@MovableMethod(Description = "")
	void foo(){
		// synchronized (this) {
		   // code block
		// }
	}

}