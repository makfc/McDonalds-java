package com.threatmetrix.TrustDefender;

import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.threatmetrix.TrustDefender.u */
class C2625u implements ValueCallback<String> {
    /* renamed from: d */
    private static final String f6076d = C4549w.m8585a(C2625u.class);
    /* renamed from: a */
    CountDownLatch f6077a = null;
    /* renamed from: b */
    public String f6078b;
    /* renamed from: c */
    public final ArrayList<String> f6079c = new ArrayList();

    public /* synthetic */ void onReceiveValue(Object x0) {
        String str = (String) x0;
        if (str != null) {
            if (str.length() == 2 && str.equals("\"\"")) {
                str = "";
            } else if (str.length() > 1) {
                str = str.substring(1, str.length() - 1);
            }
        }
        m7481a(str, "onReceiveValue");
    }

    C2625u(CountDownLatch latch) {
        mo23199a(latch);
    }

    /* renamed from: a */
    public final void mo23199a(CountDownLatch latch) {
        if (this.f6077a != null) {
            C4549w.m8594c(f6076d, "existing latch: " + this.f6077a.hashCode() + " with count: " + this.f6077a.getCount());
            C4549w.m8594c(f6076d, "Setting latch when latch already has non-null value");
        }
        this.f6077a = latch;
        if (this.f6077a != null) {
            C4549w.m8594c(f6076d, "new latch: " + latch.hashCode() + " with count: " + latch.getCount());
        }
    }

    /* renamed from: a */
    private void m7481a(String value, String source) {
        try {
            CountDownLatch l = this.f6077a;
            String log_message = value;
            if (value == null) {
                log_message = SafeJsonPrimitive.NULL_STRING;
            }
            long count = 0;
            if (l != null) {
                count = l.getCount();
            }
            C4549w.m8594c(f6076d, "in " + source + "(" + log_message + ") count = " + count);
            this.f6078b = value;
            if (value == null) {
                this.f6079c.add("");
            } else {
                this.f6079c.add(value);
            }
            if (l != null) {
                C4549w.m8594c(f6076d, "countdown latch: " + l.hashCode() + " with count: " + l.getCount());
                l.countDown();
                if (source == null) {
                    source = SafeJsonPrimitive.NULL_STRING;
                }
                if (l == null) {
                    C4549w.m8594c(f6076d, "in " + source + "() with null latch");
                    return;
                } else {
                    C4549w.m8594c(f6076d, "in " + source + "() count = " + l.getCount() + " and " + (l == this.f6077a ? "latch constant" : "latch changed"));
                    return;
                }
            }
            C4549w.m8587a(f6076d, "in " + source + "() latch == null");
        } catch (Exception e) {
            String str = f6076d;
        }
    }

    @JavascriptInterface
    /* renamed from: a */
    public final void mo23198a(String value) {
        m7481a(value, "getString");
    }
}
