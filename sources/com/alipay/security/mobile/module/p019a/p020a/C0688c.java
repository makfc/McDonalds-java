package com.alipay.security.mobile.module.p019a.p020a;

import com.alipay.security.mobile.module.p019a.C0689a;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.alipay.security.mobile.module.a.a.c */
public final class C0688c {
    /* renamed from: a */
    private static String f723a = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

    /* renamed from: a */
    public static String m1158a() {
        String str = new String();
        for (int i = 0; i < f723a.length() - 1; i += 4) {
            str = str + f723a.charAt(i);
        }
        return str;
    }

    /* renamed from: a */
    public static String m1159a(String str, String str2) {
        try {
            PBEKeySpec a = C0688c.m1160a(str);
            byte[] bytes = str2.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(a).getEncoded(), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            byte[] salt = a.getSalt();
            ByteBuffer allocate = ByteBuffer.allocate(salt.length + instance.getOutputSize(bytes.length));
            allocate.put(salt);
            instance.doFinal(ByteBuffer.wrap(bytes), allocate);
            return C0688c.m1164b(allocate.array());
        } catch (Exception e) {
            try {
                return C0688c.m1164b(C0688c.m1162a(C0688c.m1161a(str.getBytes()), str2.getBytes()));
            } catch (Exception e2) {
                return null;
            }
        }
    }

    /* renamed from: a */
    private static PBEKeySpec m1160a(String str) {
        Class cls = Class.forName(new String(C0686a.m1156a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object newInstance = cls.newInstance();
        Method method = cls.getMethod("nextBytes", new Class[]{new byte[16].getClass()});
        method.setAccessible(true);
        method.invoke(newInstance, new Object[]{r2});
        return new PBEKeySpec(str.toCharArray(), new byte[16], 10, 128);
    }

    /* renamed from: a */
    private static byte[] m1161a(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        Class cls = Class.forName(new String(C0686a.m1156a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object invoke = cls.getMethod("getInstance", new Class[]{String.class, String.class}).invoke(null, new Object[]{"SHA1PRNG", "Crypto"});
        Method method = cls.getMethod("setSeed", new Class[]{bArr.getClass()});
        method.setAccessible(true);
        method.invoke(invoke, new Object[]{bArr});
        KeyGenerator.class.getMethod("init", new Class[]{Integer.TYPE, cls}).invoke(instance, new Object[]{Integer.valueOf(128), invoke});
        return instance.generateKey().getEncoded();
    }

    /* renamed from: a */
    private static byte[] m1162a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return instance.doFinal(bArr2);
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m1163b(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec;
            Cipher instance;
            PBEKeySpec a = C0688c.m1160a(str);
            byte[] b = C0688c.m1165b(str2);
            if (b.length <= 16) {
                b = null;
            } else {
                secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(a.getPassword(), Arrays.copyOf(b, 16), 10, 128)).getEncoded(), "AES");
                instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
                b = instance.doFinal(b, 16, b.length - 16);
            }
            if (b == null) {
                throw new Exception();
            }
            String str3 = new String(b);
            if (C0689a.m1173c(str3)) {
                return str3;
            }
            try {
                byte[] a2 = C0688c.m1161a(str.getBytes());
                b = C0688c.m1165b(str2);
                secretKeySpec = new SecretKeySpec(a2, "AES");
                instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
                str3 = new String(instance.doFinal(b));
                if (C0689a.m1173c(str3)) {
                    return str3;
                }
            } catch (Exception e) {
            }
            return null;
        } catch (Exception e2) {
        }
    }

    /* renamed from: b */
    private static String m1164b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & 15));
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static byte[] m1165b(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }
}
