package com.amap.api.services.core;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log */
/* renamed from: com.amap.api.services.core.be */
public class C1107be {
    /* renamed from: a */
    static final String f3724a = "/a/";
    /* renamed from: b */
    static final String f3725b = "b";
    /* renamed from: c */
    static final String f3726c = "c";
    /* renamed from: d */
    static final String f3727d = "d";

    /* renamed from: a */
    static void m4877a(Context context, Throwable th, int i, String str, String str2) {
        try {
            ExecutorService a = C1099ax.m4797a();
            if (a != null && !a.isShutdown()) {
                final int i2 = i;
                final Context context2 = context;
                final Throwable th2 = th;
                final String str3 = str;
                final String str4 = str2;
                a.submit(new Runnable() {
                    public void run() {
                        try {
                            LogWriter a = LogWriter.m4823a(i2);
                            if (a != null) {
                                a.mo12053a(context2, th2, str3, str4);
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
    static void m4876a(Context context) {
        try {
            LogWriter a = LogWriter.m4823a(2);
            if (a != null) {
                a.mo12052a(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    static void m4878b(final Context context) {
        try {
            ExecutorService a = C1099ax.m4797a();
            if (a != null && !a.isShutdown()) {
                a.submit(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067  */
                    /* JADX WARNING: Removed duplicated region for block: B:56:0x00aa A:{ExcHandler: RejectedExecutionException (e java.util.concurrent.RejectedExecutionException), Splitter:B:5:0x000f} */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
                    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067  */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
                    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067  */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
                    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
                    /* JADX WARNING: Removed duplicated region for block: B:59:? A:{SYNTHETIC, RETURN} */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
                    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d  */
                    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062  */
                    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067  */
                    /* JADX WARNING: Failed to process nested try/catch */
                    /* JADX WARNING: Missing block: B:20:0x0047, code skipped:
            r3.mo12045c();
     */
                    /* JADX WARNING: Missing block: B:22:0x004c, code skipped:
            r2.mo12045c();
     */
                    /* JADX WARNING: Missing block: B:24:0x0051, code skipped:
            r1.mo12045c();
     */
                    /* JADX WARNING: Missing block: B:43:0x0082, code skipped:
            r3 = move-exception;
     */
                    /* JADX WARNING: Missing block: B:44:0x0083, code skipped:
            r6 = r3;
            r3 = r2;
            r2 = r1;
            r1 = r0;
            r0 = r6;
     */
                    /* JADX WARNING: Missing block: B:50:0x0099, code skipped:
            r3 = move-exception;
     */
                    /* JADX WARNING: Missing block: B:51:0x009a, code skipped:
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
                        r1 = r3;	 Catch:{ RejectedExecutionException -> 0x006b, Throwable -> 0x0035, all -> 0x0055 }
                        r2 = 0;
                        r2 = com.amap.api.services.core.LogUpDateProcessor.m4804a(r1, r2);	 Catch:{ RejectedExecutionException -> 0x006b, Throwable -> 0x0035, all -> 0x0055 }
                        r1 = r3;	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x0092, all -> 0x007b }
                        r3 = 1;
                        r1 = com.amap.api.services.core.LogUpDateProcessor.m4804a(r1, r3);	 Catch:{ RejectedExecutionException -> 0x00a7, Throwable -> 0x0092, all -> 0x007b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x0099, all -> 0x0082 }
                        r4 = 2;
                        r0 = com.amap.api.services.core.LogUpDateProcessor.m4804a(r3, r4);	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x0099, all -> 0x0082 }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x00a0, all -> 0x0089 }
                        r2.mo12044b(r3);	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x00a0, all -> 0x0089 }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x00a0, all -> 0x0089 }
                        r1.mo12044b(r3);	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x00a0, all -> 0x0089 }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x00a0, all -> 0x0089 }
                        r0.mo12044b(r3);	 Catch:{ RejectedExecutionException -> 0x00aa, Throwable -> 0x00a0, all -> 0x0089 }
                        if (r2 == 0) goto L_0x002a;
                    L_0x0027:
                        r2.mo12045c();
                    L_0x002a:
                        if (r1 == 0) goto L_0x002f;
                    L_0x002c:
                        r1.mo12045c();
                    L_0x002f:
                        if (r0 == 0) goto L_0x0034;
                    L_0x0031:
                        r0.mo12045c();
                    L_0x0034:
                        return;
                    L_0x0035:
                        r1 = move-exception;
                        r2 = r0;
                        r3 = r0;
                        r6 = r1;
                        r1 = r0;
                        r0 = r6;
                    L_0x003b:
                        r4 = "Log";
                        r5 = "processLog";
                        com.amap.api.services.core.C1099ax.m4800a(r0, r4, r5);	 Catch:{ all -> 0x0090 }
                        r0.printStackTrace();	 Catch:{ all -> 0x0090 }
                        if (r3 == 0) goto L_0x004a;
                    L_0x0047:
                        r3.mo12045c();
                    L_0x004a:
                        if (r2 == 0) goto L_0x004f;
                    L_0x004c:
                        r2.mo12045c();
                    L_0x004f:
                        if (r1 == 0) goto L_0x0034;
                    L_0x0051:
                        r1.mo12045c();
                        goto L_0x0034;
                    L_0x0055:
                        r1 = move-exception;
                        r2 = r0;
                        r3 = r0;
                        r6 = r1;
                        r1 = r0;
                        r0 = r6;
                    L_0x005b:
                        if (r3 == 0) goto L_0x0060;
                    L_0x005d:
                        r3.mo12045c();
                    L_0x0060:
                        if (r2 == 0) goto L_0x0065;
                    L_0x0062:
                        r2.mo12045c();
                    L_0x0065:
                        if (r1 == 0) goto L_0x006a;
                    L_0x0067:
                        r1.mo12045c();
                    L_0x006a:
                        throw r0;
                    L_0x006b:
                        r1 = move-exception;
                        r1 = r0;
                        r2 = r0;
                    L_0x006e:
                        if (r2 == 0) goto L_0x0073;
                    L_0x0070:
                        r2.mo12045c();
                    L_0x0073:
                        if (r1 == 0) goto L_0x0078;
                    L_0x0075:
                        r1.mo12045c();
                    L_0x0078:
                        if (r0 == 0) goto L_0x0034;
                    L_0x007a:
                        goto L_0x0031;
                    L_0x007b:
                        r1 = move-exception;
                        r3 = r2;
                        r2 = r0;
                        r6 = r0;
                        r0 = r1;
                        r1 = r6;
                        goto L_0x005b;
                    L_0x0082:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x005b;
                    L_0x0089:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x005b;
                    L_0x0090:
                        r0 = move-exception;
                        goto L_0x005b;
                    L_0x0092:
                        r1 = move-exception;
                        r3 = r2;
                        r2 = r0;
                        r6 = r0;
                        r0 = r1;
                        r1 = r6;
                        goto L_0x003b;
                    L_0x0099:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x003b;
                    L_0x00a0:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x003b;
                    L_0x00a7:
                        r1 = move-exception;
                        r1 = r0;
                        goto L_0x006e;
                    L_0x00aa:
                        r3 = move-exception;
                        goto L_0x006e;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1107be$C11062.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "Log", "processLog");
            th.printStackTrace();
        }
    }
}
