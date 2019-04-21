package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log */
/* renamed from: com.amap.api.mapcore2d.db */
public class C0985db {
    /* renamed from: a */
    public static final String f2808a = "/a/";
    /* renamed from: b */
    static final String f2809b = "b";
    /* renamed from: c */
    static final String f2810c = "c";
    /* renamed from: d */
    static final String f2811d = "d";
    /* renamed from: e */
    public static final String f2812e = "e";

    /* renamed from: a */
    public static String m4081a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(f2808a);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static Class<? extends C1001ds> m4080a(int i) {
        switch (i) {
            case 0:
                return C1003dn.class;
            case 1:
                return C1005dp.class;
            case 2:
                return C1002dm.class;
            default:
                return null;
        }
    }

    /* renamed from: b */
    public static C1001ds m4084b(int i) {
        switch (i) {
            case 0:
                return new C1003dn();
            case 1:
                return new C1005dp();
            case 2:
                return new C1002dm();
            default:
                return null;
        }
    }

    /* renamed from: c */
    public static String m4086c(int i) {
        switch (i) {
            case 0:
                return f2810c;
            case 1:
                return f2809b;
            case 2:
                return f2811d;
            default:
                return "";
        }
    }

    /* renamed from: a */
    static C0991dh m4079a(Context context, int i) {
        switch (i) {
            case 0:
                return new C0994df(i);
            case 1:
                return new C0995dg(i);
            case 2:
                return new C0993de(i);
            default:
                return null;
        }
    }

    /* renamed from: a */
    static void m4083a(Context context, Throwable th, int i, String str, String str2) {
        try {
            ExecutorService c = C0990dd.m4099c();
            if (c != null && !c.isShutdown()) {
                final Context context2 = context;
                final int i2 = i;
                final Throwable th2 = th;
                final String str3 = str;
                final String str4 = str2;
                c.submit(new Runnable() {
                    public void run() {
                        try {
                            C0991dh a = C0985db.m4079a(context2, i2);
                            if (a != null) {
                                a.mo10194a(context2, th2, str3, str4);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
    }

    /* renamed from: a */
    static void m4082a(Context context) {
        try {
            C0991dh a = C0985db.m4079a(context, 2);
            if (a != null) {
                a.mo10199b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    static void m4085b(final Context context) {
        try {
            ExecutorService c = C0990dd.m4099c();
            if (c != null && !c.isShutdown()) {
                c.submit(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069  */
                    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ac A:{ExcHandler: RejectedExecutionException (e java.util.concurrent.RejectedExecutionException), Splitter:B:5:0x000f} */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x004e  */
                    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069  */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x004e  */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
                    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069  */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x004e  */
                    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069  */
                    /* JADX WARNING: Failed to process nested try/catch */
                    /* JADX WARNING: Missing block: B:20:0x0049, code skipped:
            r3.mo10200c();
     */
                    /* JADX WARNING: Missing block: B:22:0x004e, code skipped:
            r2.mo10200c();
     */
                    /* JADX WARNING: Missing block: B:24:0x0053, code skipped:
            r1.mo10200c();
     */
                    /* JADX WARNING: Missing block: B:43:0x0084, code skipped:
            r3 = move-exception;
     */
                    /* JADX WARNING: Missing block: B:44:0x0085, code skipped:
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
     */
                    /* JADX WARNING: Missing block: B:50:0x009b, code skipped:
            r3 = move-exception;
     */
                    /* JADX WARNING: Missing block: B:51:0x009c, code skipped:
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
     */
                    /* JADX WARNING: Missing block: B:59:?, code skipped:
            return;
     */
                    /* JADX WARNING: Missing block: B:60:?, code skipped:
            return;
     */
                    public void run() {
                        /*
                        r7 = this;
                        r0 = 0;
                        r1 = r3;	 Catch:{ RejectedExecutionException -> 0x006d, Throwable -> 0x003a, all -> 0x0057 }
                        r2 = 0;
                        r2 = com.amap.api.mapcore2d.C0985db.m4079a(r1, r2);	 Catch:{ RejectedExecutionException -> 0x006d, Throwable -> 0x003a, all -> 0x0057 }
                        r1 = r3;	 Catch:{ RejectedExecutionException -> 0x00a9, Throwable -> 0x0094, all -> 0x007d }
                        r3 = 1;
                        r1 = com.amap.api.mapcore2d.C0985db.m4079a(r1, r3);	 Catch:{ RejectedExecutionException -> 0x00a9, Throwable -> 0x0094, all -> 0x007d }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x009b, all -> 0x0084 }
                        r4 = 2;
                        r0 = com.amap.api.mapcore2d.C0985db.m4079a(r3, r4);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x009b, all -> 0x0084 }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r2.mo10201c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r1.mo10201c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r0.mo10201c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        com.amap.api.mapcore2d.C0980cy.m4066a(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        if (r2 == 0) goto L_0x002f;
                    L_0x002c:
                        r2.mo10200c();
                    L_0x002f:
                        if (r1 == 0) goto L_0x0034;
                    L_0x0031:
                        r1.mo10200c();
                    L_0x0034:
                        if (r0 == 0) goto L_0x0039;
                    L_0x0036:
                        r0.mo10200c();
                    L_0x0039:
                        return;
                    L_0x003a:
                        r1 = move-exception;
                        r2 = r0;
                        r3 = r0;
                        r6 = r1;
                        r1 = r0;
                        r0 = r6;
                    L_0x0040:
                        r4 = "Log";
                        r5 = "processLog";
                        com.amap.api.mapcore2d.C0982da.m4076a(r0, r4, r5);	 Catch:{ all -> 0x0092 }
                        if (r3 == 0) goto L_0x004c;
                    L_0x0049:
                        r3.mo10200c();
                    L_0x004c:
                        if (r2 == 0) goto L_0x0051;
                    L_0x004e:
                        r2.mo10200c();
                    L_0x0051:
                        if (r1 == 0) goto L_0x0039;
                    L_0x0053:
                        r1.mo10200c();
                        goto L_0x0039;
                    L_0x0057:
                        r1 = move-exception;
                        r2 = r0;
                        r3 = r0;
                        r6 = r1;
                        r1 = r0;
                        r0 = r6;
                    L_0x005d:
                        if (r3 == 0) goto L_0x0062;
                    L_0x005f:
                        r3.mo10200c();
                    L_0x0062:
                        if (r2 == 0) goto L_0x0067;
                    L_0x0064:
                        r2.mo10200c();
                    L_0x0067:
                        if (r1 == 0) goto L_0x006c;
                    L_0x0069:
                        r1.mo10200c();
                    L_0x006c:
                        throw r0;
                    L_0x006d:
                        r1 = move-exception;
                        r1 = r0;
                        r2 = r0;
                    L_0x0070:
                        if (r2 == 0) goto L_0x0075;
                    L_0x0072:
                        r2.mo10200c();
                    L_0x0075:
                        if (r1 == 0) goto L_0x007a;
                    L_0x0077:
                        r1.mo10200c();
                    L_0x007a:
                        if (r0 == 0) goto L_0x0039;
                    L_0x007c:
                        goto L_0x0036;
                    L_0x007d:
                        r1 = move-exception;
                        r3 = r2;
                        r2 = r0;
                        r6 = r0;
                        r0 = r1;
                        r1 = r6;
                        goto L_0x005d;
                    L_0x0084:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x005d;
                    L_0x008b:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x005d;
                    L_0x0092:
                        r0 = move-exception;
                        goto L_0x005d;
                    L_0x0094:
                        r1 = move-exception;
                        r3 = r2;
                        r2 = r0;
                        r6 = r0;
                        r0 = r1;
                        r1 = r6;
                        goto L_0x0040;
                    L_0x009b:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x0040;
                    L_0x00a2:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x0040;
                    L_0x00a9:
                        r1 = move-exception;
                        r1 = r0;
                        goto L_0x0070;
                    L_0x00ac:
                        r3 = move-exception;
                        goto L_0x0070;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0985db$C09842.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            C0982da.m4076a(th, "Log", "processLog");
        }
    }
}
