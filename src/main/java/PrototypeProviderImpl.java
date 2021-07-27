import annotation.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrototypeProviderImpl<T> implements Provider<T> {
    private Class<T> tClass;
    private Injector injector;

    public PrototypeProviderImpl(Class<T> tClass, Injector injector) {
        Objects.requireNonNull(tClass);
        this.tClass = tClass;
        this.injector = injector;
    }

    @Override
    public T getInstance() {
        Constructor<?>[] t = null;
        try {
            t = tClass.getConstructors();
            int count = 0;
            for (Constructor<?> constructor : tClass.getConstructors()) {
                if (constructor.isAnnotationPresent(Inject.class)) {
                    count++;
                }
            }
            if (count > 1) {
                throw new TooManyConstructorsException();
            }
            for (Constructor<?> constructor : tClass.getConstructors()) {
                if (constructor.isAnnotationPresent(Inject.class)) {
                    Parameter[] constructorParameters = constructor.getParameters();
                    List<Object> args = new ArrayList<>();
                    for (Parameter constructorParameter : constructorParameters) {
                        Provider<?> provider = injector.getProvider(constructorParameter.getType());
                        if (provider == null){
                            throw new BindingNotFoundException();
                        }
                        Object instance = provider.getInstance();
                        args.add(instance);
                    }
                    return (T) constructor.newInstance(args.toArray());
                }
            }
            return tClass.getDeclaredConstructor().newInstance();
        } catch (TooManyConstructorsException | BindingNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw  new ConstructorNotFoundException();


    }


}
