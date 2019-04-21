package com.baidu.android.pushservice.p039k;

import com.baidu.android.pushservice.p036h.C1425a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.baidu.android.pushservice.k.f */
public class C1472f {
    /* renamed from: a */
    public static String m6715a(byte[] bArr, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (z) {
                toHexString = toHexString.toUpperCase();
            }
            if (toHexString.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(toHexString).append(str);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m6716a(byte[] bArr, boolean z) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return C1472f.m6715a(instance.digest(), "", z);
        } catch (NoSuchAlgorithmException e) {
            C1425a.m6440a("MD5Util", e);
            return null;
        }
    }
}
