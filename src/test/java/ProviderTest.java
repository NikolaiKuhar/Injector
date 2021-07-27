import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ProviderTest {
    @Test
    public void testProviderCreateObject() {
        PrototypeProviderImpl<ArrayList> arrayListProvider = new PrototypeProviderImpl<>(ArrayList.class, null);
        ArrayList instance = arrayListProvider.getInstance();
        Assert.assertNotNull(instance);
    }
}
