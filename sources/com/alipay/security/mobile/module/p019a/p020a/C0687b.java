package com.alipay.security.mobile.module.p019a.p020a;

import com.alipay.security.mobile.module.p019a.C0689a;
import com.facebook.stetho.common.Utf8Charset;
import java.security.MessageDigest;

/* renamed from: com.alipay.security.mobile.module.a.a.b */
public final class C0687b {
    /* renamed from: a */
    public static String m1157a(String str) {
        try {
            if (C0689a.m1169a(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes(Utf8Charset.NAME));
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
