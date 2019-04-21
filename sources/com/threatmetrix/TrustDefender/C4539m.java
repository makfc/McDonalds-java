package com.threatmetrix.TrustDefender;

import com.threatmetrix.TrustDefender.C4537o.C4538a;
import java.io.IOException;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.m */
class C4539m extends C4537o {
    /* renamed from: f */
    private static final String f7801f = C4549w.m8585a(C4539m.class);
    /* renamed from: a */
    public C4492aj f7802a = null;
    /* renamed from: e */
    private C4483e f7803e = null;

    public C4539m(C4480am client, String url, C4540n params, Map<String, String> headers, C4473TrustDefender sdk, C4483e state) {
        super(client, C4538a.GET, url, params, headers, sdk, null, state);
        this.f7803e = state;
    }

    public void run() {
        this.f7802a = null;
        try {
            C4549w.m8594c(f7801f, "starting retrieval: " + this.f7791c + "?" + this.f7792d.mo34254b());
            super.run();
            if (mo34245b() == 200) {
                this.f7802a = new C4492aj();
                try {
                    this.f7802a.mo34135a(this.f7790b.mo34106d());
                    this.f7790b.mo34107e();
                } catch (IOException e) {
                    if (this.f7803e == null || !this.f7803e.mo34114a()) {
                        C4549w.m8588a(f7801f, "IO Error", e);
                    } else {
                        C4549w.m8594c(f7801f, "IO Error, probably due to cancel");
                    }
                    this.f7790b.mo34107e();
                } catch (Throwable th) {
                    this.f7790b.mo34107e();
                    throw th;
                }
            }
        } catch (InterruptedException e2) {
            if (this.f7803e == null || !this.f7803e.mo34114a()) {
                C4549w.m8588a(f7801f, "starting retrieval: " + this.f7791c + " but interrupted", e2);
            } else {
                C4549w.m8594c(f7801f, "starting retrieval: " + this.f7791c + " but interrupted by cancel");
            }
        }
    }

    /* renamed from: a */
    public final THMStatusCode mo34244a() {
        if (this.f7790b.mo34108f() == THMStatusCode.THM_OK) {
            return (this.f7802a == null || !this.f7802a.mo34136a()) ? THMStatusCode.THM_ConfigurationError : THMStatusCode.THM_OK;
        } else {
            return super.mo34244a();
        }
    }
}
