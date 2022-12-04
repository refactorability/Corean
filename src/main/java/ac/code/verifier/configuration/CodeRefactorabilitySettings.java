package ac.code.verifier.configuration;


public class CodeRefactorabilitySettings {

    private boolean listOfStatementsTest;
    private boolean continueBreakTest;
    private boolean returnTest;
    private boolean localVariableTest;

    private String annotationMeaning;
    private String annotationActionVerb;

	private String markOfBegin;
    private String markOfEnd;
    
    public String getAnnotationActionVerb() {
		return annotationActionVerb;
	}

	public void setAnnotationActionVerb(String annotationActionVerb) {
		this.annotationActionVerb = annotationActionVerb;
	}

    public boolean isListOfStatementsTest() {
        return listOfStatementsTest;
    }

    public void setListOfStatementsTest(boolean listOfStatementsTest) {
        this.listOfStatementsTest = listOfStatementsTest;
    }

    public boolean isContinueBreakTest() {
        return continueBreakTest;
    }

    public void setContinueBreakTest(boolean continueBreakTest) {
        this.continueBreakTest = continueBreakTest;
    }

    public boolean isReturnTest() {
        return returnTest;
    }

    public void setReturnTest(boolean returnTest) {
        this.returnTest = returnTest;
    }

    public boolean isLocalVariableTest() {
        return localVariableTest;
    }

    public void setLocalVariableTest(boolean localVariableTest) {
        this.localVariableTest = localVariableTest;
    }

    public String getAnnotationMeaning() {
        return annotationMeaning;
    }

    public void setAnnotationMeaning(String annotationMeaning) {
        this.annotationMeaning = annotationMeaning;
    }

    public String getMarkOfBegin() {
        return markOfBegin;
    }

    public void setMarkOfBegin(String markOfBegin) {
        this.markOfBegin = markOfBegin;
    }

    public String getMarkOfEnd() {
        return markOfEnd;
    }

    public void setMarkOfEnd(String markOfEnd) {
        this.markOfEnd = markOfEnd;
    }
}

