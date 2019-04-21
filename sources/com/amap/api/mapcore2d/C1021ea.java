package com.amap.api.mapcore2d;

import java.net.Proxy;

/* compiled from: BaseNetManager */
/* renamed from: com.amap.api.mapcore2d.ea */
public class C1021ea {
    /* renamed from: a */
    private static C1021ea f2895a;

    /* renamed from: a */
    public static C1021ea m4260a() {
        if (f2895a == null) {
            f2895a = new C1021ea();
        }
        return f2895a;
    }

    /* renamed from: a */
    public byte[] mo10269a(C0931eg c0931eg) throws C0956cl {
        C0956cl e;
        try {
            C1031ei a = mo10268a(c0931eg, true);
            if (a != null) {
                return a.f2916a;
            }
            return null;
        } catch (C0956cl e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new C0956cl("未知的错误");
        }
    }

    /* renamed from: b */
    public byte[] mo10270b(C0931eg c0931eg) throws C0956cl {
        C0956cl e;
        try {
            C1031ei a = mo10268a(c0931eg, false);
            if (a != null) {
                return a.f2916a;
            }
            return null;
        } catch (C0956cl e2) {
            throw e2;
        } catch (Throwable th) {
            C0982da.m4076a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new C0956cl("未知的错误");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo10271c(C0931eg c0931eg) throws C0956cl {
        if (c0931eg == null) {
            throw new C0956cl("requeust is null");
        } else if (c0931eg.mo10073g() == null || "".equals(c0931eg.mo10073g())) {
            throw new C0956cl("request url is empty");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public C1031ei mo10268a(C0931eg c0931eg, boolean z) throws C0956cl {
        C0956cl e;
        try {
            Proxy proxy;
            mo10271c(c0931eg);
            if (c0931eg.f2563e == null) {
                proxy = null;
            } else {
                proxy = c0931eg.f2563e;
            }
            return new C1024ed(c0931eg.f2561c, c0931eg.f2562d, proxy, z).mo10275a(c0931eg.mo10074h(), c0931eg.mo10071e(), c0931eg.mo10075i());
        } catch (C0956cl e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new C0956cl("未知的错误");
        }
    }
}
