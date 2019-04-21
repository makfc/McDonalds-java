package com.baidu.android.pushservice.util;

import java.io.IOException;
import java.util.Properties;

/* renamed from: com.baidu.android.pushservice.util.b */
public class C1534b {
    /* renamed from: a */
    private final Properties f5372a = new Properties();

    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036 A:{SYNTHETIC, Splitter:B:15:0x0036} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044 A:{SYNTHETIC, Splitter:B:21:0x0044} */
    private C1534b() throws java.io.IOException {
        /*
        r5 = this;
        r5.<init>();
        r0 = new java.util.Properties;
        r0.<init>();
        r5.f5372a = r0;
        r2 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x002d, all -> 0x0041 }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x002d, all -> 0x0041 }
        r3 = android.os.Environment.getRootDirectory();	 Catch:{ Exception -> 0x002d, all -> 0x0041 }
        r4 = "build.prop";
        r0.<init>(r3, r4);	 Catch:{ Exception -> 0x002d, all -> 0x0041 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x002d, all -> 0x0041 }
        r0 = r5.f5372a;	 Catch:{ Exception -> 0x0052 }
        r0.load(r1);	 Catch:{ Exception -> 0x0052 }
        if (r1 == 0) goto L_0x0025;
    L_0x0022:
        r1.close();	 Catch:{ Exception -> 0x0026 }
    L_0x0025:
        return;
    L_0x0026:
        r0 = move-exception;
        r1 = "BuildProperties";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);
        goto L_0x0025;
    L_0x002d:
        r0 = move-exception;
        r1 = r2;
    L_0x002f:
        r2 = "BuildProperties";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);	 Catch:{ all -> 0x004f }
        if (r1 == 0) goto L_0x0025;
    L_0x0036:
        r1.close();	 Catch:{ Exception -> 0x003a }
        goto L_0x0025;
    L_0x003a:
        r0 = move-exception;
        r1 = "BuildProperties";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);
        goto L_0x0025;
    L_0x0041:
        r0 = move-exception;
    L_0x0042:
        if (r2 == 0) goto L_0x0047;
    L_0x0044:
        r2.close();	 Catch:{ Exception -> 0x0048 }
    L_0x0047:
        throw r0;
    L_0x0048:
        r1 = move-exception;
        r2 = "BuildProperties";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x0047;
    L_0x004f:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0042;
    L_0x0052:
        r0 = move-exception;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1534b.<init>():void");
    }

    /* renamed from: a */
    public static C1534b m6898a() throws IOException {
        return new C1534b();
    }

    /* renamed from: a */
    public String mo14059a(String str, String str2) {
        return this.f5372a.getProperty(str, str2);
    }
}
