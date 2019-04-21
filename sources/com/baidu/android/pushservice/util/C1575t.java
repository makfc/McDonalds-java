package com.baidu.android.pushservice.util;

/* renamed from: com.baidu.android.pushservice.util.t */
public class C1575t {
    /* renamed from: a */
    private static final String f5536a = C1575t.class.getSimpleName();

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: a */
    public static java.lang.String m7034a(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getChannelID cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushChannelID;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushchannelid  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushchannelid  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7034a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: b */
    public static java.lang.String m7035b(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getCurrentPkgName cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushCurPkgName;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushcurrpkgname  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushcurrpkgname  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7035b(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ae A:{SYNTHETIC, Splitter:B:25:0x00ae} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bd A:{SYNTHETIC, Splitter:B:32:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ae A:{SYNTHETIC, Splitter:B:25:0x00ae} */
    /* renamed from: c */
    public static int m7036c(android.content.Context r9, java.lang.String r10) {
        /*
        r7 = 0;
        r6 = 0;
        r0 = r9.getContentResolver();	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        if (r0 == 0) goto L_0x00d5;
    L_0x0008:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1 = r1.append(r10);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00ca }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ca }
        r1.<init>();	 Catch:{ Throwable -> 0x00ca }
        r3 = "getPriority cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00ca }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00ca }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00ca }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00ca }
        if (r2 == 0) goto L_0x00d3;
    L_0x0053:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00ca }
        if (r0 == 0) goto L_0x00d3;
    L_0x0059:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushPriority;	 Catch:{ Throwable -> 0x00ca }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00ca }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00ca }
        r1 = r2.getInt(r0);	 Catch:{ Throwable -> 0x00ca }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ce }
        r0.<init>();	 Catch:{ Throwable -> 0x00ce }
        r3 = "pushpriority  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00ce }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00ce }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00ce }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r9);	 Catch:{ Throwable -> 0x00ce }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00ce }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ce }
        r3.<init>();	 Catch:{ Throwable -> 0x00ce }
        r4 = "pushpriority  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00ce }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00ce }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00ce }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00ce }
        r0 = r1;
    L_0x0096:
        if (r2 == 0) goto L_0x009b;
    L_0x0098:
        r2.close();	 Catch:{ Exception -> 0x009c }
    L_0x009b:
        return r0;
    L_0x009c:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009b;
    L_0x00a3:
        r0 = move-exception;
        r1 = r0;
        r2 = r7;
        r0 = r6;
    L_0x00a7:
        r3 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r1);	 Catch:{ all -> 0x00c8 }
        if (r2 == 0) goto L_0x009b;
    L_0x00ae:
        r2.close();	 Catch:{ Exception -> 0x00b2 }
        goto L_0x009b;
    L_0x00b2:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009b;
    L_0x00b9:
        r0 = move-exception;
        r2 = r7;
    L_0x00bb:
        if (r2 == 0) goto L_0x00c0;
    L_0x00bd:
        r2.close();	 Catch:{ Exception -> 0x00c1 }
    L_0x00c0:
        throw r0;
    L_0x00c1:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00c0;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x00bb;
    L_0x00ca:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        goto L_0x00a7;
    L_0x00ce:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x00a7;
    L_0x00d3:
        r0 = r6;
        goto L_0x0096;
    L_0x00d5:
        r0 = r6;
        r2 = r7;
        goto L_0x0096;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7036c(android.content.Context, java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ae A:{SYNTHETIC, Splitter:B:25:0x00ae} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bd A:{SYNTHETIC, Splitter:B:32:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ae A:{SYNTHETIC, Splitter:B:25:0x00ae} */
    /* renamed from: d */
    public static int m7037d(android.content.Context r9, java.lang.String r10) {
        /*
        r7 = 0;
        r6 = 0;
        r0 = r9.getContentResolver();	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        if (r0 == 0) goto L_0x00d5;
    L_0x0008:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1 = r1.append(r10);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a3, all -> 0x00b9 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00ca }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ca }
        r1.<init>();	 Catch:{ Throwable -> 0x00ca }
        r3 = "getVersion cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00ca }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00ca }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00ca }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00ca }
        if (r2 == 0) goto L_0x00d3;
    L_0x0053:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00ca }
        if (r0 == 0) goto L_0x00d3;
    L_0x0059:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushVersion;	 Catch:{ Throwable -> 0x00ca }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00ca }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00ca }
        r1 = r2.getInt(r0);	 Catch:{ Throwable -> 0x00ca }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ce }
        r0.<init>();	 Catch:{ Throwable -> 0x00ce }
        r3 = "pushversion  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00ce }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00ce }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00ce }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r9);	 Catch:{ Throwable -> 0x00ce }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00ce }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ce }
        r3.<init>();	 Catch:{ Throwable -> 0x00ce }
        r4 = "pushversion  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00ce }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00ce }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00ce }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00ce }
        r0 = r1;
    L_0x0096:
        if (r2 == 0) goto L_0x009b;
    L_0x0098:
        r2.close();	 Catch:{ Exception -> 0x009c }
    L_0x009b:
        return r0;
    L_0x009c:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009b;
    L_0x00a3:
        r0 = move-exception;
        r1 = r0;
        r2 = r7;
        r0 = r6;
    L_0x00a7:
        r3 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r1);	 Catch:{ all -> 0x00c8 }
        if (r2 == 0) goto L_0x009b;
    L_0x00ae:
        r2.close();	 Catch:{ Exception -> 0x00b2 }
        goto L_0x009b;
    L_0x00b2:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009b;
    L_0x00b9:
        r0 = move-exception;
        r2 = r7;
    L_0x00bb:
        if (r2 == 0) goto L_0x00c0;
    L_0x00bd:
        r2.close();	 Catch:{ Exception -> 0x00c1 }
    L_0x00c0:
        throw r0;
    L_0x00c1:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00c0;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x00bb;
    L_0x00ca:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        goto L_0x00a7;
    L_0x00ce:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x00a7;
    L_0x00d3:
        r0 = r6;
        goto L_0x0096;
    L_0x00d5:
        r0 = r6;
        r2 = r7;
        goto L_0x0096;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7037d(android.content.Context, java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: e */
    public static java.lang.String m7038e(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getWebAppBindInfo cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushWebAppBindInfo;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushwebappbindinfo  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushwebappbindinfo  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7038e(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: f */
    public static java.lang.String m7039f(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getLightAppBindInfo cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushLightAppBindInfo;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushlightappbindinfo  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushlightappbindinfo  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7039f(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: g */
    public static java.lang.String m7040g(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getSDKClientBindInfo cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSDKClientBindInfo;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushlightappbindinfo  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushlightappbindinfo  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7040g(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: h */
    public static java.lang.String m7041h(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getPushClientsBindInfo cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushClientsBindInfo;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushlightappbindinfo  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushlightappbindinfo  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7041h(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb A:{SYNTHETIC, Splitter:B:32:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A:{SYNTHETIC, Splitter:B:25:0x00ac} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0038} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:?, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:28:0x00b1, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:35:0x00bf, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:36:0x00c0, code skipped:
            com.baidu.android.pushservice.p036h.C1425a.m6440a(f5536a, r1);
     */
    /* JADX WARNING: Missing block: B:37:0x00c6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:41:0x00cc, code skipped:
            r1 = r0;
            r0 = null;
            r6 = r2;
     */
    /* renamed from: i */
    public static java.lang.String m7042i(android.content.Context r8, java.lang.String r9) {
        /*
        r6 = 0;
        r0 = r8.getContentResolver();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0007:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1.<init>();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.append(r9);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = ".bdpush";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "/";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = "pushinfo";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2, all -> 0x00b7 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r3 = "getPushSelfBindInfo cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r2 == 0) goto L_0x00d6;
    L_0x0052:
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = com.baidu.android.pushservice.util.C1574s.C1573c.PushSelfBindInfo;	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r0.name();	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r1 = r2.getString(r0);	 Catch:{ Throwable -> 0x00cb, all -> 0x00c6 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = "pushlightappbindinfo  is ";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r0, r8);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = f5536a;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r4 = "pushlightappbindinfo  is ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.append(r1);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);	 Catch:{ Throwable -> 0x00d0, all -> 0x00c6 }
        r0 = r1;
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ Exception -> 0x009b }
    L_0x009a:
        return r0;
    L_0x009b:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00a2:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00a5:
        r2 = f5536a;	 Catch:{ all -> 0x00c8 }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x00c8 }
        if (r6 == 0) goto L_0x009a;
    L_0x00ac:
        r6.close();	 Catch:{ Exception -> 0x00b0 }
        goto L_0x009a;
    L_0x00b0:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x009a;
    L_0x00b7:
        r0 = move-exception;
        r2 = r6;
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ Exception -> 0x00bf }
    L_0x00be:
        throw r0;
    L_0x00bf:
        r1 = move-exception;
        r2 = f5536a;
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x00be;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00b9;
    L_0x00c8:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b9;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        r6 = r2;
        goto L_0x00a5;
    L_0x00d0:
        r0 = move-exception;
        r6 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a5;
    L_0x00d6:
        r0 = r6;
        goto L_0x0095;
    L_0x00d8:
        r0 = r6;
        r2 = r6;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1575t.m7042i(android.content.Context, java.lang.String):java.lang.String");
    }
}
