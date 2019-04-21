package com.alipay.sdk.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.alipay.sdk.encrypt.e */
public class C0623e {
    /* renamed from: a */
    private static String f589a = "DESede/CBC/PKCS5Padding";

    /* renamed from: a */
    public static byte[] m907a(String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
            Cipher instance = Cipher.getInstance(f589a);
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return bArr2;
        }
    }

    /* renamed from: b */
    public static byte[] m909b(String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
            Cipher instance = Cipher.getInstance(f589a);
            instance.init(2, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return bArr2;
        }
    }

    /* renamed from: a */
    public static String m906a(String str, String str2) {
        String str3 = null;
        try {
            return C0619a.m894a(C0623e.m907a(str, str2.getBytes()));
        } catch (Exception e) {
            return str3;
        }
    }

    /* renamed from: b */
    public static String m908b(String str, String str2) {
        try {
            return new String(C0623e.m909b(str, C0619a.m896a(str2)));
        } catch (Exception e) {
            return null;
        }
    }
}
