package com.baidu.android.pushservice.p039k;

import android.annotation.SuppressLint;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.baidu.android.pushservice.k.g */
public final class C1473g {
    @SuppressLint({"InlinedApi", "OldTargetApi"})
    /* renamed from: a */
    public static byte[] m6717a(byte[] bArr, String str) throws Exception {
        PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(C1465b.m6679a(str.getBytes())));
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, generatePrivate);
        return instance.doFinal(bArr);
    }

    @SuppressLint({"InlinedApi", "OldTargetApi"})
    /* renamed from: a */
    public static byte[] m6718a(byte[] bArr, String str, int i) throws Exception {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(C1465b.m6679a(str.getBytes())));
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, generatePublic);
        int i2 = i / 8;
        int i3 = i2 - 11;
        int length = bArr.length;
        byte[] bArr2 = new byte[((((length + i3) - 1) / i3) * i2)];
        int i4 = 0;
        int i5 = 0;
        while (i5 < length) {
            int i6 = length - i5;
            if (i3 < i6) {
                i6 = i3;
            }
            byte[] bArr3 = new byte[i6];
            System.arraycopy(bArr, i5, bArr3, 0, i6);
            i5 += i6;
            System.arraycopy(instance.doFinal(bArr3), 0, bArr2, i4, i2);
            i4 += i2;
        }
        return bArr2;
    }

    @SuppressLint({"InlinedApi", "OldTargetApi"})
    /* renamed from: b */
    public static byte[] m6719b(byte[] bArr, String str) throws Exception {
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(C1465b.m6679a(str.getBytes())));
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, generatePublic);
        return instance.doFinal(bArr);
    }
}
