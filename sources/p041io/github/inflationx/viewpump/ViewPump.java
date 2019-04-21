package p041io.github.inflationx.viewpump;

import android.support.annotation.MainThread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: io.github.inflationx.viewpump.ViewPump */
public final class ViewPump {
    private static ViewPump INSTANCE;
    private final List<Interceptor> interceptors;
    private final boolean mCustomViewCreation;
    private final List<Interceptor> mInterceptorsWithFallback;
    private final boolean mReflection;

    /* renamed from: io.github.inflationx.viewpump.ViewPump$Builder */
    public static final class Builder {
        private boolean customViewCreation;
        private final List<Interceptor> interceptors;
        private boolean reflection;

        /* synthetic */ Builder(C26291 x0) {
            this();
        }

        private Builder() {
            this.interceptors = new ArrayList();
            this.reflection = true;
            this.customViewCreation = true;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder setPrivateFactoryInjectionEnabled(boolean enabled) {
            this.reflection = enabled;
            return this;
        }

        public Builder setCustomViewInflationEnabled(boolean enabled) {
            this.customViewCreation = enabled;
            return this;
        }

        public ViewPump build() {
            return new ViewPump(this);
        }
    }

    private ViewPump(Builder builder) {
        this.interceptors = ViewPump.immutableList(builder.interceptors);
        List<Interceptor> interceptorsWithFallback = builder.interceptors;
        interceptorsWithFallback.add(new FallbackViewCreationInterceptor());
        this.mInterceptorsWithFallback = ViewPump.immutableList(interceptorsWithFallback);
        this.mReflection = builder.reflection;
        this.mCustomViewCreation = builder.customViewCreation;
    }

    public static void init(ViewPump viewPump) {
        INSTANCE = viewPump;
    }

    @MainThread
    public static ViewPump get() {
        if (INSTANCE == null) {
            INSTANCE = ViewPump.builder().build();
        }
        return INSTANCE;
    }

    public InflateResult inflate(InflateRequest originalRequest) {
        return new InterceptorChain(this.mInterceptorsWithFallback, 0, originalRequest).proceed(originalRequest);
    }

    public List<Interceptor> interceptors() {
        return this.interceptors;
    }

    public boolean isReflection() {
        return this.mReflection;
    }

    public boolean isCustomViewCreation() {
        return this.mCustomViewCreation;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }
}
