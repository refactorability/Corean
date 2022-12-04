package ac.code.verifier.configuration;

public class MethodRefactorabilitySettings {
    private boolean overrideTest;
    private boolean lockTest;
    private boolean instanceVariableTest;
    private boolean callNotMoveableMethodTest;

    public boolean isOverrideTest() {
        return overrideTest;
    }

    public void setOverrideTest(boolean overrideTest) {
        this.overrideTest = overrideTest;
    }

    public boolean isLockTest() {
        return lockTest;
    }

    public void setLockTest(boolean lockTest) {
        this.lockTest = lockTest;
    }

    public boolean isInstanceVariableTest() {
        return instanceVariableTest;
    }

    public void setInstanceVariableTest(boolean instanceVariableTest) {
        this.instanceVariableTest = instanceVariableTest;
    }

    public boolean isCallNotMoveableMethodTest() {
        return callNotMoveableMethodTest;
    }

    public void setCallNotMoveableMethodTest(boolean callNotMoveableMethodTest) {
        this.callNotMoveableMethodTest = callNotMoveableMethodTest;
    }
}
