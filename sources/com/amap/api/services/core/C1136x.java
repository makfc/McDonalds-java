package com.amap.api.services.core;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/* compiled from: ClientInfo */
/* renamed from: com.amap.api.services.core.x */
public class C1136x {
    /* renamed from: a */
    public static String m5093a(Context context, C1081ac c1081ac, Map<String, String> map) {
        try {
            String b = C1136x.m5096b(context, c1081ac, map);
            if ("".equals(b)) {
                return null;
            }
            return C1136x.m5097b(context, b.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            C1099ax.m4800a(e, "CInfo", "rsaInfo");
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private static String m5098c(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = C1082ad.m4711a(context);
        if (a == null) {
            return null;
        }
        byte[] a2 = C1139z.m5128a(encoded, a);
        encoded = C1139z.m5129a(encoded, bArr);
        byte[] bArr2 = new byte[(a2.length + encoded.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(encoded, 0, bArr2, a2.length, encoded.length);
        a2 = C1082ad.m4712a(bArr2);
        if (a2 != null) {
            return C1139z.m5127a(a2);
        }
        return "";
    }

    /* renamed from: a */
    public static String m5095a(Context context, byte[] bArr) {
        try {
            return C1136x.m5098c(context, bArr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (NoSuchPaddingException e3) {
            e3.printStackTrace();
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
        } catch (InvalidKeySpecException e6) {
            e6.printStackTrace();
        } catch (CertificateException e7) {
            e7.printStackTrace();
        } catch (IOException e8) {
            e8.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    /* renamed from: b */
    public static String m5097b(Context context, byte[] bArr) {
        try {
            return C1136x.m5098c(context, bArr);
        } catch (InvalidKeyException e) {
            C1099ax.m4800a(e, "CInfo", "AESData");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            C1099ax.m4800a(e2, "CInfo", "AESData");
            e2.printStackTrace();
        } catch (NoSuchPaddingException e22) {
            C1099ax.m4800a(e22, "CInfo", "AESData");
            e22.printStackTrace();
        } catch (IllegalBlockSizeException e222) {
            C1099ax.m4800a(e222, "CInfo", "AESData");
            e222.printStackTrace();
        } catch (BadPaddingException e2222) {
            C1099ax.m4800a(e2222, "CInfo", "AESData");
            e2222.printStackTrace();
        } catch (InvalidKeySpecException e22222) {
            C1099ax.m4800a(e22222, "CInfo", "AESData");
            e22222.printStackTrace();
        } catch (CertificateException e222222) {
            C1099ax.m4800a(e222222, "CInfo", "AESData");
            e222222.printStackTrace();
        } catch (IOException e2222222) {
            C1099ax.m4800a(e2222222, "CInfo", "AESData");
            e2222222.printStackTrace();
        } catch (Throwable e22222222) {
            C1099ax.m4800a(e22222222, "CInfo", "AESData");
            e22222222.printStackTrace();
        }
        return "";
    }

    /* renamed from: a */
    public static String m5092a(Context context, C1081ac c1081ac) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(C1138y.m5119q(context)).append("\",\"av\":\"").append(c1081ac.f3623a).append("\",\"pro\":\"").append(c1081ac.f3625c).append("\",\"ed\":\"").append(c1081ac.mo11991d()).append("\",\"nt\":\"").append(C1138y.m5113k(context)).append("\",\"np\":\"").append(C1138y.m5118p(context)).append("\",\"mnc\":\"").append(C1138y.m5107e(context)).append("\",\"lnt\":\"").append(C1138y.m5108f(context)).append("\",\"ant\":\"").append(C1138y.m5110h(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m5091a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(C1134v.m5087f(context)).append("\",\"ct\":\"android\",\"ime\":\"").append(C1138y.m5115m(context)).append("\",\"pkg\":\"").append(C1134v.m5083b(context)).append("\",\"mod\":\"").append(Build.MODEL).append("\",\"apn\":\"").append(C1134v.m5081a(context)).append("\",\"apv\":\"").append(C1134v.m5084c(context)).append("\",\"sv\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            C1099ax.m4800a(th, "CInfo", "getPublicJSONInfo");
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private static String m5096b(Context context, C1081ac c1081ac, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String m = C1138y.m5115m(context);
            stringBuilder.append("ct=android");
            stringBuilder.append("&ime=").append(m);
            stringBuilder.append("&pkg=").append(C1134v.m5083b(context));
            stringBuilder.append("&mod=");
            stringBuilder.append(Build.MODEL);
            stringBuilder.append("&apn=").append(C1134v.m5081a(context));
            stringBuilder.append("&apv=").append(C1134v.m5084c(context));
            stringBuilder.append("&sv=");
            stringBuilder.append(VERSION.RELEASE);
            stringBuilder.append("&sim=").append(C1138y.m5116n(context));
            stringBuilder.append("&av=").append(c1081ac.f3623a);
            stringBuilder.append("&pro=").append(c1081ac.f3625c);
            stringBuilder.append("&cid=").append(C1138y.m5106d(context));
            stringBuilder.append("&dmac=").append(C1138y.m5105c(context));
            stringBuilder.append("&wmac=").append(C1138y.m5104b(context));
            stringBuilder.append("&nt=");
            stringBuilder.append(C1138y.m5114l(context));
            m = C1138y.m5117o(context);
            stringBuilder.append("&np=");
            stringBuilder.append(m);
            stringBuilder.append("&ia=1&");
            m = C1138y.m5099a(context);
            if (m == null) {
                m = "";
            }
            stringBuilder.append("utd=").append(m).append("&");
            m = C1134v.m5087f(context);
            if (m != null && m.length() > 0) {
                stringBuilder.append("key=");
                stringBuilder.append(m);
                stringBuilder.append("&");
            }
            stringBuilder.append("ctm=" + System.currentTimeMillis());
            stringBuilder.append("&re=" + C1138y.m5112j(context));
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    stringBuilder.append("&").append((String) entry.getKey()).append("=").append((String) entry.getValue());
                }
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "CInfo", "InitXInfo");
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m5090a() {
        Throwable th;
        String str;
        Throwable th2;
        String str2 = null;
        try {
            str2 = String.valueOf(System.currentTimeMillis());
            try {
                int length = str2.length();
                return str2.substring(0, length - 2) + "1" + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
        }
        C1099ax.m4800a(th2, "CInfo", "getTS");
        th2.printStackTrace();
        return str;
    }

    /* renamed from: a */
    public static String m5094a(Context context, String str, String str2) {
        String str3 = null;
        try {
            return C1077aa.m4682a(C1134v.m5085d(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "CInfo", "Scode");
            th.printStackTrace();
            return str3;
        }
    }
}
