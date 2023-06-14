import ac.collaborative.refactoring.annotations.ExtractableCode;

public enum ExtractableCodeEnumCorrect {
	ORDERED (5){

		@ExtractableCode(Description = "") 
        boolean isOrdered() {		
			/*@ExtractableBegin*/
		        //some code
		    /*@ExtractableEnd*/
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}