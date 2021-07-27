package stub;

import annotation.Inject;

import java.util.concurrent.Callable;

public class TestToInjectConstructors implements  TestInterface2{
    @Inject
    public TestToInjectConstructors(Runnable R) {
    }

    @Inject
    public TestToInjectConstructors(Callable C) {
    }
}
