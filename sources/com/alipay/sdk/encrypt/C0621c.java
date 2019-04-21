package com.alipay.sdk.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/* renamed from: com.alipay.sdk.encrypt.c */
public class C0621c {
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A:{SYNTHETIC, Splitter:B:17:0x0025} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002a A:{SYNTHETIC, Splitter:B:20:0x002a} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x002f A:{SYNTHETIC, Splitter:B:23:0x002f} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A:{SYNTHETIC, Splitter:B:17:0x0025} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002a A:{SYNTHETIC, Splitter:B:20:0x002a} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x002f A:{SYNTHETIC, Splitter:B:23:0x002f} */
    /* renamed from: a */
    public static byte[] m902a(byte[] r6) throws java.io.IOException {
        /*
        r2 = 0;
        r4 = new java.io.ByteArrayInputStream;	 Catch:{ all -> 0x0059 }
        r4.<init>(r6);	 Catch:{ all -> 0x0059 }
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ all -> 0x005d }
        r3.<init>();	 Catch:{ all -> 0x005d }
        r1 = new java.util.zip.GZIPOutputStream;	 Catch:{ all -> 0x0061 }
        r1.<init>(r3);	 Catch:{ all -> 0x0061 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ all -> 0x0020 }
    L_0x0014:
        r2 = r4.read(r0);	 Catch:{ all -> 0x0020 }
        r5 = -1;
        if (r2 == r5) goto L_0x0033;
    L_0x001b:
        r5 = 0;
        r1.write(r0, r5, r2);	 Catch:{ all -> 0x0020 }
        goto L_0x0014;
    L_0x0020:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
    L_0x0023:
        if (r3 == 0) goto L_0x0028;
    L_0x0025:
        r3.close();	 Catch:{ Exception -> 0x0053 }
    L_0x0028:
        if (r2 == 0) goto L_0x002d;
    L_0x002a:
        r2.close();	 Catch:{ Exception -> 0x0055 }
    L_0x002d:
        if (r1 == 0) goto L_0x0032;
    L_0x002f:
        r1.close();	 Catch:{ Exception -> 0x0057 }
    L_0x0032:
        throw r0;
    L_0x0033:
        r1.flush();	 Catch:{ all -> 0x0020 }
        r1.finish();	 Catch:{ all -> 0x0020 }
        r0 = r3.toByteArray();	 Catch:{ all -> 0x0020 }
        if (r4 == 0) goto L_0x0042;
    L_0x003f:
        r4.close();	 Catch:{ Exception -> 0x004d }
    L_0x0042:
        if (r3 == 0) goto L_0x0047;
    L_0x0044:
        r3.close();	 Catch:{ Exception -> 0x004f }
    L_0x0047:
        if (r1 == 0) goto L_0x004c;
    L_0x0049:
        r1.close();	 Catch:{ Exception -> 0x0051 }
    L_0x004c:
        return r0;
    L_0x004d:
        r2 = move-exception;
        goto L_0x0042;
    L_0x004f:
        r2 = move-exception;
        goto L_0x0047;
    L_0x0051:
        r1 = move-exception;
        goto L_0x004c;
    L_0x0053:
        r3 = move-exception;
        goto L_0x0028;
    L_0x0055:
        r2 = move-exception;
        goto L_0x002d;
    L_0x0057:
        r1 = move-exception;
        goto L_0x0032;
    L_0x0059:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x0023;
    L_0x005d:
        r0 = move-exception;
        r1 = r2;
        r3 = r4;
        goto L_0x0023;
    L_0x0061:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.encrypt.C0621c.m902a(byte[]):byte[]");
    }

    /* renamed from: b */
    public static byte[] m903b(byte[] bArr) throws IOException {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        GZIPInputStream gZIPInputStream;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
                try {
                    byte[] bArr2 = new byte[4096];
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr2, 0, read);
                        } catch (Throwable th2) {
                            th = th2;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            byteArrayInputStream = byteArrayInputStream2;
                        }
                    }
                    byteArrayOutputStream2.flush();
                    bArr2 = byteArrayOutputStream2.toByteArray();
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e2) {
                    }
                    try {
                        byteArrayInputStream2.close();
                    } catch (Exception e3) {
                    }
                    return bArr2;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayInputStream = byteArrayInputStream2;
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e5) {
                    }
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e6) {
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                gZIPInputStream = null;
                byteArrayInputStream = byteArrayInputStream2;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            gZIPInputStream = null;
            byteArrayInputStream = null;
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            byteArrayInputStream.close();
            throw th;
        }
    }
}
