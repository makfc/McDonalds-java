package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;

/* compiled from: AnrLogProcessor */
/* renamed from: com.amap.api.mapcore2d.de */
public class C0993de extends C0991dh {
    /* renamed from: a */
    private static boolean f2828a = true;
    /* renamed from: b */
    private String[] f2829b = new String[10];
    /* renamed from: c */
    private int f2830c = 0;
    /* renamed from: d */
    private boolean f2831d = false;
    /* renamed from: e */
    private int f2832e = 0;

    protected C0993de(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo10197a(Context context) {
        if (C0968cq.m3970m(context) != 1 || !f2828a) {
            return false;
        }
        f2828a = false;
        synchronized (Looper.getMainLooper()) {
            C1009du c1009du = new C1009du(context);
            C1010dv a = c1009du.mo10234a();
            if (a == null) {
                return true;
            } else if (a.mo10241c()) {
                a.mo10240c(false);
                c1009du.mo10235a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00d2 A:{SYNTHETIC, Splitter:B:78:0x00d2} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00d7 A:{SYNTHETIC, Splitter:B:81:0x00d7} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a0 A:{SYNTHETIC, Splitter:B:55:0x00a0} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a5 A:{SYNTHETIC, Splitter:B:58:0x00a5} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00be A:{SYNTHETIC, Splitter:B:68:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00c3 A:{SYNTHETIC, Splitter:B:71:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00be A:{SYNTHETIC, Splitter:B:68:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00c3 A:{SYNTHETIC, Splitter:B:71:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00d2 A:{SYNTHETIC, Splitter:B:78:0x00d2} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00d7 A:{SYNTHETIC, Splitter:B:81:0x00d7} */
    /* renamed from: a */
    public java.lang.String mo10193a(java.util.List<com.amap.api.mapcore2d.C0977cv> r10) {
        /*
        r9 = this;
        r5 = 0;
        r1 = 0;
        r0 = 0;
        r2 = 0;
        r4 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x018e, IOException -> 0x00b2, all -> 0x00cd }
        r3 = "/data/anr/traces.txt";
        r4.<init>(r3);	 Catch:{ FileNotFoundException -> 0x018e, IOException -> 0x00b2, all -> 0x00cd }
        r3 = r4.exists();	 Catch:{ FileNotFoundException -> 0x018e, IOException -> 0x00b2, all -> 0x00cd }
        if (r3 != 0) goto L_0x001d;
    L_0x0011:
        if (r1 == 0) goto L_0x0016;
    L_0x0013:
        r2.close();	 Catch:{ IOException -> 0x0136, Throwable -> 0x0140 }
    L_0x0016:
        if (r1 == 0) goto L_0x001b;
    L_0x0018:
        r0.close();	 Catch:{ IOException -> 0x014a, Throwable -> 0x0154 }
    L_0x001b:
        r0 = r1;
    L_0x001c:
        return r0;
    L_0x001d:
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x018e, IOException -> 0x00b2, all -> 0x00cd }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x018e, IOException -> 0x00b2, all -> 0x00cd }
        r2 = new com.amap.api.mapcore2d.dy;	 Catch:{ FileNotFoundException -> 0x0193, IOException -> 0x0187, all -> 0x0180 }
        r0 = com.amap.api.mapcore2d.C1020dz.f2893a;	 Catch:{ FileNotFoundException -> 0x0193, IOException -> 0x0187, all -> 0x0180 }
        r2.<init>(r3, r0);	 Catch:{ FileNotFoundException -> 0x0193, IOException -> 0x0187, all -> 0x0180 }
        r4 = r5;
    L_0x002a:
        r0 = r2.mo10266a();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r6 = "pid";
        r6 = r0.contains(r6);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        if (r6 == 0) goto L_0x019b;
    L_0x0036:
        r4 = "\"main\"";
        r4 = r0.contains(r4);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        if (r4 != 0) goto L_0x0043;
    L_0x003e:
        r0 = r2.mo10266a();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        goto L_0x0036;
    L_0x0043:
        r4 = 1;
        r6 = r0;
        r0 = r4;
    L_0x0046:
        r4 = "";
        r4 = r6.equals(r4);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        if (r4 == 0) goto L_0x0198;
    L_0x004e:
        r4 = r5;
    L_0x004f:
        if (r4 == 0) goto L_0x002a;
    L_0x0051:
        r9.m4136b(r6);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r0 = r9.f2832e;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r7 = 5;
        if (r0 != r7) goto L_0x006c;
    L_0x0059:
        if (r2 == 0) goto L_0x005e;
    L_0x005b:
        r2.close();	 Catch:{ IOException -> 0x015e, Throwable -> 0x0168 }
    L_0x005e:
        if (r3 == 0) goto L_0x0063;
    L_0x0060:
        r3.close();	 Catch:{ IOException -> 0x0172, Throwable -> 0x0179 }
    L_0x0063:
        r0 = r9.f2831d;
        if (r0 == 0) goto L_0x00db;
    L_0x0067:
        r0 = r9.m4137d();
        goto L_0x001c;
    L_0x006c:
        r0 = r9.f2831d;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        if (r0 != 0) goto L_0x0094;
    L_0x0070:
        r7 = r10.iterator();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
    L_0x0074:
        r0 = r7.hasNext();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        if (r0 == 0) goto L_0x002a;
    L_0x007a:
        r0 = r7.next();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r0 = (com.amap.api.mapcore2d.C0977cv) r0;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r8 = r0.mo10177e();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r8 = com.amap.api.mapcore2d.C0991dh.m4111a(r8, r6);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r9.f2831d = r8;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r8 = r9.f2831d;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        if (r8 == 0) goto L_0x0074;
    L_0x008e:
        r9.mo10195a(r0);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        goto L_0x0074;
    L_0x0092:
        r0 = move-exception;
        goto L_0x0059;
    L_0x0094:
        r0 = r9.f2832e;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        r0 = r0 + 1;
        r9.f2832e = r0;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x018b }
        goto L_0x002a;
    L_0x009b:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
    L_0x009e:
        if (r0 == 0) goto L_0x00a3;
    L_0x00a0:
        r0.close();	 Catch:{ IOException -> 0x00de, Throwable -> 0x00e7 }
    L_0x00a3:
        if (r2 == 0) goto L_0x0063;
    L_0x00a5:
        r2.close();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00f0 }
        goto L_0x0063;
    L_0x00a9:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
    L_0x00ae:
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x0063;
    L_0x00b2:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00b5:
        r4 = "ANRWriter";
        r5 = "initLog";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r4, r5);	 Catch:{ all -> 0x0184 }
        if (r2 == 0) goto L_0x00c1;
    L_0x00be:
        r2.close();	 Catch:{ IOException -> 0x011e, Throwable -> 0x0127 }
    L_0x00c1:
        if (r3 == 0) goto L_0x0063;
    L_0x00c3:
        r3.close();	 Catch:{ IOException -> 0x00c7, Throwable -> 0x0130 }
        goto L_0x0063;
    L_0x00c7:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        goto L_0x00ae;
    L_0x00cd:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00d0:
        if (r2 == 0) goto L_0x00d5;
    L_0x00d2:
        r2.close();	 Catch:{ IOException -> 0x00fa, Throwable -> 0x0103 }
    L_0x00d5:
        if (r3 == 0) goto L_0x00da;
    L_0x00d7:
        r3.close();	 Catch:{ IOException -> 0x010c, Throwable -> 0x0115 }
    L_0x00da:
        throw r0;
    L_0x00db:
        r0 = r1;
        goto L_0x001c;
    L_0x00de:
        r0 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r3, r4);
        goto L_0x00a3;
    L_0x00e7:
        r0 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r3, r4);
        goto L_0x00a3;
    L_0x00f0:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
    L_0x00f5:
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x0063;
    L_0x00fa:
        r1 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r4);
        goto L_0x00d5;
    L_0x0103:
        r1 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r4);
        goto L_0x00d5;
    L_0x010c:
        r1 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x00da;
    L_0x0115:
        r1 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x00da;
    L_0x011e:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r4);
        goto L_0x00c1;
    L_0x0127:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r4);
        goto L_0x00c1;
    L_0x0130:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        goto L_0x00f5;
    L_0x0136:
        r2 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r3, r4);
        goto L_0x0016;
    L_0x0140:
        r2 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.mapcore2d.C0982da.m4076a(r2, r3, r4);
        goto L_0x0016;
    L_0x014a:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x001b;
    L_0x0154:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
        goto L_0x001b;
    L_0x015e:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r4);
        goto L_0x005e;
    L_0x0168:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r4);
        goto L_0x005e;
    L_0x0172:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        goto L_0x00ae;
    L_0x0179:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        goto L_0x00f5;
    L_0x0180:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00d0;
    L_0x0184:
        r0 = move-exception;
        goto L_0x00d0;
    L_0x0187:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00b5;
    L_0x018b:
        r0 = move-exception;
        goto L_0x00b5;
    L_0x018e:
        r0 = move-exception;
        r0 = r1;
        r2 = r1;
        goto L_0x009e;
    L_0x0193:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
        goto L_0x009e;
    L_0x0198:
        r4 = r0;
        goto L_0x004f;
    L_0x019b:
        r6 = r0;
        r0 = r4;
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0993de.mo10193a(java.util.List):java.lang.String");
    }

    /* renamed from: d */
    private String m4137d() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.f2830c;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.f2829b[i]);
                i++;
            }
            for (i = 0; i < this.f2830c; i++) {
                stringBuilder.append(this.f2829b[i]);
            }
        } catch (Throwable th) {
            C0982da.m4076a(th, "ANRWriter", "getLogInfo");
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private void m4136b(String str) {
        try {
            if (this.f2830c > 9) {
                this.f2830c = 0;
            }
            this.f2829b[this.f2830c] = str;
            this.f2830c++;
        } catch (Throwable th) {
            C0982da.m4076a(th, "ANRWriter", "addData");
        }
    }
}
