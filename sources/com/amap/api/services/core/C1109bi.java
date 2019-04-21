package com.amap.api.services.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: Utils */
/* renamed from: com.amap.api.services.core.bi */
public class C1109bi {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003f A:{SYNTHETIC, Splitter:B:24:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044 A:{SYNTHETIC, Splitter:B:27:0x0044} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0056 A:{SYNTHETIC, Splitter:B:38:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005b A:{SYNTHETIC, Splitter:B:41:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003f A:{SYNTHETIC, Splitter:B:24:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044 A:{SYNTHETIC, Splitter:B:27:0x0044} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0056 A:{SYNTHETIC, Splitter:B:38:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005b A:{SYNTHETIC, Splitter:B:41:0x005b} */
    /* renamed from: a */
    static synchronized java.lang.String m4886a(java.lang.Throwable r7) {
        /*
        r0 = 0;
        r4 = com.amap.api.services.core.C1109bi.class;
        monitor-enter(r4);
        r3 = new java.io.StringWriter;	 Catch:{ Throwable -> 0x0037, all -> 0x0050 }
        r3.<init>();	 Catch:{ Throwable -> 0x0037, all -> 0x0050 }
        r2 = new java.io.PrintWriter;	 Catch:{ Throwable -> 0x007e, all -> 0x0078 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x007e, all -> 0x0078 }
        r7.printStackTrace(r2);	 Catch:{ Throwable -> 0x0081 }
        r1 = r7.getCause();	 Catch:{ Throwable -> 0x0081 }
    L_0x0015:
        if (r1 == 0) goto L_0x001f;
    L_0x0017:
        r1.printStackTrace(r2);	 Catch:{ Throwable -> 0x0081 }
        r1 = r1.getCause();	 Catch:{ Throwable -> 0x0081 }
        goto L_0x0015;
    L_0x001f:
        r1 = r3.toString();	 Catch:{ Throwable -> 0x0081 }
        r5 = "\n";
        r6 = "<br/>";
        r0 = r1.replaceAll(r5, r6);	 Catch:{ Throwable -> 0x0081 }
        if (r3 == 0) goto L_0x0030;
    L_0x002d:
        r3.close();	 Catch:{ Throwable -> 0x006e }
    L_0x0030:
        if (r2 == 0) goto L_0x0035;
    L_0x0032:
        r2.close();	 Catch:{ Throwable -> 0x0073 }
    L_0x0035:
        monitor-exit(r4);
        return r0;
    L_0x0037:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x003a:
        r1.printStackTrace();	 Catch:{ all -> 0x007c }
        if (r3 == 0) goto L_0x0042;
    L_0x003f:
        r3.close();	 Catch:{ Throwable -> 0x0069 }
    L_0x0042:
        if (r2 == 0) goto L_0x0035;
    L_0x0044:
        r2.close();	 Catch:{ Throwable -> 0x0048 }
        goto L_0x0035;
    L_0x0048:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x004d }
        goto L_0x0035;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0050:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0054:
        if (r3 == 0) goto L_0x0059;
    L_0x0056:
        r3.close();	 Catch:{ Throwable -> 0x005f }
    L_0x0059:
        if (r2 == 0) goto L_0x005e;
    L_0x005b:
        r2.close();	 Catch:{ Throwable -> 0x0064 }
    L_0x005e:
        throw r0;	 Catch:{ all -> 0x004d }
    L_0x005f:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x004d }
        goto L_0x0059;
    L_0x0064:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x004d }
        goto L_0x005e;
    L_0x0069:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x004d }
        goto L_0x0042;
    L_0x006e:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x004d }
        goto L_0x0030;
    L_0x0073:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x004d }
        goto L_0x0035;
    L_0x0078:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0054;
    L_0x007c:
        r0 = move-exception;
        goto L_0x0054;
    L_0x007e:
        r1 = move-exception;
        r2 = r0;
        goto L_0x003a;
    L_0x0081:
        r1 = move-exception;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.C1109bi.m4886a(java.lang.Throwable):java.lang.String");
    }

    /* renamed from: a */
    public static String m4885a(long j) {
        String str = null;
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }
}
