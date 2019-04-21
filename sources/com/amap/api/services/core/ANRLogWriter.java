package com.amap.api.services.core;

/* renamed from: com.amap.api.services.core.az */
class ANRLogWriter extends LogWriter {
    /* renamed from: a */
    private String[] f3702a = new String[10];
    /* renamed from: b */
    private int f3703b = 0;
    /* renamed from: c */
    private boolean f3704c = false;
    /* renamed from: d */
    private int f3705d = 0;
    /* renamed from: e */
    private C1102a f3706e;

    /* compiled from: ANRLogWriter */
    /* renamed from: com.amap.api.services.core.az$a */
    private class C1102a implements C1101bm {
        /* renamed from: b */
        private C1085aj f3700b;

        private C1102a(C1085aj c1085aj) {
            this.f3700b = c1085aj;
        }

        /* renamed from: a */
        public void mo12046a(String str) {
            try {
                this.f3700b.mo12010b(str, ANRLogWriter.this.mo12047a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    ANRLogWriter() {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo12047a() {
        return 2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo12056b() {
        return C1107be.f3727d;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12049a(String str) {
        return C1077aa.m4684b(str);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public C1101bm mo12048a(C1085aj c1085aj) {
        try {
            if (this.f3706e == null) {
                this.f3706e = new C1102a(c1085aj);
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "ANRWriter", "getListener");
            th.printStackTrace();
        }
        return this.f3706e;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00db A:{SYNTHETIC, Splitter:B:78:0x00db} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e0 A:{SYNTHETIC, Splitter:B:81:0x00e0} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00c4 A:{SYNTHETIC, Splitter:B:68:0x00c4} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00c9 A:{SYNTHETIC, Splitter:B:71:0x00c9} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00db A:{SYNTHETIC, Splitter:B:78:0x00db} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e0 A:{SYNTHETIC, Splitter:B:81:0x00e0} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a0 A:{SYNTHETIC, Splitter:B:55:0x00a0} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a5 A:{SYNTHETIC, Splitter:B:58:0x00a5} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00c4 A:{SYNTHETIC, Splitter:B:68:0x00c4} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00c9 A:{SYNTHETIC, Splitter:B:71:0x00c9} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067  */
    /* renamed from: a */
    public java.lang.String mo12051a(java.util.List<com.amap.api.services.core.C1081ac> r10) {
        /*
        r9 = this;
        r5 = 0;
        r1 = 0;
        r0 = 0;
        r2 = 0;
        r4 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x01ce, IOException -> 0x00b5, all -> 0x00d6 }
        r3 = "/data/anr/traces.txt";
        r4.<init>(r3);	 Catch:{ FileNotFoundException -> 0x01ce, IOException -> 0x00b5, all -> 0x00d6 }
        r3 = r4.exists();	 Catch:{ FileNotFoundException -> 0x01ce, IOException -> 0x00b5, all -> 0x00d6 }
        if (r3 != 0) goto L_0x001d;
    L_0x0011:
        if (r1 == 0) goto L_0x0016;
    L_0x0013:
        r2.close();	 Catch:{ IOException -> 0x015e, Throwable -> 0x016b }
    L_0x0016:
        if (r1 == 0) goto L_0x001b;
    L_0x0018:
        r0.close();	 Catch:{ IOException -> 0x0178, Throwable -> 0x0185 }
    L_0x001b:
        r0 = r1;
    L_0x001c:
        return r0;
    L_0x001d:
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x01ce, IOException -> 0x00b5, all -> 0x00d6 }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x01ce, IOException -> 0x00b5, all -> 0x00d6 }
        r2 = new com.amap.api.services.core.bn;	 Catch:{ FileNotFoundException -> 0x01d3, IOException -> 0x01c7, all -> 0x01c0 }
        r0 = com.amap.api.services.core.C1119bo.f3770a;	 Catch:{ FileNotFoundException -> 0x01d3, IOException -> 0x01c7, all -> 0x01c0 }
        r2.<init>(r3, r0);	 Catch:{ FileNotFoundException -> 0x01d3, IOException -> 0x01c7, all -> 0x01c0 }
        r4 = r5;
    L_0x002a:
        r0 = r2.mo12087a();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r6 = "pid";
        r6 = r0.contains(r6);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        if (r6 == 0) goto L_0x01db;
    L_0x0036:
        r4 = "\"main\"";
        r4 = r0.contains(r4);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        if (r4 != 0) goto L_0x0043;
    L_0x003e:
        r0 = r2.mo12087a();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        goto L_0x0036;
    L_0x0043:
        r4 = 1;
        r6 = r0;
        r0 = r4;
    L_0x0046:
        r4 = "";
        r4 = r6.equals(r4);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        if (r4 == 0) goto L_0x01d8;
    L_0x004e:
        r4 = r5;
    L_0x004f:
        if (r4 == 0) goto L_0x002a;
    L_0x0051:
        r9.m4843b(r6);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r0 = r9.f3705d;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r7 = 5;
        if (r0 != r7) goto L_0x006c;
    L_0x0059:
        if (r2 == 0) goto L_0x005e;
    L_0x005b:
        r2.close();	 Catch:{ IOException -> 0x0192, Throwable -> 0x019f }
    L_0x005e:
        if (r3 == 0) goto L_0x0063;
    L_0x0060:
        r3.close();	 Catch:{ IOException -> 0x01ac, Throwable -> 0x01b6 }
    L_0x0063:
        r0 = r9.f3704c;
        if (r0 == 0) goto L_0x00e4;
    L_0x0067:
        r0 = r9.m4844c();
        goto L_0x001c;
    L_0x006c:
        r0 = r9.f3704c;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        if (r0 != 0) goto L_0x0094;
    L_0x0070:
        r7 = r10.iterator();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
    L_0x0074:
        r0 = r7.hasNext();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        if (r0 == 0) goto L_0x002a;
    L_0x007a:
        r0 = r7.next();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r0 = (com.amap.api.services.core.C1081ac) r0;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r8 = r0.mo11993f();	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r8 = r9.mo12055a(r8, r6);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r9.f3704c = r8;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r8 = r9.f3704c;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        if (r8 == 0) goto L_0x0074;
    L_0x008e:
        r9.mo12054a(r0);	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        goto L_0x0074;
    L_0x0092:
        r0 = move-exception;
        goto L_0x0059;
    L_0x0094:
        r0 = r9.f3705d;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        r0 = r0 + 1;
        r9.f3705d = r0;	 Catch:{ EOFException -> 0x0092, FileNotFoundException -> 0x009b, IOException -> 0x01cb }
        goto L_0x002a;
    L_0x009b:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
    L_0x009e:
        if (r0 == 0) goto L_0x00a3;
    L_0x00a0:
        r0.close();	 Catch:{ IOException -> 0x00e7, Throwable -> 0x00f3 }
    L_0x00a3:
        if (r2 == 0) goto L_0x0063;
    L_0x00a5:
        r2.close();	 Catch:{ IOException -> 0x00a9, Throwable -> 0x00ff }
        goto L_0x0063;
    L_0x00a9:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
    L_0x00b1:
        r0.printStackTrace();
        goto L_0x0063;
    L_0x00b5:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00b8:
        r4 = "ANRWriter";
        r5 = "initLog";
        com.amap.api.services.core.C1099ax.m4800a(r0, r4, r5);	 Catch:{ all -> 0x01c4 }
        r0.printStackTrace();	 Catch:{ all -> 0x01c4 }
        if (r2 == 0) goto L_0x00c7;
    L_0x00c4:
        r2.close();	 Catch:{ IOException -> 0x013c, Throwable -> 0x0148 }
    L_0x00c7:
        if (r3 == 0) goto L_0x0063;
    L_0x00c9:
        r3.close();	 Catch:{ IOException -> 0x00cd, Throwable -> 0x0155 }
        goto L_0x0063;
    L_0x00cd:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
        goto L_0x00b1;
    L_0x00d6:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x00d9:
        if (r2 == 0) goto L_0x00de;
    L_0x00db:
        r2.close();	 Catch:{ IOException -> 0x010c, Throwable -> 0x0118 }
    L_0x00de:
        if (r3 == 0) goto L_0x00e3;
    L_0x00e0:
        r3.close();	 Catch:{ IOException -> 0x0124, Throwable -> 0x0130 }
    L_0x00e3:
        throw r0;
    L_0x00e4:
        r0 = r1;
        goto L_0x001c;
    L_0x00e7:
        r0 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.services.core.C1099ax.m4800a(r0, r3, r4);
        r0.printStackTrace();
        goto L_0x00a3;
    L_0x00f3:
        r0 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.services.core.C1099ax.m4800a(r0, r3, r4);
        r0.printStackTrace();
        goto L_0x00a3;
    L_0x00ff:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
    L_0x0107:
        r0.printStackTrace();
        goto L_0x0063;
    L_0x010c:
        r1 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r4);
        r1.printStackTrace();
        goto L_0x00de;
    L_0x0118:
        r1 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r4);
        r1.printStackTrace();
        goto L_0x00de;
    L_0x0124:
        r1 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        r1.printStackTrace();
        goto L_0x00e3;
    L_0x0130:
        r1 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.services.core.C1099ax.m4800a(r1, r2, r3);
        r1.printStackTrace();
        goto L_0x00e3;
    L_0x013c:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r4);
        r0.printStackTrace();
        goto L_0x00c7;
    L_0x0148:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r4);
        r0.printStackTrace();
        goto L_0x00c7;
    L_0x0155:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
        goto L_0x0107;
    L_0x015e:
        r2 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.services.core.C1099ax.m4800a(r2, r3, r4);
        r2.printStackTrace();
        goto L_0x0016;
    L_0x016b:
        r2 = move-exception;
        r3 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.services.core.C1099ax.m4800a(r2, r3, r4);
        r2.printStackTrace();
        goto L_0x0016;
    L_0x0178:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
        r0.printStackTrace();
        goto L_0x001b;
    L_0x0185:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
        r0.printStackTrace();
        goto L_0x001b;
    L_0x0192:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog1";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r4);
        r0.printStackTrace();
        goto L_0x005e;
    L_0x019f:
        r0 = move-exception;
        r2 = "ANRWriter";
        r4 = "initLog2";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r4);
        r0.printStackTrace();
        goto L_0x005e;
    L_0x01ac:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog3";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
        goto L_0x00b1;
    L_0x01b6:
        r0 = move-exception;
        r2 = "ANRWriter";
        r3 = "initLog4";
        com.amap.api.services.core.C1099ax.m4800a(r0, r2, r3);
        goto L_0x0107;
    L_0x01c0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00d9;
    L_0x01c4:
        r0 = move-exception;
        goto L_0x00d9;
    L_0x01c7:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00b8;
    L_0x01cb:
        r0 = move-exception;
        goto L_0x00b8;
    L_0x01ce:
        r0 = move-exception;
        r0 = r1;
        r2 = r1;
        goto L_0x009e;
    L_0x01d3:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
        goto L_0x009e;
    L_0x01d8:
        r4 = r0;
        goto L_0x004f;
    L_0x01db:
        r6 = r0;
        r0 = r4;
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.ANRLogWriter.mo12051a(java.util.List):java.lang.String");
    }

    /* renamed from: c */
    private String m4844c() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.f3703b;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.f3702a[i]);
                i++;
            }
            for (i = 0; i < this.f3703b; i++) {
                stringBuilder.append(this.f3702a[i]);
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "ANRWriter", "getLogInfo");
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private void m4843b(String str) {
        try {
            if (this.f3703b > 9) {
                this.f3703b = 0;
            }
            this.f3702a[this.f3703b] = str;
            this.f3703b++;
        } catch (Throwable th) {
            C1099ax.m4800a(th, "ANRWriter", "addData");
            th.printStackTrace();
        }
    }
}
