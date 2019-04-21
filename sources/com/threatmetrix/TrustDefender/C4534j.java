package com.threatmetrix.TrustDefender;

import android.graphics.Point;
import android.view.Display;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.j */
class C4534j extends C4485at {
    /* renamed from: a */
    private static final String f7770a = C4549w.m8585a(C4534j.class);
    /* renamed from: b */
    private static final Class<?> f7771b = C4485at.m8327b("android.graphics.Point");
    /* renamed from: c */
    private static final Class<?> f7772c = C4485at.m8327b("android.view.WindowManager");
    /* renamed from: d */
    private static final Method f7773d = C4485at.m8325a(Display.class, "getWidth", new Class[0]);
    /* renamed from: e */
    private static final Method f7774e = C4485at.m8325a(Display.class, "getHeight", new Class[0]);
    /* renamed from: f */
    private static final Method f7775f;
    /* renamed from: g */
    private static final Method f7776g;
    /* renamed from: h */
    private static final Method f7777h;
    /* renamed from: i */
    private static final Method f7778i;
    /* renamed from: j */
    private static final Method f7779j = C4485at.m8325a(f7772c, "getDefaultDisplay", new Class[0]);
    /* renamed from: k */
    private Display f7780k;
    /* renamed from: l */
    private Point f7781l;

    static {
        if (f7771b != null) {
            f7775f = C4485at.m8325a(Display.class, "getSize", Point.class);
            f7776g = C4485at.m8325a(Display.class, "getRealSize", Point.class);
            f7777h = C4485at.m8325a(Display.class, "getRawWidth", Point.class);
            f7778i = C4485at.m8325a(Display.class, "getRawHeight", Point.class);
            return;
        }
        f7778i = null;
        f7777h = null;
        f7776g = null;
        f7775f = null;
    }

    /* JADX WARNING: Missing block: B:15:0x003c, code skipped:
            if (r2.y != 0) goto L_0x003e;
     */
    public C4534j(android.content.Context r9) {
        /*
        r8 = this;
        r3 = 0;
        r8.<init>();
        r2 = f7779j;
        if (r2 == 0) goto L_0x001c;
    L_0x0008:
        r2 = "window";
        r1 = r9.getSystemService(r2);	 Catch:{ SecurityException -> 0x0041, Exception -> 0x0045 }
        if (r1 == 0) goto L_0x001c;
    L_0x0010:
        r2 = r1 instanceof android.view.WindowManager;	 Catch:{ SecurityException -> 0x0041, Exception -> 0x0045 }
        if (r2 == 0) goto L_0x001c;
    L_0x0014:
        r1 = (android.view.WindowManager) r1;	 Catch:{ SecurityException -> 0x0041, Exception -> 0x0045 }
        r2 = r1.getDefaultDisplay();	 Catch:{ SecurityException -> 0x0041, Exception -> 0x0045 }
        r8.f7780k = r2;	 Catch:{ SecurityException -> 0x0041, Exception -> 0x0045 }
    L_0x001c:
        r2 = f7771b;
        if (r2 == 0) goto L_0x0052;
    L_0x0020:
        r2 = f7776g;
        if (r2 == 0) goto L_0x0050;
    L_0x0024:
        r2 = new android.graphics.Point;
        r2.<init>();
        r4 = r8.f7780k;
        r5 = f7776g;
        r6 = 1;
        r6 = new java.lang.Object[r6];
        r7 = 0;
        r6[r7] = r2;
        com.threatmetrix.TrustDefender.C4485at.m8323a(r4, r5, r6);
        r4 = r2.x;
        if (r4 == 0) goto L_0x0050;
    L_0x003a:
        r4 = r2.y;
        if (r4 == 0) goto L_0x0050;
    L_0x003e:
        r8.f7781l = r2;
        return;
    L_0x0041:
        r2 = move-exception;
        r2 = f7770a;
        goto L_0x001c;
    L_0x0045:
        r0 = move-exception;
        r2 = f7770a;
        r4 = r0.getMessage();
        com.threatmetrix.TrustDefender.C4549w.m8594c(r2, r4);
        goto L_0x001c;
    L_0x0050:
        r2 = r3;
        goto L_0x003e;
    L_0x0052:
        r2 = r3;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4534j.<init>(android.content.Context):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* renamed from: a */
    public final int mo34237a() {
        /*
        r7 = this;
        r4 = 0;
        r3 = r7.f7780k;
        if (r3 != 0) goto L_0x0007;
    L_0x0005:
        r2 = r4;
    L_0x0006:
        return r2;
    L_0x0007:
        r3 = r7.f7781l;
        if (r3 == 0) goto L_0x0010;
    L_0x000b:
        r3 = r7.f7781l;
        r2 = r3.x;
        goto L_0x0006;
    L_0x0010:
        r3 = f7777h;
        if (r3 == 0) goto L_0x0040;
    L_0x0014:
        r3 = r7.f7780k;
        r5 = f7777h;
        r6 = new java.lang.Object[r4];
        r3 = com.threatmetrix.TrustDefender.C4485at.m8323a(r3, r5, r6);
        r3 = (java.lang.Integer) r3;
        if (r3 == 0) goto L_0x0040;
    L_0x0022:
        r2 = r3.intValue();
    L_0x0026:
        if (r2 != 0) goto L_0x0006;
    L_0x0028:
        r3 = f7775f;
        if (r3 == 0) goto L_0x0042;
    L_0x002c:
        r0 = new android.graphics.Point;
        r0.<init>();
        r3 = r7.f7780k;
        r5 = f7775f;
        r6 = 1;
        r6 = new java.lang.Object[r6];
        r6[r4] = r0;
        com.threatmetrix.TrustDefender.C4485at.m8323a(r3, r5, r6);
        r2 = r0.x;
        goto L_0x0006;
    L_0x0040:
        r2 = r4;
        goto L_0x0026;
    L_0x0042:
        r3 = f7773d;
        if (r3 == 0) goto L_0x0059;
    L_0x0046:
        r3 = r7.f7780k;
        r5 = f7773d;
        r6 = new java.lang.Object[r4];
        r1 = com.threatmetrix.TrustDefender.C4485at.m8323a(r3, r5, r6);
        r1 = (java.lang.Integer) r1;
        if (r1 == 0) goto L_0x0059;
    L_0x0054:
        r2 = r1.intValue();
        goto L_0x0006;
    L_0x0059:
        r3 = f7770a;
        r2 = r4;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4534j.mo34237a():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* renamed from: b */
    public final int mo34238b() {
        /*
        r7 = this;
        r4 = 0;
        r3 = r7.f7780k;
        if (r3 != 0) goto L_0x0007;
    L_0x0005:
        r0 = r4;
    L_0x0006:
        return r0;
    L_0x0007:
        r3 = r7.f7781l;
        if (r3 == 0) goto L_0x0010;
    L_0x000b:
        r3 = r7.f7781l;
        r0 = r3.y;
        goto L_0x0006;
    L_0x0010:
        r3 = f7778i;
        if (r3 == 0) goto L_0x0040;
    L_0x0014:
        r3 = r7.f7780k;
        r5 = f7778i;
        r6 = new java.lang.Object[r4];
        r3 = com.threatmetrix.TrustDefender.C4485at.m8323a(r3, r5, r6);
        r3 = (java.lang.Integer) r3;
        if (r3 == 0) goto L_0x0040;
    L_0x0022:
        r0 = r3.intValue();
    L_0x0026:
        if (r0 != 0) goto L_0x0006;
    L_0x0028:
        r3 = f7775f;
        if (r3 == 0) goto L_0x0042;
    L_0x002c:
        r1 = new android.graphics.Point;
        r1.<init>();
        r3 = r7.f7780k;
        r5 = f7775f;
        r6 = 1;
        r6 = new java.lang.Object[r6];
        r6[r4] = r1;
        com.threatmetrix.TrustDefender.C4485at.m8323a(r3, r5, r6);
        r0 = r1.y;
        goto L_0x0006;
    L_0x0040:
        r0 = r4;
        goto L_0x0026;
    L_0x0042:
        r3 = f7774e;
        if (r3 == 0) goto L_0x0059;
    L_0x0046:
        r3 = r7.f7780k;
        r5 = f7774e;
        r6 = new java.lang.Object[r4];
        r2 = com.threatmetrix.TrustDefender.C4485at.m8323a(r3, r5, r6);
        r2 = (java.lang.Integer) r2;
        if (r2 == 0) goto L_0x0059;
    L_0x0054:
        r0 = r2.intValue();
        goto L_0x0006;
    L_0x0059:
        r3 = f7770a;
        r0 = r4;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4534j.mo34238b():int");
    }
}
