package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.o */
class C4537o implements Runnable {
    /* renamed from: h */
    private static final String f7788h = C4549w.m8585a(C4537o.class);
    /* renamed from: a */
    private final C4538a f7789a;
    /* renamed from: b */
    final C4475aq f7790b;
    /* renamed from: c */
    final String f7791c;
    /* renamed from: d */
    final C4540n f7792d;
    /* renamed from: e */
    private Context f7793e = null;
    /* renamed from: f */
    private final C4473TrustDefender f7794f;
    /* renamed from: g */
    private C4483e f7795g = null;

    /* renamed from: com.threatmetrix.TrustDefender.o$a */
    enum C4538a {
        GET,
        GET_CONSUME,
        POST,
        POST_CONSUME
    }

    public C4537o(C4480am client, C4538a type, String url, C4540n params, Map<String, String> headers, C4473TrustDefender sdk, Context context, C4483e state) {
        this.f7795g = state;
        this.f7790b = client.mo34110a(state);
        this.f7790b.mo34103a((Map) headers);
        this.f7789a = type;
        this.f7791c = url;
        this.f7792d = params;
        this.f7794f = sdk;
        this.f7793e = context;
    }

    public void run() {
        long startTime = System.nanoTime();
        C4549w.m8594c(f7788h, "starting retrieval: " + this.f7791c);
        long result = -1;
        if (this.f7789a == C4538a.GET || this.f7789a == C4538a.GET_CONSUME) {
            try {
                result = this.f7790b.mo34100a(this.f7792d == null ? this.f7791c : this.f7791c + "?" + this.f7792d.mo34254b());
            } catch (InterruptedException e) {
                if (this.f7795g == null || !this.f7795g.mo34114a()) {
                    C4549w.m8588a(f7788h, "interrupted, aborting connection", e);
                } else {
                    C4549w.m8594c(f7788h, "interrupted due to cancel");
                }
                if (this.f7794f != null) {
                    this.f7794f.mo34091a(THMStatusCode.THM_Interrupted_Error);
                    return;
                }
                return;
            }
        } else if (this.f7789a == C4538a.POST || this.f7789a == C4538a.POST_CONSUME) {
            result = this.f7790b.mo34101a(this.f7791c, this.f7792d);
        }
        long duration = (System.nanoTime() - startTime) / 1000000;
        if (result < 0) {
            C4549w.m8592b(f7788h, "failed to retrieve from " + this.f7790b.mo34104b() + " with " + this.f7790b.mo34108f().toString() + " in " + duration + "ms");
            if (this.f7794f != null) {
                this.f7794f.mo34091a(this.f7790b.mo34108f());
                return;
            }
            return;
        }
        C4549w.m8594c(f7788h, "retrieved: " + this.f7790b.mo34102a() + " in " + duration + "ms");
        if (result != 200) {
            C4549w.m8592b(f7788h, "error (" + result + ") status on request to " + this.f7790b.mo34104b());
        } else if (this.f7789a == C4538a.GET_CONSUME || this.f7789a == C4538a.POST_CONSUME) {
            C4549w.m8594c(f7788h, "consuming content");
            this.f7790b.mo34107e();
        }
    }

    /* renamed from: a */
    public THMStatusCode mo34244a() {
        return this.f7790b.mo34108f();
    }

    /* renamed from: b */
    public final int mo34245b() {
        return this.f7790b.mo34109g();
    }

    /* renamed from: c */
    public final void mo34246c() {
        this.f7790b.mo34105c();
    }
}
