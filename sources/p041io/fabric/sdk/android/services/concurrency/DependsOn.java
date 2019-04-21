package p041io.fabric.sdk.android.services.concurrency;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* renamed from: io.fabric.sdk.android.services.concurrency.DependsOn */
public @interface DependsOn {
    Class<?>[] value();
}
