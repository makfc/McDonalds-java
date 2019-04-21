package p041io.github.inflationx.calligraphy3;

import p041io.github.inflationx.viewpump.InflateResult;
import p041io.github.inflationx.viewpump.Interceptor;
import p041io.github.inflationx.viewpump.Interceptor.Chain;

/* renamed from: io.github.inflationx.calligraphy3.CalligraphyInterceptor */
public class CalligraphyInterceptor implements Interceptor {
    private final Calligraphy calligraphy;

    public CalligraphyInterceptor(CalligraphyConfig calligraphyConfig) {
        this.calligraphy = new Calligraphy(calligraphyConfig);
    }

    public InflateResult intercept(Chain chain) {
        InflateResult result = chain.proceed(chain.request());
        return result.toBuilder().view(this.calligraphy.onViewCreated(result.view(), result.context(), result.attrs())).build();
    }
}
