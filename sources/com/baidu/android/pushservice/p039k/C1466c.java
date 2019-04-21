package com.baidu.android.pushservice.p039k;

/* renamed from: com.baidu.android.pushservice.k.c */
public class C1466c {
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0079 A:{SYNTHETIC, Splitter:B:20:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009c A:{SYNTHETIC, Splitter:B:37:0x009c} */
    /* renamed from: a */
    public static java.util.ArrayList<java.lang.String> m6681a(java.lang.String r8, java.io.File r9) {
        /*
        r2 = 0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = "no su";
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x00a7, all -> 0x0098 }
        r0 = r0.exec(r8);	 Catch:{ Exception -> 0x00a7, all -> 0x0098 }
        r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x00a7, all -> 0x0098 }
        r5 = r0.getInputStream();	 Catch:{ Exception -> 0x00a7, all -> 0x0098 }
        r6 = "utf-8";
        r1.<init>(r5, r6);	 Catch:{ Exception -> 0x00a7, all -> 0x0098 }
        r5 = new java.io.LineNumberReader;	 Catch:{ Exception -> 0x0070 }
        r5.<init>(r1);	 Catch:{ Exception -> 0x0070 }
        if (r9 == 0) goto L_0x004e;
    L_0x0023:
        r2 = r9.exists();	 Catch:{ Exception -> 0x0070 }
        if (r2 != 0) goto L_0x002c;
    L_0x0029:
        r9.createNewFile();	 Catch:{ Exception -> 0x0070 }
    L_0x002c:
        r2 = new java.io.FileWriter;	 Catch:{ Exception -> 0x0070 }
        r6 = 1;
        r2.<init>(r9, r6);	 Catch:{ Exception -> 0x0070 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0070 }
        r6.<init>();	 Catch:{ Exception -> 0x0070 }
        r7 = "\n\n\n---------------   CMD : ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0070 }
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x0070 }
        r7 = "   ---------------\n\n\n";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0070 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0070 }
        r2.append(r6);	 Catch:{ Exception -> 0x0070 }
    L_0x004e:
        r6 = r5.readLine();	 Catch:{ Exception -> 0x0070 }
        if (r6 == 0) goto L_0x007d;
    L_0x0054:
        r3.add(r6);	 Catch:{ Exception -> 0x0070 }
        if (r2 == 0) goto L_0x004e;
    L_0x0059:
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0070 }
        r7.<init>();	 Catch:{ Exception -> 0x0070 }
        r6 = r7.append(r6);	 Catch:{ Exception -> 0x0070 }
        r7 = "\n";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0070 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0070 }
        r2.append(r6);	 Catch:{ Exception -> 0x0070 }
        goto L_0x004e;
    L_0x0070:
        r0 = move-exception;
    L_0x0071:
        r0.printStackTrace();	 Catch:{ all -> 0x00a5 }
        r3.add(r4);	 Catch:{ all -> 0x00a5 }
        if (r1 == 0) goto L_0x007c;
    L_0x0079:
        r1.close();	 Catch:{ IOException -> 0x0093 }
    L_0x007c:
        return r3;
    L_0x007d:
        if (r2 == 0) goto L_0x0085;
    L_0x007f:
        r2.flush();	 Catch:{ Exception -> 0x0070 }
        r2.close();	 Catch:{ Exception -> 0x0070 }
    L_0x0085:
        r0.waitFor();	 Catch:{ Exception -> 0x0070 }
        if (r1 == 0) goto L_0x007c;
    L_0x008a:
        r1.close();	 Catch:{ IOException -> 0x008e }
        goto L_0x007c;
    L_0x008e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007c;
    L_0x0093:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007c;
    L_0x0098:
        r0 = move-exception;
        r1 = r2;
    L_0x009a:
        if (r1 == 0) goto L_0x009f;
    L_0x009c:
        r1.close();	 Catch:{ IOException -> 0x00a0 }
    L_0x009f:
        throw r0;
    L_0x00a0:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x009f;
    L_0x00a5:
        r0 = move-exception;
        goto L_0x009a;
    L_0x00a7:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p039k.C1466c.m6681a(java.lang.String, java.io.File):java.util.ArrayList");
    }
}
