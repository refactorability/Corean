import ac.collaborative.refactoring.annotations.ExtractableCode;

public enum ExtractableCodeEnumError1 {
	ORDERED (5){

		@ExtractableCode(Description = "") 
        boolean isOrdered() {		
			/*@ExtractableBegin*/
		        //some code
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}
