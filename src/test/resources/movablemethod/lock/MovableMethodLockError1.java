import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodLockError1 {
	
	@MovableMethod(Description = "")
    synchronized void foo(){
		
	}

}
