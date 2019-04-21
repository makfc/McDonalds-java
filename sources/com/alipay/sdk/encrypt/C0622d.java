package com.alipay.sdk.encrypt;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/* renamed from: com.alipay.sdk.encrypt.d */
public class C0622d {
    /* renamed from: b */
    private static PublicKey m905b(String str, String str2) throws NoSuchAlgorithmException, Exception {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(C0619a.m896a(str2)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004e A:{SYNTHETIC, Splitter:B:24:0x004e} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005c A:{SYNTHETIC, Splitter:B:31:0x005c} */
    /* renamed from: a */
    public static byte[] m904a(java.lang.String r7, java.lang.String r8) {
        /*
        r0 = 0;
        r1 = "RSA";
        r1 = com.alipay.sdk.encrypt.C0622d.m905b(r1, r8);	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r2 = "RSA/ECB/PKCS1Padding";
        r5 = javax.crypto.Cipher.getInstance(r2);	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r2 = 1;
        r5.init(r2, r1);	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r1 = "UTF-8";
        r6 = r7.getBytes(r1);	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r3 = r5.getBlockSize();	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r2.<init>();	 Catch:{ Exception -> 0x0047, all -> 0x0057 }
        r1 = 0;
        r4 = r1;
    L_0x0022:
        r1 = r6.length;	 Catch:{ Exception -> 0x0067 }
        if (r4 >= r1) goto L_0x0038;
    L_0x0025:
        r1 = r6.length;	 Catch:{ Exception -> 0x0067 }
        r1 = r1 - r4;
        if (r1 >= r3) goto L_0x0036;
    L_0x0029:
        r1 = r6.length;	 Catch:{ Exception -> 0x0067 }
        r1 = r1 - r4;
    L_0x002b:
        r1 = r5.doFinal(r6, r4, r1);	 Catch:{ Exception -> 0x0067 }
        r2.write(r1);	 Catch:{ Exception -> 0x0067 }
        r1 = r4 + r3;
        r4 = r1;
        goto L_0x0022;
    L_0x0036:
        r1 = r3;
        goto L_0x002b;
    L_0x0038:
        r0 = r2.toByteArray();	 Catch:{ Exception -> 0x0067 }
        if (r2 == 0) goto L_0x0041;
    L_0x003e:
        r2.close();	 Catch:{ IOException -> 0x0042 }
    L_0x0041:
        return r0;
    L_0x0042:
        r1 = move-exception;
        com.alipay.sdk.util.C0646c.m1016a(r1);
        goto L_0x0041;
    L_0x0047:
        r1 = move-exception;
        r2 = r0;
    L_0x0049:
        com.alipay.sdk.util.C0646c.m1016a(r1);	 Catch:{ all -> 0x0065 }
        if (r2 == 0) goto L_0x0041;
    L_0x004e:
        r2.close();	 Catch:{ IOException -> 0x0052 }
        goto L_0x0041;
    L_0x0052:
        r1 = move-exception;
        com.alipay.sdk.util.C0646c.m1016a(r1);
        goto L_0x0041;
    L_0x0057:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x005a:
        if (r2 == 0) goto L_0x005f;
    L_0x005c:
        r2.close();	 Catch:{ IOException -> 0x0060 }
    L_0x005f:
        throw r0;
    L_0x0060:
        r1 = move-exception;
        com.alipay.sdk.util.C0646c.m1016a(r1);
        goto L_0x005f;
    L_0x0065:
        r0 = move-exception;
        goto L_0x005a;
    L_0x0067:
        r1 = move-exception;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.encrypt.C0622d.m904a(java.lang.String, java.lang.String):byte[]");
    }
}
