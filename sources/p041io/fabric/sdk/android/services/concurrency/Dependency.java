package p041io.fabric.sdk.android.services.concurrency;

import java.util.Collection;

/* renamed from: io.fabric.sdk.android.services.concurrency.Dependency */
public interface Dependency<T> {
    void addDependency(T t);

    boolean areDependenciesMet();

    Collection<T> getDependencies();
}
