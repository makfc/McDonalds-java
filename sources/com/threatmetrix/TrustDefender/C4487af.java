package com.threatmetrix.TrustDefender;

/* renamed from: com.threatmetrix.TrustDefender.af */
class C4487af implements Runnable {
    /* renamed from: f */
    private static final String f7372f = C4549w.m8585a(C4487af.class);
    /* renamed from: a */
    private String f7373a = null;
    /* renamed from: b */
    private String f7374b = null;
    /* renamed from: c */
    private String f7375c = null;
    /* renamed from: d */
    private String f7376d = null;
    /* renamed from: e */
    private int f7377e = 10000;

    public C4487af(String fp_server, String org_id, String session_id, String w, int timeout) {
        this.f7373a = fp_server;
        this.f7374b = org_id;
        this.f7375c = session_id;
        this.f7376d = w;
        this.f7377e = timeout;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x00a3=Splitter:B:30:0x00a3, B:49:0x00ce=Splitter:B:49:0x00ce} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b3 A:{SYNTHETIC, Splitter:B:34:0x00b3} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b8 A:{SYNTHETIC, Splitter:B:37:0x00b8} */
    /* JADX WARNING: Removed duplicated region for block: B:106:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd A:{SYNTHETIC, Splitter:B:40:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00de A:{SYNTHETIC, Splitter:B:53:0x00de} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e3 A:{SYNTHETIC, Splitter:B:56:0x00e3} */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e8 A:{SYNTHETIC, Splitter:B:59:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0102 A:{SYNTHETIC, Splitter:B:70:0x0102} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0107 A:{SYNTHETIC, Splitter:B:73:0x0107} */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010c A:{SYNTHETIC, Splitter:B:76:0x010c} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0102 A:{SYNTHETIC, Splitter:B:70:0x0102} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0107 A:{SYNTHETIC, Splitter:B:73:0x0107} */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010c A:{SYNTHETIC, Splitter:B:76:0x010c} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b3 A:{SYNTHETIC, Splitter:B:34:0x00b3} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b8 A:{SYNTHETIC, Splitter:B:37:0x00b8} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd A:{SYNTHETIC, Splitter:B:40:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:106:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00de A:{SYNTHETIC, Splitter:B:53:0x00de} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e3 A:{SYNTHETIC, Splitter:B:56:0x00e3} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e8 A:{SYNTHETIC, Splitter:B:59:0x00e8} */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0102 A:{SYNTHETIC, Splitter:B:70:0x0102} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0107 A:{SYNTHETIC, Splitter:B:73:0x0107} */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010c A:{SYNTHETIC, Splitter:B:76:0x010c} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b3 A:{SYNTHETIC, Splitter:B:34:0x00b3} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b8 A:{SYNTHETIC, Splitter:B:37:0x00b8} */
    /* JADX WARNING: Removed duplicated region for block: B:106:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd A:{SYNTHETIC, Splitter:B:40:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00de A:{SYNTHETIC, Splitter:B:53:0x00de} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e3 A:{SYNTHETIC, Splitter:B:56:0x00e3} */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e8 A:{SYNTHETIC, Splitter:B:59:0x00e8} */
    public void run() {
        /*
        r13 = this;
        r8 = 0;
        r5 = 0;
        r6 = 0;
        r3 = 0;
        r9 = new java.net.Socket;	 Catch:{ UnknownHostException -> 0x00a2, IOException -> 0x00cd }
        r10 = r13.f7373a;	 Catch:{ UnknownHostException -> 0x00a2, IOException -> 0x00cd }
        r11 = 8080; // 0x1f90 float:1.1322E-41 double:3.992E-320;
        r9.<init>(r10, r11);	 Catch:{ UnknownHostException -> 0x00a2, IOException -> 0x00cd }
        r10 = r13.f7377e;	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r9.setSoTimeout(r10);	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r5 = r9.getOutputStream();	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r7 = new java.io.BufferedWriter;	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r10 = new java.io.PrintWriter;	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r10.<init>(r5);	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r7.<init>(r10);	 Catch:{ UnknownHostException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
        r4 = new java.io.BufferedReader;	 Catch:{ UnknownHostException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
        r10 = new java.io.InputStreamReader;	 Catch:{ UnknownHostException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
        r11 = r9.getInputStream();	 Catch:{ UnknownHostException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
        r10.<init>(r11);	 Catch:{ UnknownHostException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
        r4.<init>(r10);	 Catch:{ UnknownHostException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
        r10 = new java.lang.StringBuilder;	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = "<handle sig=FF44EE55 session_id=";
        r10.<init>(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = r13.f7375c;	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r10 = r10.append(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = " org_id=";
        r10 = r10.append(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = r13.f7374b;	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r10 = r10.append(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = " w=";
        r10 = r10.append(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = r13.f7376d;	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r10 = r10.append(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = " />";
        r10 = r10.append(r11);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r10 = r10.toString();	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r7.write(r10);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r7.flush();	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r10 = r4.read();	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r11 = -1;
        if (r10 == r11) goto L_0x007f;
    L_0x006a:
        r10 = 1;
        r1 = new char[r10];	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r10 = 0;
        r11 = 0;
        r1[r10] = r11;	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r2 = 0;
    L_0x0072:
        r10 = 15;
        if (r2 >= r10) goto L_0x007c;
    L_0x0076:
        r7.write(r1);	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
        r2 = r2 + 1;
        goto L_0x0072;
    L_0x007c:
        r4.read();	 Catch:{ UnknownHostException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
    L_0x007f:
        r10 = f7372f;
        r11 = "Tidying up";
        com.threatmetrix.TrustDefender.C4549w.m8594c(r10, r11);
        r9.close();	 Catch:{ IOException -> 0x0093 }
    L_0x0089:
        r7.close();	 Catch:{ IOException -> 0x0097 }
    L_0x008c:
        r4.close();	 Catch:{ IOException -> 0x009b }
        r3 = r4;
        r6 = r7;
        r8 = r9;
    L_0x0092:
        return;
    L_0x0093:
        r0 = move-exception;
        r10 = f7372f;
        goto L_0x0089;
    L_0x0097:
        r0 = move-exception;
        r10 = f7372f;
        goto L_0x008c;
    L_0x009b:
        r0 = move-exception;
        r10 = f7372f;
        r3 = r4;
        r6 = r7;
        r8 = r9;
        goto L_0x0092;
    L_0x00a2:
        r0 = move-exception;
    L_0x00a3:
        r10 = f7372f;	 Catch:{ all -> 0x00f8 }
        r11 = "Failed to connect to the fp server";
        com.threatmetrix.TrustDefender.C4549w.m8595c(r10, r11, r0);	 Catch:{ all -> 0x00f8 }
        r10 = f7372f;
        r11 = "Tidying up";
        com.threatmetrix.TrustDefender.C4549w.m8594c(r10, r11);
        if (r8 == 0) goto L_0x00b6;
    L_0x00b3:
        r8.close();	 Catch:{ IOException -> 0x00c5 }
    L_0x00b6:
        if (r6 == 0) goto L_0x00bb;
    L_0x00b8:
        r6.close();	 Catch:{ IOException -> 0x00c9 }
    L_0x00bb:
        if (r3 == 0) goto L_0x0092;
    L_0x00bd:
        r3.close();	 Catch:{ IOException -> 0x00c1 }
        goto L_0x0092;
    L_0x00c1:
        r10 = move-exception;
        r10 = f7372f;
        goto L_0x0092;
    L_0x00c5:
        r10 = move-exception;
        r10 = f7372f;
        goto L_0x00b6;
    L_0x00c9:
        r10 = move-exception;
        r10 = f7372f;
        goto L_0x00bb;
    L_0x00cd:
        r0 = move-exception;
    L_0x00ce:
        r10 = f7372f;	 Catch:{ all -> 0x00f8 }
        r11 = "Failed to read/write to the fp server";
        com.threatmetrix.TrustDefender.C4549w.m8595c(r10, r11, r0);	 Catch:{ all -> 0x00f8 }
        r10 = f7372f;
        r11 = "Tidying up";
        com.threatmetrix.TrustDefender.C4549w.m8594c(r10, r11);
        if (r8 == 0) goto L_0x00e1;
    L_0x00de:
        r8.close();	 Catch:{ IOException -> 0x00f0 }
    L_0x00e1:
        if (r6 == 0) goto L_0x00e6;
    L_0x00e3:
        r6.close();	 Catch:{ IOException -> 0x00f4 }
    L_0x00e6:
        if (r3 == 0) goto L_0x0092;
    L_0x00e8:
        r3.close();	 Catch:{ IOException -> 0x00ec }
        goto L_0x0092;
    L_0x00ec:
        r10 = move-exception;
        r10 = f7372f;
        goto L_0x0092;
    L_0x00f0:
        r10 = move-exception;
        r10 = f7372f;
        goto L_0x00e1;
    L_0x00f4:
        r10 = move-exception;
        r10 = f7372f;
        goto L_0x00e6;
    L_0x00f8:
        r10 = move-exception;
    L_0x00f9:
        r11 = f7372f;
        r12 = "Tidying up";
        com.threatmetrix.TrustDefender.C4549w.m8594c(r11, r12);
        if (r8 == 0) goto L_0x0105;
    L_0x0102:
        r8.close();	 Catch:{ IOException -> 0x0110 }
    L_0x0105:
        if (r6 == 0) goto L_0x010a;
    L_0x0107:
        r6.close();	 Catch:{ IOException -> 0x0114 }
    L_0x010a:
        if (r3 == 0) goto L_0x010f;
    L_0x010c:
        r3.close();	 Catch:{ IOException -> 0x0118 }
    L_0x010f:
        throw r10;
    L_0x0110:
        r11 = move-exception;
        r11 = f7372f;
        goto L_0x0105;
    L_0x0114:
        r11 = move-exception;
        r11 = f7372f;
        goto L_0x010a;
    L_0x0118:
        r11 = move-exception;
        r11 = f7372f;
        goto L_0x010f;
    L_0x011c:
        r10 = move-exception;
        r8 = r9;
        goto L_0x00f9;
    L_0x011f:
        r10 = move-exception;
        r6 = r7;
        r8 = r9;
        goto L_0x00f9;
    L_0x0123:
        r10 = move-exception;
        r3 = r4;
        r6 = r7;
        r8 = r9;
        goto L_0x00f9;
    L_0x0128:
        r0 = move-exception;
        r8 = r9;
        goto L_0x00ce;
    L_0x012b:
        r0 = move-exception;
        r6 = r7;
        r8 = r9;
        goto L_0x00ce;
    L_0x012f:
        r0 = move-exception;
        r3 = r4;
        r6 = r7;
        r8 = r9;
        goto L_0x00ce;
    L_0x0134:
        r0 = move-exception;
        r8 = r9;
        goto L_0x00a3;
    L_0x0138:
        r0 = move-exception;
        r6 = r7;
        r8 = r9;
        goto L_0x00a3;
    L_0x013d:
        r0 = move-exception;
        r3 = r4;
        r6 = r7;
        r8 = r9;
        goto L_0x00a3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4487af.run():void");
    }
}
