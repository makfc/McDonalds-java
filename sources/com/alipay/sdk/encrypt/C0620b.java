package com.alipay.sdk.encrypt;

import com.alipay.sdk.util.C0646c;
import com.facebook.stetho.common.Utf8Charset;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.alipay.sdk.encrypt.b */
public class C0620b {
    /* renamed from: a */
    public static String m900a(String str, String str2) {
        return C0620b.m899a(1, str, str2);
    }

    /* renamed from: b */
    public static String m901b(String str, String str2) {
        return C0620b.m899a(2, str, str2);
    }

    /* renamed from: a */
    public static String m899a(int i, String str, String str2) {
        try {
            byte[] a;
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "DES");
            Cipher instance = Cipher.getInstance("DES");
            instance.init(i, secretKeySpec);
            if (i == 2) {
                a = C0619a.m896a(str);
            } else {
                a = str.getBytes(Utf8Charset.NAME);
            }
            byte[] doFinal = instance.doFinal(a);
            if (i == 2) {
                return new String(doFinal);
            }
            return C0619a.m894a(doFinal);
        } catch (Exception e) {
            C0646c.m1016a(e);
            return null;
        }
    }
}
