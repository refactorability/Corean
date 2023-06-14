import ac.collaborative.refactoring.annotations.MovableCode;

public enum MovableCodeEnumCorrect {
	ORDERED (5){

		@ExtractableCode(Description = "") 
        boolean isOrdered() {		
			/*@MovableBegin*/
		        //some code
		    /*@MovableEnd*/
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}