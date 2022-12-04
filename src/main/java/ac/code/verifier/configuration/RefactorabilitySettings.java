package ac.code.verifier.configuration;


public class RefactorabilitySettings {

    public RefactorabilitySettings(){
        setMethodRefactorability(new MethodRefactorabilitySettings());
        setCodeRefactorability(new CodeRefactorabilitySettings());
    }

    private MethodRefactorabilitySettings methodRefactorability;
    private CodeRefactorabilitySettings codeRefactorability;


    public MethodRefactorabilitySettings getMethodRefactorability() {
        return methodRefactorability;
    }

    public void setMethodRefactorability(MethodRefactorabilitySettings mr) {
        this.methodRefactorability = mr;
    }

    public CodeRefactorabilitySettings getCodeRefactorability() {
        return codeRefactorability;
    }

    public void setCodeRefactorability(CodeRefactorabilitySettings cr) {
        this.codeRefactorability = cr;
    }
}

