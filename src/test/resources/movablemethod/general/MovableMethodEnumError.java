import ac.collaborative.refactoring.annotations.MovableMethod;

public class MovableMethodError {
	
	enum Status {
        ORDERED (5){

        	@MovableMethod(Description = "") 
        	synchronized public boolean isOrdered() {
                return true;
            }
        },
        READY (2){
        },
        DELIVERED (0){
        };
	}

}
	
