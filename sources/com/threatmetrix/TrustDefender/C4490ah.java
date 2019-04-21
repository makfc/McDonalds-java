package com.threatmetrix.TrustDefender;

import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.ah */
class C4490ah extends C4485at {
    /* renamed from: a */
    private static final Class<?> f7383a;
    /* renamed from: b */
    private static final Method f7384b;
    /* renamed from: c */
    private static final Method f7385c = C4485at.m8325a(f7383a, "getBlockSizeLong", new Class[0]);
    /* renamed from: d */
    private static final Method f7386d = C4485at.m8325a(f7383a, "getAvailableBlocks", new Class[0]);
    /* renamed from: e */
    private static final Method f7387e = C4485at.m8325a(f7383a, "getAvailableBlocksLong", new Class[0]);
    /* renamed from: f */
    private static final Method f7388f = C4485at.m8325a(f7383a, "getBlockCount", new Class[0]);
    /* renamed from: g */
    private static final Method f7389g = C4485at.m8325a(f7383a, "getBlockCountLong", new Class[0]);
    /* renamed from: h */
    private static final String f7390h = C4549w.m8585a(C4490ah.class);
    /* renamed from: i */
    private final Object f7391i;

    static {
        Class b = C4485at.m8327b("android.os.StatFs");
        f7383a = b;
        f7384b = C4485at.m8325a(b, "getBlockSize", new Class[0]);
    }

    public C4490ah(String path) {
        if (f7383a == null) {
            this.f7391i = null;
            return;
        }
        this.f7391i = C4485at.m8320a(f7383a, new Class[]{String.class}, new Object[]{path});
    }

    /* renamed from: a */
    public final long mo34132a() {
        if (f7389g != null) {
            Long ret = (Long) C4485at.m8323a(this.f7391i, f7389g, new Object[0]);
            if (ret != null) {
                return ret.longValue();
            }
        }
        if (f7388f != null) {
            Integer ret2 = (Integer) C4485at.m8323a(this.f7391i, f7388f, new Object[0]);
            if (ret2 != null) {
                return (long) ret2.intValue();
            }
        }
        return 0;
    }

    /* renamed from: b */
    public final long mo34133b() {
        if (f7385c != null) {
            Long ret = (Long) C4485at.m8323a(this.f7391i, f7385c, new Object[0]);
            if (ret != null) {
                return ret.longValue();
            }
        }
        if (f7384b != null) {
            Integer ret2 = (Integer) C4485at.m8323a(this.f7391i, f7384b, new Object[0]);
            if (ret2 != null) {
                return (long) ret2.intValue();
            }
        }
        return 0;
    }

    /* renamed from: c */
    public final long mo34134c() {
        if (f7387e != null) {
            Long ret = (Long) C4485at.m8323a(this.f7391i, f7387e, new Object[0]);
            if (ret != null) {
                return ret.longValue();
            }
        }
        if (f7386d != null) {
            Integer ret2 = (Integer) C4485at.m8323a(this.f7391i, f7386d, new Object[0]);
            if (ret2 != null) {
                return (long) ret2.intValue();
            }
        }
        return 0;
    }
}
