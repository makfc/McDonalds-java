package p041io.github.inflationx.viewpump;

import android.view.View;
import p041io.github.inflationx.viewpump.Interceptor.Chain;

/* renamed from: io.github.inflationx.viewpump.FallbackViewCreationInterceptor */
class FallbackViewCreationInterceptor implements Interceptor {
    FallbackViewCreationInterceptor() {
    }

    public InflateResult intercept(Chain chain) {
        InflateRequest request = chain.request();
        View fallbackView = request.fallbackViewCreator().onCreateView(request.parent(), request.name(), request.context(), request.attrs());
        return InflateResult.builder().view(fallbackView).name(fallbackView != null ? fallbackView.getClass().getName() : request.name()).context(request.context()).attrs(request.attrs()).build();
    }
}
