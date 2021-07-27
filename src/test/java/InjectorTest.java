import org.testng.Assert;
import org.testng.annotations.Test;
import stub.*;

import java.util.*;

public class InjectorTest {
    @Test
    public void testBindingWorkCorrectly() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(List.class, ArrayList.class);
        injector.bind(Set.class, HashSet.class);
        Provider<List> ListProvider = injector.getProvider(List.class);
        Assert.assertNotNull(ListProvider);
        Provider<Set> SetProvider = injector.getProvider(Set.class);
        Assert.assertNotNull(SetProvider);
    }

    @Test
    public void testBindingForMissing() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(List.class, ArrayList.class);
        injector.bind(Set.class, HashSet.class);
        Provider<Map> mapProvider = injector.getProvider(Map.class);
        Assert.assertNull(mapProvider);


    }

    @Test
    public void testInjectAnnotationWorkCorrectly() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInferfaceImpl.class);
        injector.bind(TestInterface2.class, TestInterface2Impl.class);
        TestInterface testInterface = injector.getProvider(TestInterface.class).getInstance();
        Assert.assertNotNull(testInterface.getTestInterface2());
    }

    @Test(expectedExceptions = {TooManyConstructorsException.class})
    public void testToManyConstructorsException() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(TestInterface2.class, TestToInjectConstructors.class);
        injector.getProvider(TestInterface2.class).getInstance();
    }

    @Test(expectedExceptions = {ConstructorNotFoundException.class})
    public void testConstructorNotFoundException() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(TestInterface2.class, TestDefaulConstructor.class);
        injector.getProvider(TestInterface2.class).getInstance();
    }

    @Test(expectedExceptions = {BindingNotFoundException.class})
    public void testBindingNotFoundException() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInferfaceImpl.class);
        TestInterface testInterface = injector.getProvider(TestInterface.class).getInstance();
        Assert.assertNotNull(testInterface.getTestInterface2());
    }

    @Test
    public void testSingletonReturnSameInstance() {
        InjectorImpl injector = new InjectorImpl();
        injector.bindSingleton(TestInterface.class, TestInferfaceImpl.class);
        injector.bindSingleton(TestInterface2.class, TestInterface2Impl.class);
        TestInterface testInterface = injector.getProvider(TestInterface.class).getInstance();
        TestInterface testInterface2 = injector.getProvider(TestInterface.class).getInstance();
        Assert.assertNotNull(testInterface.getTestInterface2());
        Assert.assertSame(testInterface,testInterface2);
    }

    @Test
    public void testPrototypeReturnDifferentInstance() {
        InjectorImpl injector = new InjectorImpl();
        injector.bind(TestInterface.class, TestInferfaceImpl.class);
        injector.bind(TestInterface2.class, TestInterface2Impl.class);
        TestInterface testInterface = injector.getProvider(TestInterface.class).getInstance();
        TestInterface testInterface2 = injector.getProvider(TestInterface.class).getInstance();
        Assert.assertNotNull(testInterface.getTestInterface2());
        Assert.assertNotSame(testInterface,testInterface2);
    }
}
