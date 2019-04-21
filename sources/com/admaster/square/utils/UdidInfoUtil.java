package com.admaster.square.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Environment;
import android.text.TextUtils;
import com.admaster.square.api.C0486n;
import com.admaster.square.p008a.AdMasterCollect;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import org.json.JSONObject;

/* renamed from: com.admaster.square.utils.s */
public class UdidInfoUtil {
    /* renamed from: a */
    private static String f310a = null;

    /* renamed from: a */
    public static String m480a(Context context, String str, String... strArr) {
        try {
            String a;
            String a2;
            String str2;
            String a3 = UdidInfoUtil.m477a(1, context, str);
            if (TextUtils.isEmpty(a3)) {
                a = UdidInfoUtil.m477a(2, context, str);
                if (TextUtils.isEmpty(a)) {
                    a2 = UdidInfoUtil.m479a(context, str);
                    if (TextUtils.isEmpty(a2)) {
                        str2 = a2;
                        a2 = a;
                        a = null;
                    } else {
                        str2 = a2;
                        String str3 = a;
                        a = a2;
                        a2 = str3;
                    }
                } else {
                    a2 = a;
                    str2 = null;
                }
            } else {
                a2 = null;
                str2 = null;
                a = a3;
            }
            if (!TextUtils.isEmpty(a)) {
                return AndroidUtil.m395b(a);
            }
            if (str.equalsIgnoreCase("mac")) {
                a = UdidInfoUtil.m482a(NetWorkInfoUtil.m454c(context));
            } else if (str.equalsIgnoreCase("imei")) {
                a = UdidInfoUtil.m482a(GSMInfoUtil.m444a(context));
            } else if (str.equalsIgnoreCase("imsi")) {
                a = UdidInfoUtil.m482a(GSMInfoUtil.m446c(context));
            } else if (str.equalsIgnoreCase("androidID")) {
                a = UdidInfoUtil.m482a(OSInfoUtil.m463c(context));
            } else if (str.equalsIgnoreCase("idfa")) {
                a = Constant.f267i;
            } else if (str.equalsIgnoreCase("it")) {
                a = strArr[0];
            } else if (str.equalsIgnoreCase("device_md5")) {
                a = UdidInfoUtil.m478a(context);
            } else if (str.equalsIgnoreCase("MAC1")) {
                a = AdMasterCollect.m247a(context).mo7722e();
            }
            if (TextUtils.isEmpty(a)) {
                return a;
            }
            if (str.equalsIgnoreCase("it") && Constant.f265g != null) {
                Constant.f265g.mo7775a(a);
            }
            if (!a.equalsIgnoreCase(str2)) {
                UdidInfoUtil.m503e(context, str, AndroidUtil.m391a(a));
            }
            if (!a.equalsIgnoreCase(a2)) {
                UdidInfoUtil.m493b(context, str, AndroidUtil.m391a(a));
            }
            if (a.equalsIgnoreCase(a3)) {
                return a;
            }
            UdidInfoUtil.m488a(str, AndroidUtil.m391a(a));
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m476a() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UdidInfoUtil.m482a(Constant.f265g.mo7776b())).append('|').append(UdidInfoUtil.m482a(Constant.f265g.mo7778c())).append('|').append(UdidInfoUtil.m482a(Constant.f265g.mo7782e())).append('|').append(UdidInfoUtil.m482a(Constant.f265g.mo7784f()));
            return C0493n.m447a(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private static void m503e(Context context, String str, String str2) {
        try {
            JSONObject a = UdidInfoUtil.m483a(0, CustomPreferenceUtil.m409a(context, "sp_store", "sp_json"), str, str2);
            if (a != null && a.length() > 0) {
                CustomPreferenceUtil.m411a(context, "sp_store", "sp_json", AndroidUtil.m391a(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    private static void m492b(Context context, String str) {
        int i = 0;
        try {
            File file;
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            UdidInfoUtil.m487a(new File(absolutePath, Constant.f260b), str);
            File[] listFiles = Environment.getExternalStorageDirectory().listFiles();
            for (File file2 : listFiles) {
                if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().equalsIgnoreCase(absolutePath)) {
                    File file3 = new File(file2, Constant.f260b);
                    if (file3.exists()) {
                        file3.delete();
                    }
                }
            }
            HashSet hashSet = new HashSet();
            while (i < listFiles.length) {
                file2 = listFiles[i];
                if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().equalsIgnoreCase(absolutePath) && !hashSet.contains(file2.getAbsolutePath())) {
                    UdidInfoUtil.m487a(new File(file2, Constant.f260b), str);
                    if (hashSet.size() < Constant.f262d) {
                        hashSet.add(file2.getAbsolutePath());
                    } else {
                        return;
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private static String m497c(Context context) {
        try {
            if (f310a == null) {
                Sensor[] sensorArr = new Sensor[64];
                for (Sensor sensor : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
                    if (sensor.getType() < sensorArr.length && sensor.getType() >= 0) {
                        sensorArr[sensor.getType()] = sensor;
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < sensorArr.length; i++) {
                    if (sensorArr[i] != null) {
                        stringBuffer.append(i).append('.').append(sensorArr[i].getVendor()).append('-').append(sensorArr[i].getName()).append('-').append(sensorArr[i].getVersion()).append(10);
                    }
                }
                f310a = String.valueOf(stringBuffer.toString().hashCode());
            }
            return f310a;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m487a(File file, String str) {
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
    public static String m482a(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /* renamed from: c */
    private static String m498c(Context context, String str) {
        String str2 = "";
        try {
            if (!"mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory(), Constant.f261c + OSInfoUtil.m461b(context) + "_" + str + UdidInfoUtil.m497c(context));
            if (file.exists() && file.canRead()) {
                return UdidInfoUtil.m481a(file);
            }
            return str2;
        } catch (Exception e) {
            return str2;
        }
    }

    /* renamed from: c */
    private static String m496c() {
        try {
            if (!"mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                return null;
            }
            String a = UdidInfoUtil.m481a(new File(Environment.getExternalStorageDirectory(), Constant.f260b));
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
                    a = UdidInfoUtil.m481a(new File(file, Constant.f260b));
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
    private static String m477a(int i, Context context, String str) {
        if (Constant.f265g == null) {
            UdidInfoUtil.m484a(i, context);
        }
        if (Constant.f265g == null) {
            return null;
        }
        if (str.equalsIgnoreCase("mac")) {
            return Constant.f265g.mo7776b();
        }
        if (str.equalsIgnoreCase("imsi")) {
            return Constant.f265g.mo7780d();
        }
        if (str.equalsIgnoreCase("imei")) {
            return Constant.f265g.mo7778c();
        }
        if (str.equalsIgnoreCase("androidID")) {
            return Constant.f265g.mo7782e();
        }
        if (str.equalsIgnoreCase("idfa")) {
            return Constant.f265g.mo7784f();
        }
        if (str.equalsIgnoreCase("it")) {
            return Constant.f265g.mo7774a();
        }
        if (str.equalsIgnoreCase("MAC1")) {
            return Constant.f265g.mo7788h();
        }
        if (str.equalsIgnoreCase("device_md5")) {
            return Constant.f265g.mo7786g();
        }
        return null;
    }

    /* renamed from: a */
    public static void m489a(JSONObject jSONObject, Context context) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.length() > 0) {
            Constant.f265g = new C0486n();
            String str = "";
            try {
                str = (String) jSONObject.get("imsi");
            } catch (Exception e) {
                str = "";
            }
            try {
                Constant.f265g.mo7781d(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("imei");
                } catch (Exception e2) {
                    str = "";
                }
                Constant.f265g.mo7779c(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("androidID");
                } catch (Exception e3) {
                    str = "";
                }
                Constant.f265g.mo7783e(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("it");
                } catch (Exception e4) {
                    str = "";
                }
                Constant.f265g.mo7775a(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("idfa");
                } catch (Exception e5) {
                    str = "";
                }
                Constant.f265g.mo7785f(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("mac");
                } catch (Exception e6) {
                    str = "";
                }
                Constant.f265g.mo7777b(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("MAC1");
                } catch (Exception e7) {
                    str = "";
                }
                Constant.f265g.mo7789h(AndroidUtil.m395b(str));
                str = "";
                try {
                    str = (String) jSONObject.get("device_md5");
                } catch (Exception e8) {
                    str = "";
                }
                String a = UdidInfoUtil.m476a();
                if (TextUtils.isEmpty(a)) {
                    Constant.f265g = null;
                } else if (a.equalsIgnoreCase(str)) {
                    Constant.f265g.mo7787g(str);
                } else {
                    Constant.f265g = null;
                }
            } catch (Exception e9) {
                e9.printStackTrace();
                Constant.f265g = null;
            }
        }
    }

    /* renamed from: a */
    public static String m478a(Context context) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UdidInfoUtil.m482a(NetWorkInfoUtil.m454c(context))).append('|').append(UdidInfoUtil.m482a(GSMInfoUtil.m444a(context))).append('|').append(UdidInfoUtil.m482a(OSInfoUtil.m463c(context))).append("|").append(UdidInfoUtil.m482a(Constant.f267i));
            return C0493n.m447a(stringBuilder.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m484a(int i, Context context) {
        String d;
        if (i == 1) {
            try {
                d = UdidInfoUtil.m500d();
            } catch (Exception e) {
                e.printStackTrace();
                Constant.f265g = null;
                return;
            }
        } else if (i == 2) {
            d = UdidInfoUtil.m496c();
        } else {
            CharSequence d2 = null;
        }
        if (!TextUtils.isEmpty(d2)) {
            UdidInfoUtil.m489a(JSONObjectInstrumentation.init(AndroidUtil.m395b(d2)), context);
        }
    }

    /* renamed from: a */
    public static JSONObject m483a(int i, String str, String str2, String str3) {
        JSONObject jSONObject = null;
        try {
            if (TextUtils.isEmpty(str)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = JSONObjectInstrumentation.init(AndroidUtil.m395b(str));
            }
            if (i == 0) {
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put(str2, AndroidUtil.m391a(AndroidUtil.m395b(str3)));
                }
            } else if (i == 1) {
                jSONObject.put(str2, str3);
            }
        } catch (Exception e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m485a(Context context, String str, String str2) {
        try {
            if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                JSONObject a;
                try {
                    a = UdidInfoUtil.m483a(0, UdidInfoUtil.m500d(), str2, UdidInfoUtil.m501d(context, str));
                    if (a != null && a.length() > 0) {
                        UdidInfoUtil.m495b(AndroidUtil.m391a(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
                    }
                } catch (Exception e) {
                }
                try {
                    a = UdidInfoUtil.m483a(0, UdidInfoUtil.m496c(), str2, UdidInfoUtil.m498c(context, str));
                    if (a != null && a.length() > 0) {
                        String jSONObjectInstrumentation;
                        if (a instanceof JSONObject) {
                            jSONObjectInstrumentation = JSONObjectInstrumentation.toString(a);
                        } else {
                            jSONObjectInstrumentation = a.toString();
                        }
                        UdidInfoUtil.m492b(context, AndroidUtil.m391a(jSONObjectInstrumentation));
                    }
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
        }
    }

    /* renamed from: d */
    private static String m500d() {
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
                        str = UdidInfoUtil.m481a(new File(file, Constant.f260b));
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

    /* renamed from: d */
    private static String m501d(Context context, String str) {
        try {
            if (!"mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                return null;
            }
            File[] listFiles = new File("/").listFiles();
            if (listFiles == null) {
                return null;
            }
            String str2 = Constant.f261c + OSInfoUtil.m461b(context) + "_" + str;
            String str3 = null;
            for (File file : listFiles) {
                if (file.isDirectory() && !"/sdcard".equalsIgnoreCase(file.getAbsolutePath())) {
                    File file2 = new File(file, str2);
                    if (file2.exists() && file2.canRead()) {
                        str3 = UdidInfoUtil.m481a(file2);
                        if (!TextUtils.isEmpty(str3)) {
                            return str3;
                        }
                    }
                    File[] listFiles2 = file.listFiles();
                    if (listFiles2 != null) {
                        for (File file3 : listFiles2) {
                            if (file3.isDirectory()) {
                                File file4 = new File(file3, str2);
                                if (file4.exists() && file4.canRead()) {
                                    str3 = UdidInfoUtil.m481a(file4);
                                    if (!TextUtils.isEmpty(str3)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return str3;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m490b() {
        try {
            if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                File[] listFiles = new File("/").listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.isDirectory()) {
                            File[] listFiles2 = file.listFiles();
                            if (listFiles2 != null) {
                                for (File file2 : listFiles2) {
                                    if (file2.isDirectory()) {
                                        File[] listFiles3 = file2.listFiles();
                                        if (listFiles3 != null) {
                                            for (File file3 : listFiles3) {
                                                if (file3.exists() && file3.getName().contains(Constant.f261c)) {
                                                    file3.delete();
                                                }
                                            }
                                        }
                                    }
                                    if (file2.exists() && file2.getName().contains(Constant.f261c)) {
                                        file2.delete();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public static void m495b(String str) {
        int i = 0;
        try {
            if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
                File[] listFiles = new File("/").listFiles();
                if (listFiles != null) {
                    File file;
                    for (File file2 : listFiles) {
                        if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().contains("/sdcard")) {
                            File file3 = new File(file2, Constant.f260b);
                            if (file3.exists()) {
                                file3.delete();
                            }
                        }
                    }
                    HashSet hashSet = new HashSet();
                    while (i < listFiles.length) {
                        file2 = listFiles[i];
                        if (file2.isDirectory() && file2.canWrite() && !file2.getAbsolutePath().contains("/sdcard") && !hashSet.contains(file2.getAbsolutePath())) {
                            UdidInfoUtil.m487a(new File(file2, Constant.f260b), str);
                            if (hashSet.size() < Constant.f262d) {
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

    /* renamed from: a */
    public static void m488a(String str, String str2) {
        try {
            JSONObject a = UdidInfoUtil.m483a(1, UdidInfoUtil.m500d(), str, str2);
            if (a != null && a.length() > 0) {
                UdidInfoUtil.m495b(AndroidUtil.m391a(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: b */
    public static void m493b(Context context, String str, String str2) {
        try {
            JSONObject a = UdidInfoUtil.m483a(1, UdidInfoUtil.m496c(), str, str2);
            if (a != null && a.length() > 0) {
                UdidInfoUtil.m492b(context, AndroidUtil.m391a(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static String m481a(File file) {
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

    /* renamed from: a */
    public static void m486a(Context context, String str, String str2, String str3) {
        String f = UdidInfoUtil.m504f(context, new StringBuilder(String.valueOf(str)).append(str2).toString(), new StringBuilder(String.valueOf(str)).append(str2).toString());
        UdidInfoUtil.m502d(context, new StringBuilder(String.valueOf(str)).append(str2).toString(), new StringBuilder(String.valueOf(str)).append(str2).toString());
        if (!TextUtils.isEmpty(f)) {
            try {
                JSONObject a = UdidInfoUtil.m483a(0, CustomPreferenceUtil.m409a(context, "sp_store", "sp_json"), str3, AndroidUtil.m391a(f));
                if (a != null && a.length() > 0) {
                    CustomPreferenceUtil.m411a(context, "sp_store", "sp_json", AndroidUtil.m391a(!(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a)));
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public static void m494b(Context context, String str, String str2, String str3) {
        String g = UdidInfoUtil.m505g(context, str, str2);
        UdidInfoUtil.m502d(context, str, EncryptionUtil.m420a(str2));
        if (!TextUtils.isEmpty(g)) {
            CustomPreferenceUtil.m411a(context, "sp_store", str3, g);
        }
    }

    /* renamed from: c */
    public static void m499c(Context context, String str, String str2) {
        try {
            boolean b = CustomPreferenceUtil.m414b(context, str, str2);
            UdidInfoUtil.m502d(context, str, str2);
            if (b) {
                CustomPreferenceUtil.m412a(context, "sp_store", str2, b);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static String m479a(Context context, String str) {
        try {
            String a = CustomPreferenceUtil.m409a(context, "sp_store", "sp_json");
            if (!TextUtils.isEmpty(a)) {
                UdidInfoUtil.m489a(JSONObjectInstrumentation.init(AndroidUtil.m395b(a)), context);
            }
            if (Constant.f265g == null) {
                return null;
            }
            if (str.equalsIgnoreCase("mac")) {
                return Constant.f265g.mo7776b();
            }
            if (str.equalsIgnoreCase("imsi")) {
                return Constant.f265g.mo7780d();
            }
            if (str.equalsIgnoreCase("imei")) {
                return Constant.f265g.mo7778c();
            }
            if (str.equalsIgnoreCase("androidID")) {
                return Constant.f265g.mo7782e();
            }
            if (str.equalsIgnoreCase("idfa")) {
                return Constant.f265g.mo7784f();
            }
            if (str.equalsIgnoreCase("it")) {
                return Constant.f265g.mo7774a();
            }
            if (str.equalsIgnoreCase("device_md5")) {
                return Constant.f265g.mo7786g();
            }
            if (str.equalsIgnoreCase("MAC1")) {
                return Constant.f265g.mo7788h();
            }
            return null;
        } catch (Exception e) {
            Constant.f265g = null;
            return null;
        }
    }

    /* renamed from: b */
    public static void m491b(Context context) {
        int i = 0;
        try {
            int i2;
            String[] strArr = new String[]{"dev_mac", "imei", "imsi", "it", "androidID", "androidIDFA"};
            String[] strArr2 = new String[]{"mac", "imei", "imsi", "it", "androidID", "idfa"};
            for (i2 = 0; i2 < strArr.length; i2++) {
                UdidInfoUtil.m486a(context, "adm_convimobi_sp_", strArr[i2], strArr2[i2]);
            }
            String[] strArr3 = new String[]{"m2id", "ch"};
            for (i2 = 0; i2 < strArr3.length; i2++) {
                UdidInfoUtil.m494b(context, "sp_isActive", strArr3[i2], strArr3[i2]);
            }
            UdidInfoUtil.m499c(context, "sp_isActive", "is_install");
            while (i < strArr.length) {
                UdidInfoUtil.m485a(context, strArr[i], strArr2[i]);
                i++;
            }
            UdidInfoUtil.m490b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f */
    private static String m504f(Context context, String str, String str2) {
        return CustomPreferenceUtil.m417e(context, str, str2);
    }

    /* renamed from: g */
    private static String m505g(Context context, String str, String str2) {
        return CustomPreferenceUtil.m418f(context, str, str2);
    }

    /* renamed from: d */
    public static void m502d(Context context, String str, String str2) {
        context.getSharedPreferences(str, 0).edit().remove(str2).commit();
    }
}
