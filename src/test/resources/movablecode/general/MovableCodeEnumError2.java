import ac.collaborative.refactoring.annotations.MovableCode;

public enum MovableCodeEnumError2 {
	ORDERED (5){

		@MovableCode(Description = "") 
        boolean isOrdered() {		
			/*@MovableBegin*/
			if(b){
				// code block
			}else{
				return true;
			} 
		    /*@MovableEnd*/
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}
