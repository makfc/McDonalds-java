package com.baidu.android.pushservice.p039k;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.baidu.android.pushservice.k.h */
public final class C1474h {
    /* renamed from: a */
    public static byte[] m6720a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
