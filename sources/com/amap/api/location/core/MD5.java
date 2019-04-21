package com.amap.api.location.core;

import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.amap.api.location.core.g */
public class MD5 {
    /* renamed from: a */
    private static final String[] f939a = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", DCSProfile.INDICATOR_FALSE, "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", DCSProfile.INDICATOR_TRUE, "Z"};

    /* renamed from: a */
    public static String m1481a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m1480a(String str) {
        MessageDigest instance;
        byte[] bArr = null;
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            instance = null;
        }
        if (instance != null) {
            try {
                instance.update(str.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        if (instance != null) {
            bArr = instance.digest();
        }
        return MD5.m1481a(bArr);
    }
}
