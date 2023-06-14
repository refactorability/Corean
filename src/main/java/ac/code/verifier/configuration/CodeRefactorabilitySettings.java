package ac.code.verifier.configuration;

/**
 * The class CodeRefactorabilitySettings stores the settings of ExtractableCode annotation.
 *
 */
public class CodeRefactorabilitySettings {

    private boolean listOfStatementsTest;
    private boolean continueBreakTest;
    private boolean returnTest;
    private boolean localVariableTest;

    private String annotationMeaning;
    private String annotationActionVerb;

	private String markOfBegin;
    private String markOfEnd;
    
    /**
     * Return annotation action verb.
     * @return
     */
    public String getAnnotationActionVerb() {
		return annotationActionVerb;
	}

    /**
     * Defines annotation action verb.
     * @param annotationActionVerb
     */
	public void setAnnotationActionVerb(String annotationActionVerb) {
		this.annotationActionVerb = annotationActionVerb;
	}

	/**
	 * Checks whether to check that the code fragment contains only a valid and complete list of statements.
	 * @return
	 */
    public boolean isListOfStatementsTest() {
        return listOfStatementsTest;
    }

    /**
     * Determines whether to check that the code fragment contains only a valid and complete list of statements.
     * @param listOfStatementsTest The new value.
     */
    public void setListOfStatementsTest(boolean listOfStatementsTest) {
        this.listOfStatementsTest = listOfStatementsTest;
    }

    /**
     * Checks whether to check that if the code fragment contains continue or break commands, then it also contains the jump destination.
     * @return
     */
    public boolean isContinueBreakTest() {
        return continueBreakTest;
    }

    /**
     * Determines whether to check that if the code fragment contains continue or break commands, then it also contains the jump destination.
     * @param continueBreakTest The new value.
     */
    public void setContinueBreakTest(boolean continueBreakTest) {
        this.continueBreakTest = continueBreakTest;
    }

    /**
     * Checks whether to check that if the code fragment contains a return command, then a return exists in all paths.
     * @return
     */
    public boolean isReturnTest() {
        return returnTest;
    }

    /**
     * Determines whether to check that if the code fragment contains a return command, then a return exists in all paths.
     * @param returnTest The new value.
     */
    public void setReturnTest(boolean returnTest) {
        this.returnTest = returnTest;
    }

    /**
     * Checks whether to check that the code fragment contains an assignment to at most one local variable that is used after the marked fragment.
     * @return
     */
    public boolean isLocalVariableTest() {
        return localVariableTest;
    }

    /**
     * Determines whether to check that the code fragment contains an assignment to at most one local variable that is used after the marked fragment. 
     * @param localVariableTest The new value.
     */
    public void setLocalVariableTest(boolean localVariableTest) {
        this.localVariableTest = localVariableTest;
    }

    /**
     * Returns the meaning of the annotation.
     * @return
     */
    public String getAnnotationMeaning() {
        return annotationMeaning;
    }

    /**
     * Determines the meaning of the annotation.
     * @param annotationMeaning The new value.
     */
    public void setAnnotationMeaning(String annotationMeaning) {
        this.annotationMeaning = annotationMeaning;
    }

    /**
     * Returns the mark for the beginning of a fragment.
     * @return
     */
    public String getMarkOfBegin() {
        return markOfBegin;
    }

    /**
     * Determines the mark for the beginning of a fragment.
     * @param markOfBegin The mark of the beginning.
     */
    public void setMarkOfBegin(String markOfBegin) {
        this.markOfBegin = markOfBegin;
    }

    /**
     * Returns the mark for the end of a fragment.
     * @return
     */
    public String getMarkOfEnd() {
        return markOfEnd;
    }

    /**
     * Determines the mark for the end of a fragment.
     * @param markOfEnd The mark of the end.
     */
    public void setMarkOfEnd(String markOfEnd) {
        this.markOfEnd = markOfEnd;
    }
}

