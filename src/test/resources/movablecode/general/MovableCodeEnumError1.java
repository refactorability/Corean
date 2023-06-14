import ac.collaborative.refactoring.annotations.MovableCode;

public enum MovableCodeEnumError1 {
	ORDERED (5){

		@MovableCode(Description = "") 
        boolean isOrdered() {		
			/*@MovableBegin*/
		        //some code
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}
