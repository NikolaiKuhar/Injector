public class SingletonProviderImpl<T>  extends  PrototypeProviderImpl<T>{
    public SingletonProviderImpl(Class<T> aClass, Injector injector) {
        super(aClass, injector);
    }

    @Override
    public T getInstance() {
        if (cache == null) {
            cache = super.getInstance();
        }
        return cache;
    }

    private T cache;

}
