package com.baidu.android.pushservice.p028a.p029a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.baidu.android.pushservice.a.a.b */
class C1309b implements C1308c {
    /* renamed from: a */
    private String f4659a;
    /* renamed from: b */
    private C1308c f4660b;
    /* renamed from: c */
    private int f4661c;
    /* renamed from: d */
    private int f4662d;
    /* renamed from: e */
    private Map<String, Integer> f4663e = new HashMap();

    public C1309b(String str, int i, int i2, C1308c c1308c) {
        this.f4659a = str;
        this.f4661c = i;
        this.f4662d = i2;
        this.f4660b = c1308c;
    }

    /* renamed from: a */
    public Bitmap mo13558a(String str) {
        if (!mo13559b(str)) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactoryInstrumentation.decodeFile(mo13560c(str), options);
        options.inSampleSize = C1307a.m5907a(options, -1, this.f4662d);
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactoryInstrumentation.decodeFile(mo13560c(str), options);
        if (decodeFile == null) {
            return null;
        }
        Integer num = (Integer) this.f4663e.get(str);
        if (num == null) {
            num = Integer.valueOf(0);
        }
        if (num.intValue() + 1 < this.f4661c || this.f4660b == null) {
            this.f4663e.put(str, Integer.valueOf(num.intValue() + 1));
            return decodeFile;
        }
        this.f4660b.mo13557a(str, decodeFile);
        this.f4663e.remove(str);
        return decodeFile;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c A:{SYNTHETIC, Splitter:B:16:0x003c} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045 A:{SYNTHETIC, Splitter:B:21:0x0045} */
    /* renamed from: a */
    public void mo13557a(java.lang.String r5, android.graphics.Bitmap r6) {
        /*
        r4 = this;
        r2 = new java.io.File;
        r0 = r4.mo13560c(r5);
        r2.<init>(r0);
        r0 = r2.getParentFile();
        if (r0 == 0) goto L_0x0018;
    L_0x000f:
        r1 = r0.exists();
        if (r1 != 0) goto L_0x0018;
    L_0x0015:
        r0.mkdirs();
    L_0x0018:
        r1 = 0;
        r0 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0038, all -> 0x0042 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0038, all -> 0x0042 }
        r1 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        r2 = 100;
        r6.compress(r1, r2, r0);	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        r0.flush();	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        r1 = r4.f4663e;	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        r2 = 1;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        r1.put(r5, r2);	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        if (r0 == 0) goto L_0x0037;
    L_0x0034:
        r0.close();	 Catch:{ IOException -> 0x0049 }
    L_0x0037:
        return;
    L_0x0038:
        r0 = move-exception;
        r0 = r1;
    L_0x003a:
        if (r0 == 0) goto L_0x0037;
    L_0x003c:
        r0.close();	 Catch:{ IOException -> 0x0040 }
        goto L_0x0037;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0037;
    L_0x0042:
        r0 = move-exception;
    L_0x0043:
        if (r1 == 0) goto L_0x0048;
    L_0x0045:
        r1.close();	 Catch:{ IOException -> 0x004b }
    L_0x0048:
        throw r0;
    L_0x0049:
        r0 = move-exception;
        goto L_0x0037;
    L_0x004b:
        r1 = move-exception;
        goto L_0x0048;
    L_0x004d:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0043;
    L_0x0052:
        r1 = move-exception;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p028a.p029a.C1309b.mo13557a(java.lang.String, android.graphics.Bitmap):void");
    }

    /* renamed from: b */
    public boolean mo13559b(String str) {
        return new File(mo13560c(str)).exists();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public String mo13560c(String str) {
        return this.f4659a + "/" + str + ".png";
    }
}
