package com.aps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import java.io.File;

/* renamed from: com.aps.az */
public final class C1238az {
    /* renamed from: a */
    private Context f4294a = null;
    /* renamed from: b */
    private boolean f4295b = true;
    /* renamed from: c */
    private int f4296c = 1270;
    /* renamed from: d */
    private int f4297d = 310;
    /* renamed from: e */
    private int f4298e = 4;
    /* renamed from: f */
    private int f4299f = 200;
    /* renamed from: g */
    private int f4300g = 1;
    /* renamed from: h */
    private int f4301h = 0;
    /* renamed from: i */
    private int f4302i = 0;
    /* renamed from: j */
    private long f4303j = 0;
    /* renamed from: k */
    private C1237ay f4304k = null;

    private C1238az(Context context) {
        this.f4294a = context;
    }

    /* renamed from: a */
    private static int m5430a(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 4) {
            i3 += (bArr[i2 + i] & 255) << (i2 << 3);
            i2++;
        }
        return i3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x005a A:{SYNTHETIC, Splitter:B:11:0x005a} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c3 A:{SYNTHETIC, Splitter:B:32:0x00c3} */
    /* renamed from: a */
    protected static com.aps.C1238az m5431a(android.content.Context r13) {
        /*
        r2 = 1;
        r10 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r3 = 0;
        r4 = new com.aps.az;
        r4.<init>(r13);
        r4.f4301h = r3;
        r4.f4302i = r3;
        r0 = java.lang.System.currentTimeMillis();
        r6 = 28800000; // 0x1b77400 float:6.7390035E-38 double:1.42290906E-316;
        r0 = r0 + r6;
        r0 = r0 / r10;
        r0 = r0 * r10;
        r4.f4303j = r0;
        r1 = 0;
        r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r5 = new java.io.File;	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6.<init>();	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r7 = com.aps.C1238az.m5434b(r13);	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r7 = java.io.File.separator;	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r7 = "data_carrier_status";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r5.<init>(r6);	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r0.<init>(r5);	 Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r5 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r5.<init>();	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 32;
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
    L_0x004b:
        r6 = r0.read(r1);	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r7 = -1;
        if (r6 == r7) goto L_0x005e;
    L_0x0052:
        r7 = 0;
        r5.write(r1, r7, r6);	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        goto L_0x004b;
    L_0x0057:
        r1 = move-exception;
    L_0x0058:
        if (r0 == 0) goto L_0x005d;
    L_0x005a:
        r0.close();	 Catch:{ Exception -> 0x00c7 }
    L_0x005d:
        return r4;
    L_0x005e:
        r5.flush();	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r6 = r5.toByteArray();	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        if (r6 == 0) goto L_0x00b5;
    L_0x0067:
        r1 = r6.length;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r7 = 22;
        if (r1 < r7) goto L_0x00b5;
    L_0x006c:
        r1 = 0;
        r1 = r6[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        if (r1 == 0) goto L_0x00be;
    L_0x0071:
        r1 = r2;
    L_0x0072:
        r4.f4295b = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 1;
        r1 = r6[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = r1 * 10;
        r1 = r1 << 10;
        r4.f4296c = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 2;
        r1 = r6[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = r1 * 10;
        r1 = r1 << 10;
        r4.f4297d = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 3;
        r1 = r6[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.f4298e = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 4;
        r1 = r6[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = r1 * 10;
        r4.f4299f = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 5;
        r1 = r6[r1];	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.f4300g = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 14;
        r2 = com.aps.C1238az.m5433b(r6, r1);	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r8 = r4.f4303j;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r8 = r8 - r2;
        r1 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r1 >= 0) goto L_0x00b5;
    L_0x00a4:
        r4.f4303j = r2;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 6;
        r1 = com.aps.C1238az.m5430a(r6, r1);	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.f4301h = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 10;
        r1 = com.aps.C1238az.m5430a(r6, r1);	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.f4302i = r1;	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
    L_0x00b5:
        r5.close();	 Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r0.close();	 Catch:{ Exception -> 0x00bc }
        goto L_0x005d;
    L_0x00bc:
        r0 = move-exception;
        goto L_0x005d;
    L_0x00be:
        r1 = r3;
        goto L_0x0072;
    L_0x00c0:
        r0 = move-exception;
    L_0x00c1:
        if (r1 == 0) goto L_0x00c6;
    L_0x00c3:
        r1.close();	 Catch:{ Exception -> 0x00c9 }
    L_0x00c6:
        throw r0;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x005d;
    L_0x00c9:
        r1 = move-exception;
        goto L_0x00c6;
    L_0x00cb:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x00c1;
    L_0x00d0:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.C1238az.m5431a(android.content.Context):com.aps.az");
    }

    /* renamed from: a */
    private static byte[] m5432a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((int) ((j >> (i << 3)) & 255));
        }
        return bArr;
    }

    /* renamed from: b */
    private static long m5433b(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 8) {
            i3 += (bArr[i2 + 14] & 255) << (i2 << 3);
            i2++;
        }
        return (long) i3;
    }

    /* renamed from: b */
    private static String m5434b(Context context) {
        boolean z = false;
        File file = null;
        if (Process.myUid() != 1000) {
            file = C1222aj.m5334a(context);
        }
        try {
            z = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
        }
        return ((z || !C1222aj.m5343c()) && file != null) ? file.getPath() : context.getFilesDir().getPath();
    }

    /* renamed from: c */
    private static byte[] m5435c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (i2 << 3));
        }
        return bArr;
    }

    /* renamed from: g */
    private void m5436g() {
        long currentTimeMillis = System.currentTimeMillis() + 28800000;
        if (currentTimeMillis - this.f4303j > 86400000) {
            this.f4303j = (currentTimeMillis / 86400000) * 86400000;
            this.f4301h = 0;
            this.f4302i = 0;
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo13153a(int i) {
        m5436g();
        if (i < 0) {
            i = 0;
        }
        this.f4301h = i;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo13154a(C1237ay c1237ay) {
        this.f4304k = c1237ay;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final boolean mo13155a() {
        m5436g();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f4294a.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? this.f4295b : activeNetworkInfo.getType() == 1 ? this.f4295b && this.f4301h < this.f4296c : this.f4295b && this.f4302i < this.f4297d;
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x016e A:{SYNTHETIC, Splitter:B:39:0x016e} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0178 A:{SYNTHETIC, Splitter:B:45:0x0178} */
    /* renamed from: a */
    public final boolean mo13156a(java.lang.String r11) {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0163 }
        r3 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r11);	 Catch:{ Exception -> 0x0163 }
        r0 = "e";
        r0 = r3.has(r0);	 Catch:{ Exception -> 0x0163 }
        if (r0 == 0) goto L_0x001b;
    L_0x0010:
        r0 = "e";
        r0 = r3.getInt(r0);	 Catch:{ Exception -> 0x0163 }
        if (r0 == 0) goto L_0x015d;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        r10.f4295b = r0;	 Catch:{ Exception -> 0x0163 }
    L_0x001b:
        r0 = "d";
        r0 = r3.has(r0);	 Catch:{ Exception -> 0x0163 }
        if (r0 == 0) goto L_0x0066;
    L_0x0023:
        r0 = "d";
        r0 = r3.getInt(r0);	 Catch:{ Exception -> 0x0163 }
        r4 = r0 & 127;
        r4 = r4 * 10;
        r4 = r4 << 10;
        r10.f4296c = r4;	 Catch:{ Exception -> 0x0163 }
        r4 = r0 & 3968;
        r4 = r4 >> 7;
        r4 = r4 * 10;
        r4 = r4 << 10;
        r10.f4297d = r4;	 Catch:{ Exception -> 0x0163 }
        r4 = 520192; // 0x7f000 float:7.28944E-40 double:2.57009E-318;
        r4 = r4 & r0;
        r4 = r4 >> 12;
        r10.f4298e = r4;	 Catch:{ Exception -> 0x0163 }
        r4 = 66584576; // 0x3f80000 float:1.457613E-36 double:3.28971515E-316;
        r4 = r4 & r0;
        r4 = r4 >> 19;
        r4 = r4 * 10;
        r10.f4299f = r4;	 Catch:{ Exception -> 0x0163 }
        r4 = 2080374784; // 0x7c000000 float:2.658456E36 double:1.0278417112E-314;
        r0 = r0 & r4;
        r0 = r0 >> 26;
        r10.f4300g = r0;	 Catch:{ Exception -> 0x0163 }
        r0 = r10.f4300g;	 Catch:{ Exception -> 0x0163 }
        r4 = 31;
        if (r0 != r4) goto L_0x005d;
    L_0x0059:
        r0 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r10.f4300g = r0;	 Catch:{ Exception -> 0x0163 }
    L_0x005d:
        r0 = r10.f4304k;	 Catch:{ Exception -> 0x0163 }
        if (r0 == 0) goto L_0x0066;
    L_0x0061:
        r0 = r10.f4304k;	 Catch:{ Exception -> 0x0163 }
        r0.mo13152a();	 Catch:{ Exception -> 0x0163 }
    L_0x0066:
        r0 = "u";
        r0 = r3.has(r0);	 Catch:{ Exception -> 0x0163 }
        if (r0 == 0) goto L_0x0185;
    L_0x006f:
        r0 = "u";
        r0 = r3.getInt(r0);	 Catch:{ Exception -> 0x0163 }
        if (r0 == 0) goto L_0x0160;
    L_0x0078:
        r0 = r1;
    L_0x0079:
        r4 = 0;
        r10.m5436g();	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r5 = new java.io.File;	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r6.<init>();	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r7 = r10.f4294a;	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r7 = com.aps.C1238az.m5434b(r7);	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r7 = java.io.File.separator;	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r7 = "data_carrier_status";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r5.<init>(r6);	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r3.<init>(r5);	 Catch:{ Exception -> 0x016a, all -> 0x0174 }
        r4 = r10.f4301h;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r4 = com.aps.C1238az.m5435c(r4);	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r5 = r10.f4302i;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r5 = com.aps.C1238az.m5435c(r5);	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r6 = r10.f4303j;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r6 = com.aps.C1238az.m5432a(r6);	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7 = 22;
        r7 = new byte[r7];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r8 = 0;
        r9 = r10.f4295b;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        if (r9 == 0) goto L_0x0167;
    L_0x00c1:
        r1 = (byte) r1;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r8] = r1;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 1;
        r2 = r10.f4296c;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r2 = r2 / 10240;
        r2 = (byte) r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 2;
        r2 = r10.f4297d;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r2 = r2 / 10240;
        r2 = (byte) r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 3;
        r2 = r10.f4298e;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r2 = (byte) r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 4;
        r2 = r10.f4299f;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r2 = r2 / 10;
        r2 = (byte) r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 5;
        r2 = r10.f4300g;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r2 = (byte) r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 6;
        r2 = 0;
        r2 = r4[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 7;
        r2 = 1;
        r2 = r4[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 8;
        r2 = 2;
        r2 = r4[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 9;
        r2 = 3;
        r2 = r4[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 10;
        r2 = 0;
        r2 = r5[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 11;
        r2 = 1;
        r2 = r5[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 12;
        r2 = 2;
        r2 = r5[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 13;
        r2 = 3;
        r2 = r5[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 14;
        r2 = 0;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 15;
        r2 = 1;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 16;
        r2 = 2;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 17;
        r2 = 3;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 18;
        r2 = 4;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 19;
        r2 = 5;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 20;
        r2 = 6;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r1 = 21;
        r2 = 7;
        r2 = r6[r2];	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r7[r1] = r2;	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r3.write(r7);	 Catch:{ Exception -> 0x0182, all -> 0x0180 }
        r3.close();	 Catch:{ Exception -> 0x017c }
    L_0x015c:
        return r0;
    L_0x015d:
        r0 = r2;
        goto L_0x0019;
    L_0x0160:
        r0 = r2;
        goto L_0x0079;
    L_0x0163:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0079;
    L_0x0167:
        r1 = r2;
        goto L_0x00c1;
    L_0x016a:
        r1 = move-exception;
        r1 = r4;
    L_0x016c:
        if (r1 == 0) goto L_0x015c;
    L_0x016e:
        r1.close();	 Catch:{ Exception -> 0x0172 }
        goto L_0x015c;
    L_0x0172:
        r1 = move-exception;
        goto L_0x015c;
    L_0x0174:
        r0 = move-exception;
        r3 = r4;
    L_0x0176:
        if (r3 == 0) goto L_0x017b;
    L_0x0178:
        r3.close();	 Catch:{ Exception -> 0x017e }
    L_0x017b:
        throw r0;
    L_0x017c:
        r1 = move-exception;
        goto L_0x015c;
    L_0x017e:
        r1 = move-exception;
        goto L_0x017b;
    L_0x0180:
        r0 = move-exception;
        goto L_0x0176;
    L_0x0182:
        r1 = move-exception;
        r1 = r3;
        goto L_0x016c;
    L_0x0185:
        r0 = r2;
        goto L_0x0079;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.C1238az.mo13156a(java.lang.String):boolean");
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final int mo13157b() {
        return this.f4298e;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final void mo13158b(int i) {
        m5436g();
        if (i < 0) {
            i = 0;
        }
        this.f4302i = i;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: c */
    public final int mo13159c() {
        return this.f4299f;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: d */
    public final int mo13160d() {
        return this.f4300g;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: e */
    public final int mo13161e() {
        m5436g();
        return this.f4301h;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: f */
    public final int mo13162f() {
        m5436g();
        return this.f4302i;
    }
}
