package p041io.github.inflationx.viewpump;

import android.support.annotation.NonNull;
import java.util.List;
import p041io.github.inflationx.viewpump.Interceptor.Chain;

/* renamed from: io.github.inflationx.viewpump.InterceptorChain */
class InterceptorChain implements Chain {
    private final int index;
    private final List<Interceptor> interceptors;
    private final InflateRequest request;

    InterceptorChain(@NonNull List<Interceptor> interceptors, int index, @NonNull InflateRequest request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @NonNull
    public InflateRequest request() {
        return this.request;
    }

    @NonNull
    public InflateResult proceed(@NonNull InflateRequest request) {
        if (this.index >= this.interceptors.size()) {
            throw new AssertionError("no interceptors added to the chain");
        }
        Interceptor interceptor = (Interceptor) this.interceptors.get(this.index);
        InflateResult result = interceptor.intercept(new InterceptorChain(this.interceptors, this.index + 1, request));
        if (result != null) {
            return result;
        }
        throw new NullPointerException("interceptor " + interceptor + " returned null");
    }
}
