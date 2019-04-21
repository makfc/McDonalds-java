package com.alipay.security.mobile.module.p019a;

import android.os.Environment;
import android.util.Base64;
import com.alipay.security.mobile.module.p019a.p020a.C0686a;
import com.facebook.stetho.common.Utf8Charset;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.alipay.security.mobile.module.a.a */
public final class C0689a {
    /* renamed from: a */
    public static File m1166a() {
        try {
            return (File) Environment.class.getMethod(new String(C0686a.m1156a("Z2V0RXh0ZXJuYWxTdG9yYWdlRGlyZWN0b3J5")), new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m1167a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static String m1168a(Map<String, String> map, String str, String str2) {
        if (map == null) {
            return str2;
        }
        String str3 = (String) map.get(str);
        return str3 != null ? str3 : str2;
    }

    /* renamed from: a */
    public static boolean m1169a(String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m1170a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    /* renamed from: b */
    public static String m1171b(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            return str2;
        }
    }

    /* renamed from: b */
    public static boolean m1172b(String str) {
        return !C0689a.m1169a(str);
    }

    /* renamed from: c */
    public static boolean m1173c(String str) {
        for (byte b : str.getBytes()) {
            if ((b >= (byte) 0 && b <= (byte) 31) || b >= Byte.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    public static String m1174d(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: e */
    public static String m1175e(String str) {
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

    /* renamed from: f */
    public static String m1176f(String str) {
        try {
            byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(str.length()).array();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(Utf8Charset.NAME));
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            byte[] bArr = new byte[(byteArrayOutputStream.toByteArray().length + 4)];
            System.arraycopy(array, 0, bArr, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, bArr, 4, byteArrayOutputStream.toByteArray().length);
            return Base64.encodeToString(bArr, 8);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: g */
    public static String m1177g(String str) {
        String str2 = "";
        if (C0689a.m1169a(str)) {
            return "";
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    gZIPOutputStream.write(bArr, 0, read);
                } else {
                    gZIPOutputStream.flush();
                    gZIPOutputStream.close();
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    byteArrayInputStream.close();
                    return new String(Base64.encode(toByteArray, 2));
                }
            }
        } catch (Exception e) {
            return str2;
        }
    }
}
