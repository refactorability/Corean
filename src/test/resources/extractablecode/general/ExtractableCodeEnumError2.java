import ac.collaborative.refactoring.annotations.ExtractableCode;

public enum ExtractableCodeEnumError2 {
	ORDERED (5){

		@ExtractableCode(Description = "") 
        boolean isOrdered() {		
			/*@ExtractableBegin*/
			if(b){
				// code block
			}else{
				return true;
			} 
		    /*@ExtractableEnd*/
            return true;
        }
    },
    READY (2){
    },
    DELIVERED (0){
    };
}
