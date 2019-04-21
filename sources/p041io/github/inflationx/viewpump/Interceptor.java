package p041io.github.inflationx.viewpump;

/* renamed from: io.github.inflationx.viewpump.Interceptor */
public interface Interceptor {

    /* renamed from: io.github.inflationx.viewpump.Interceptor$Chain */
    public interface Chain {
        InflateResult proceed(InflateRequest inflateRequest);

        InflateRequest request();
    }

    InflateResult intercept(Chain chain);
}
