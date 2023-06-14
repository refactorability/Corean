package ac.code.verifier.configuration;

/**
 * The class MethodRefactorabilitySettings stores the settings of ExtractableMethod annotation.
 *
 */
public class MethodRefactorabilitySettings {
    private boolean overrideTest;
    private boolean lockTest;
    private boolean instanceVariableTest;
    private boolean callNotMoveableMethodTest;

    /**
     * Checks whether to check that the method does not override a method of a superclass.
     * @return
     */
    public boolean isOverrideTest() {
        return overrideTest;
    }

    /**
     * Determines whether to check that the method does not override a method of a superclass.
     * @param overrideTest The new value.
     */
    public void setOverrideTest(boolean overrideTest) {
        this.overrideTest = overrideTest;
    }

    /**
     *  Checks whether to check that the method is not locked on the current object.
     * @return
     */
    public boolean isLockTest() {
        return lockTest;
    }

    /**
     * Determines whether to check that the method is not locked on the current object. 
     * @param lockTest The new value.
     */
    public void setLockTest(boolean lockTest) {
        this.lockTest = lockTest;
    }

    /**
     * Checks whether to check that the method does not assign a value to an instance variable.
     * @return
     */
    public boolean isInstanceVariableTest() {
        return instanceVariableTest;
    }

    /**
     * Determines whether to check that the method does not assign a value to an instance variable.
     * @param instanceVariableTest The new value.
     */
    public void setInstanceVariableTest(boolean instanceVariableTest) {
        this.instanceVariableTest = instanceVariableTest;
    }

    /**
     * Checks whether to check that the method does not call a private method that is not marked with @MovableMethod.
     * @return
     */
    public boolean isCallNotMoveableMethodTest() {
        return callNotMoveableMethodTest;
    }

    /**
     * Determines whether to check that the method does not call a private method that is not marked with @MovableMethod.
     * @param callNotMoveableMethodTest The new value.
     */
    public void setCallNotMoveableMethodTest(boolean callNotMoveableMethodTest) {
        this.callNotMoveableMethodTest = callNotMoveableMethodTest;
    }
}
