package stub;

import annotation.Inject;

public class TestInferfaceImpl implements TestInterface{
    @Inject
    public TestInferfaceImpl(TestInterface2 testInterface2) {
        this.testInterface2 = testInterface2;
    }

    public TestInterface2 getTestInterface2() {
        return testInterface2;
    }

    public void setTestInterface2(TestInterface2 testInterface2) {
        this.testInterface2 = testInterface2;
    }

    private TestInterface2 testInterface2;
}
