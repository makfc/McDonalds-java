package com.admaster.jice.p006c;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.admaster.jice.p007d.Md5Util;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import org.json.JSONObject;

/* renamed from: com.admaster.jice.c.f */
public class StoreUtil {
    /* renamed from: a */
    public static String m197a(Context context, String str, String... strArr) {
        Exception e;
        Exception exception;
        CharSequence charSequence = null;
        String a;
        try {
            String str2;
            String a2 = StoreUtil.m195a(1, context, str);
            Object charSequence2;
            if (TextUtils.isEmpty(a2)) {
                a = StoreUtil.m195a(2, context, str);
                if (TextUtils.isEmpty(a)) {
                    str2 = a;
                } else {
                    str2 = a;
                    charSequence2 = a;
                }
            } else {
                str2 = null;
                charSequence2 = a2;
            }
            CharSequence charSequence3;
            try {
                if (!TextUtils.isEmpty(charSequence2)) {
                    return StoreUtil.m209c(charSequence2);
                }
                if (str.equalsIgnoreCase("mac")) {
                    a = StoreUtil.m199a(DeviceInfoUtil.m179d(context));
                } else if (str.equalsIgnoreCase("imei")) {
                    a = StoreUtil.m199a(DeviceInfoUtil.m171a(context));
                } else if (str.equalsIgnoreCase("imsi")) {
                    a = StoreUtil.m199a(DeviceInfoUtil.m183g(context));
                } else if (str.equalsIgnoreCase("androidID")) {
                    a = StoreUtil.m199a(DeviceInfoUtil.m190n(context));
                } else if (str.equalsIgnoreCase("idfa")) {
                    a = ConstantAPI.f128d;
                } else if (str.equalsIgnoreCase("mac1")) {
                    a = DeviceInfoUtil.m180e();
                } else if (str.equalsIgnoreCase("device_md5")) {
                    a = StoreUtil.m196a(context);
                } else {
                    charSequence3 = charSequence2;
                }
                try {
                    if (TextUtils.isEmpty(a)) {
                        return a;
                    }
                    if (!a.equalsIgnoreCase(str2)) {
                        StoreUtil.m203a(str, StoreUtil.m206b(a));
                    }
                    if (a.equalsIgnoreCase(a2)) {
                        return a;
                    }
                    StoreUtil.m207b(str, StoreUtil.m206b(a));
                    return a;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return a;
                }
            } catch (Exception e3) {
                exception = e3;
                charSequence3 = charSequence2;
                e = exception;
                e.printStackTrace();
                return a;
            }
        } catch (Exception e32) {
            exception = e32;
            a = null;
            e = exception;
            e.printStackTrace();
            return a;
        }
    }

    /* renamed from: a */
    public static String m196a(Context context) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StoreUtil.m199a(DeviceInfoUtil.m179d(context))).append('|').append(StoreUtil.m199a(DeviceInfoUtil.m171a(context))).append('|').append(StoreUtil.m199a(DeviceInfoUtil.m190n(context))).append("|").append(StoreUtil.m199a(ConstantAPI.f128d));
            return Md5Util.m241a(stringBuilder.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m199a(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /* renamed from: b */
    public static String m206b(String str) {
        try {
            return URLEncoder.encode(str, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public static String m209c(String str) {
        try {
            return URLDecoder.decode(str, Utf8Charset.NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static void m204a(JSONObject jSONObject, Context context) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.length() > 0) {
            ConstantAPI.f125a = new NativeBean();
            String str = "";
            try {
                str = (String) jSONObject.get("imsi");
            } catch (Exception e) {
                str = "";
            }
            try {
                ConstantAPI.f125a.setImsi(StoreUtil.m209c(str));
                str = "";
                try {
                    str = (String) jSONObject.get("imei");
                } catch (Exception e2) {
                    str = "";
                }
                ConstantAPI.f125a.setImei(StoreUtil.m209c(str));
                str = "";
                try {
                    str = (String) jSONObject.get("androidID");
                } catch (Exception e3) {
                    str = "";
                }
                ConstantAPI.f125a.setAndroidId(StoreUtil.m209c(str));
                str = "";
                try {
                    str = (String) jSONObject.get("idfa");
                } catch (Exception e4) {
                    str = "";
                }
                ConstantAPI.f125a.setAndroidIDFA(StoreUtil.m209c(str));
                str = "";
                try {
                    str = (String) jSONObject.get("mac");
                } catch (Exception e5) {
                    str = "";
                }
                ConstantAPI.f125a.setDeviceMac(StoreUtil.m209c(str));
                str = "";
                try {
                    str = (String) jSONObject.get("mac1");
                } catch (Exception e6) {
                    str = "";
                }
                ConstantAPI.f125a.setMac1(StoreUtil.m209c(str));
                str = "";
                try {
                    str = (String) jSONObject.get("device_md5");
                } catch (Exception e7) {
                    str = "";
                }
                String a = StoreUtil.m194a();
                if (TextUtils.isEmpty(a)) {
                    ConstantAPI.f125a = null;
                } else if (a.equalsIgnoreCase(str)) {
                    ConstantAPI.f125a.setDeviceMd5(a);
                } else {
                    ConstantAPI.f125a = null;
                }
            } catch (Exception e8) {
                e8.printStackTrace();
                ConstantAPI.f125a = null;
            }
        }
    }

    /* renamed from: a */
    public static String m194a() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StoreUtil.m199a(ConstantAPI.f125a.getDeviceMac())).append('|').append(StoreUtil.m199a(ConstantAPI.f125a.getImei())).append('|').append(StoreUtil.m199a(ConstantAPI.f125a.getAndroidId())).append('|').append(StoreUtil.m199a(ConstantAPI.f125a.getAndroidIDFA()));
            return Md5Util.m241a(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m195a(int i, Context context, String str) {
        if (ConstantAPI.f125a == null) {
            StoreUtil.m201a(i, context);
        }
        if (ConstantAPI.f125a == null) {
            return null;
        }
        if (str.equalsIgnoreCase("mac")) {
            return ConstantAPI.f125a.getDeviceMac();
        }
        if (str.equalsIgnoreCase("imsi")) {
            return ConstantAPI.f125a.getImsi();
        }
        if (str.equalsIgnoreCase("imei")) {
            return ConstantAPI.f125a.getImei();
        }
        if (str.equalsIgnoreCase("androidID")) {
            return ConstantAPI.f125a.getAndroidId();
        }
        if (str.equalsIgnoreCase("idfa")) {
            return ConstantAPI.f125a.getAndroidIDFA();
        }
        if (str.equalsIgnoreCase("mac1")) {
            return ConstantAPI.f125a.getMac1();
        }
        if (str.equalsIgnoreCase("device_md5")) {
            return ConstantAPI.f125a.getDeviceMd5();
        }
        return null;
    }

    /* renamed from: a */
    public static void m201a(int i, Context context) {
        CharSequence c;
        if (i == 1) {
            try {
                c = StoreUtil.m208c();
            } catch (Exception e) {
                e.printStackTrace();
                ConstantAPI.f125a = null;
                return;
            }
        } else if (i == 2) {
            c = StoreUtil.m205b();
        } else {
            c = null;
        }
        if (!TextUtils.isEmpty(c)) {
            StoreUtil.m204a(JSONObjectInstrumentation.init(StoreUtil.m209c(c)), context);
        }
    }

    /* renamed from: e */
    private static void m211e(String str) {
        int i = 0;
        try {
            File file;
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            StoreUtil.m202a(new File(absolutePath, ConstantAPI.f126b), str);
            File[] listFiles = Environment.getExternalStorageDirectory().listFiles();
            for (File file2 : listFiles) {
                if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().equalsIgnoreCase(absolutePath)) {
                    File file3 = new File(file2, ConstantAPI.f126b);
                    if (file3.exists()) {
                        file3.delete();
                    }
                }
            }
            HashSet hashSet = new HashSet();
            while (i < listFiles.length) {
                file2 = listFiles[i];
                if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().equalsIgnoreCase(absolutePath) && !hashSet.contains(file2.getAbsolutePath())) {
                    StoreUtil.m202a(new File(file2, ConstantAPI.f126b), str);
                    if (hashSet.size() < ConstantAPI.f127c) {
                        hashSet.add(file2.getAbsolutePath());
                    } else {
                        return;
                    }
                }
                i++;
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static void m202a(File file, String str) {
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static void m203a(String str, String str2) {
        try {
            JSONObject a = StoreUtil.m200a(1, StoreUtil.m205b(), str, str2);
            if (a != null && a.length() > 0) {
                StoreUtil.m211e(StoreUtil.m206b(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m207b(String str, String str2) {
        try {
            JSONObject a = StoreUtil.m200a(1, StoreUtil.m208c(), str, str2);
            if (a != null && a.length() > 0) {
                StoreUtil.m210d(StoreUtil.m206b(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public static void m210d(String str) {
        int i = 0;
        try {
            if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                File[] listFiles = new File("/").listFiles();
                if (listFiles != null) {
                    File file;
                    for (File file2 : listFiles) {
                        if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().contains("/sdcard")) {
                            File file3 = new File(file2, ConstantAPI.f126b);
                            if (file3.exists()) {
                                file3.delete();
                            }
                        }
                    }
                    HashSet hashSet = new HashSet();
                    while (i < listFiles.length) {
                        file2 = listFiles[i];
                        if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().contains("/sdcard") && !hashSet.contains(file2.getAbsolutePath())) {
                            StoreUtil.m202a(new File(file2, ConstantAPI.f126b), str);
                            if (hashSet.size() < ConstantAPI.f127c) {
                                hashSet.add(file2.getAbsolutePath());
                            } else {
                                return;
                            }
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    private static String m205b() {
        try {
            if (!"mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                return null;
            }
            String a = StoreUtil.m198a(new File(Environment.getExternalStorageDirectory(), ConstantAPI.f126b));
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
            File[] listFiles = Environment.getExternalStorageDirectory().listFiles();
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (listFiles == null || listFiles.length <= 0) {
                return a;
            }
            for (File file : listFiles) {
                if (file.isDirectory() && file.canRead() && !file.getAbsolutePath().equalsIgnoreCase(absolutePath)) {
                    a = StoreUtil.m198a(new File(file, ConstantAPI.f126b));
                    if (!TextUtils.isEmpty(a)) {
                        return a;
                    }
                }
            }
            return a;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    private static String m198a(File file) {
        try {
            if (file.exists()) {
                byte[] bArr = new byte[Long.valueOf(file.length()).intValue()];
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bArr);
                fileInputStream.close();
                return new String(bArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: c */
    private static String m208c() {
        try {
            if (!"mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                return null;
            }
            String str;
            File[] listFiles = new File("/").listFiles();
            if (listFiles != null) {
                str = null;
                for (File file : listFiles) {
                    if (file.isDirectory() && file.canRead() && !file.getAbsolutePath().contains("/sdcard")) {
                        str = StoreUtil.m198a(new File(file, ConstantAPI.f126b));
                        if (!TextUtils.isEmpty(str)) {
                            break;
                        }
                    }
                }
            } else {
                str = null;
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m200a(int i, String str, String str2, String str3) {
        JSONObject jSONObject = null;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = JSONObjectInstrumentation.init(StoreUtil.m209c(str));
            }
            if (i == 0) {
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put(str2, StoreUtil.m206b(StoreUtil.m209c(str3)));
                }
            } else if (i == 1) {
                jSONObject.put(str2, str3);
            }
        } catch (Exception e) {
        }
        return jSONObject;
    }
}
