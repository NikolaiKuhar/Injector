import java.util.HashMap;
import java.util.Map;

public class InjectorImpl implements  Injector{
    private  Map<Class<?>,Provider<?>> binding = new HashMap<>();
    @Override
    public <T> Provider<T> getProvider(Class<T> intf) {
        return (Provider<T>) binding.get(intf);
    }

    @Override
    public <T> void bind(Class<T> intf, Class<? extends T> impl) {
        binding.put(intf, new PrototypeProviderImpl<>(impl, this));

    }

    @Override
    public <T> void bindSingleton(Class<T> intf, Class<? extends T> impl) {
        binding.put(intf, new SingletonProviderImpl<>(impl, this));

    }
}
