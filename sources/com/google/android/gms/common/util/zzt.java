package com.google.android.gms.common.util;

import android.os.Binder;
import android.os.Process;

public class zzt {
    private static String zzauo = null;

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060 A:{SYNTHETIC, Splitter:B:23:0x0060} */
    private static java.lang.String zzcz(int r5) {
        /*
        r0 = 0;
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r1 = new java.io.FileReader;	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r4 = 25;
        r3.<init>(r4);	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r4 = "/proc/";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r3 = r3.append(r5);	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r4 = "/cmdline";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r3 = r3.toString();	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r1.<init>(r3);	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r2.<init>(r1);	 Catch:{ IOException -> 0x003f, all -> 0x005b }
        r1 = r2.readLine();	 Catch:{ IOException -> 0x0071 }
        r0 = r1.trim();	 Catch:{ IOException -> 0x0071 }
        if (r2 == 0) goto L_0x0033;
    L_0x0030:
        r2.close();	 Catch:{ Exception -> 0x0034 }
    L_0x0033:
        return r0;
    L_0x0034:
        r1 = move-exception;
        r2 = "ProcessUtils";
        r3 = r1.getMessage();
        android.util.Log.w(r2, r3, r1);
        goto L_0x0033;
    L_0x003f:
        r1 = move-exception;
        r2 = r0;
    L_0x0041:
        r3 = "ProcessUtils";
        r4 = r1.getMessage();	 Catch:{ all -> 0x006f }
        android.util.Log.e(r3, r4, r1);	 Catch:{ all -> 0x006f }
        if (r2 == 0) goto L_0x0033;
    L_0x004c:
        r2.close();	 Catch:{ Exception -> 0x0050 }
        goto L_0x0033;
    L_0x0050:
        r1 = move-exception;
        r2 = "ProcessUtils";
        r3 = r1.getMessage();
        android.util.Log.w(r2, r3, r1);
        goto L_0x0033;
    L_0x005b:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x005e:
        if (r2 == 0) goto L_0x0063;
    L_0x0060:
        r2.close();	 Catch:{ Exception -> 0x0064 }
    L_0x0063:
        throw r0;
    L_0x0064:
        r1 = move-exception;
        r2 = "ProcessUtils";
        r3 = r1.getMessage();
        android.util.Log.w(r2, r3, r1);
        goto L_0x0063;
    L_0x006f:
        r0 = move-exception;
        goto L_0x005e;
    L_0x0071:
        r1 = move-exception;
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.zzt.zzcz(int):java.lang.String");
    }

    public static String zzvi() {
        return zzcz(Binder.getCallingPid());
    }

    public static String zzvj() {
        if (zzauo == null) {
            zzauo = zzcz(Process.myPid());
        }
        return zzauo;
    }
}
