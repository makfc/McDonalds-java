package com.p044ta.utdid2.p055a.p056a;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.ta.utdid2.a.a.a */
public class C4311a {
    /* renamed from: a */
    public static String m7750a(String str) {
        byte[] a;
        try {
            a = C4311a.m7755a(C4311a.m7753a(), str.getBytes());
        } catch (Exception e) {
            a = null;
        }
        if (a != null) {
            return C4311a.m7751a(a);
        }
        return null;
    }

    /* renamed from: b */
    public static String m7756b(String str) {
        try {
            return new String(C4311a.m7757b(C4311a.m7753a(), C4311a.m7750a(str)));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m7753a() throws Exception {
        return C4320e.m7761a(new byte[]{(byte) 33, (byte) 83, (byte) -50, (byte) -89, (byte) -84, (byte) -114, (byte) 80, (byte) 99, (byte) 10, (byte) 63, (byte) 22, (byte) -65, (byte) -11, (byte) 30, (byte) 101, (byte) -118});
    }

    /* renamed from: a */
    private static byte[] m7755a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    /* renamed from: b */
    private static byte[] m7757b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    /* renamed from: a */
    private static byte[] m8661a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }

    /* renamed from: a */
    private static String m7751a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte a : bArr) {
            C4311a.m7752a(stringBuffer, a);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static void m7752a(StringBuffer stringBuffer, byte b) {
        String str = "0123456789ABCDEF";
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
    }
}
