package com.threatmetrix.TrustDefender;

import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.ae */
class C4486ae extends C4485at {
    /* renamed from: a */
    private static final Method f7367a;
    /* renamed from: b */
    private static final Method f7368b;
    /* renamed from: c */
    private static final String f7369c = C4549w.m8585a(C4486ae.class);
    /* renamed from: d */
    private String f7370d = null;
    /* renamed from: e */
    private int f7371e = 0;

    static {
        Class<?> proxyClass = C4485at.m8327b("android.net.Proxy");
        f7367a = C4485at.m8325a((Class) proxyClass, "getDefaultHost", new Class[0]);
        f7368b = C4485at.m8325a((Class) proxyClass, "getDefaultPort", new Class[0]);
    }

    C4486ae() {
        String host = System.getProperty("http.proxyHost");
        if (!(host == null || host.isEmpty())) {
            this.f7370d = host;
        }
        String portStr = System.getProperty("http.proxyPort");
        if (!(portStr == null || portStr.isEmpty())) {
            try {
                this.f7371e = Integer.parseInt(portStr);
            } catch (NumberFormatException e) {
            }
        }
        if (this.f7370d == null || this.f7371e == 0) {
            Integer p = (Integer) C4485at.m8323a(null, f7368b, new Object[0]);
            if (p != null) {
                this.f7371e = p.intValue();
            }
            String h = (String) C4485at.m8323a(null, f7367a, new Object[0]);
            if (h != null) {
                this.f7370d = h;
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final String mo34128a() {
        return this.f7370d;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final int mo34129b() {
        return this.f7371e;
    }
}
