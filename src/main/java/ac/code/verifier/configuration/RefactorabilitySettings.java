package ac.code.verifier.configuration;

/**
 * The class RefactorabilitySettings stores the settings of the all configurable annotations.
 *
 */
public class RefactorabilitySettings {

	/**
	 * Constructor
	 */
    public RefactorabilitySettings(){
        setMethodRefactorability(new MethodRefactorabilitySettings());
        setCodeRefactorability(new CodeRefactorabilitySettings());
    }

    private MethodRefactorabilitySettings methodRefactorability;
    private CodeRefactorabilitySettings codeRefactorability;


    /**
     * Returns setting for ExtractableMethod annotation.
     * @return
     */
    public MethodRefactorabilitySettings getMethodRefactorability() {
        return methodRefactorability;
    }

    /**
     * Defines setting for ExtractableMethod annotation.
     * @param mr The settings.
     */
    public void setMethodRefactorability(MethodRefactorabilitySettings mr) {
        this.methodRefactorability = mr;
    }

    /**
     *  Returns setting for ExtractableCode annotation.
     * @return
     */
    public CodeRefactorabilitySettings getCodeRefactorability() {
        return codeRefactorability;
    }

    /**
     * Defines setting for ExtractableCode annotation.
     * @param cr The settings.
     */
    public void setCodeRefactorability(CodeRefactorabilitySettings cr) {
        this.codeRefactorability = cr;
    }
}

