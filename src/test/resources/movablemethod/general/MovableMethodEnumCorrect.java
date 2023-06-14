import ac.collaborative.refactoring.annotations.MovableMethod;

public enum MovableMethodCorrect2 {
	ORDERED (5){

    	@MovableMethod(Description = "") 
        public boolean isOrdered() {
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}
	
	