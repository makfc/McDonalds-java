package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.alipay.sdk.app.C0587i;
import com.alipay.sdk.app.C0588j;
import com.alipay.sdk.app.C0589k;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.cons.C0611a;
import com.alipay.sdk.data.C0615a.C0614a;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
/* renamed from: com.alipay.sdk.util.m */
public class C0657m {
    /* renamed from: e */
    public static final String[] f665e = new String[]{"10.1.5.1013151", "10.1.5.1013148"};
    /* renamed from: h */
    private static final char[] f666h = "0123456789ABCDEF".toCharArray();

    /* renamed from: com.alipay.sdk.util.m$a */
    public static final class C0656a {
        /* renamed from: a */
        public final PackageInfo f662a;
        /* renamed from: b */
        public final int f663b;
        /* renamed from: c */
        public final String f664c;

        public C0656a(PackageInfo packageInfo, int i, String str) {
            this.f662a = packageInfo;
            this.f663b = i;
            this.f664c = str;
        }

        /* renamed from: a */
        public boolean mo8114a() {
            Signature[] signatureArr = this.f662a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            int length = signatureArr.length;
            int i = 0;
            while (i < length) {
                String a = C0657m.m1056a(signatureArr[i].toByteArray());
                if (a == null || TextUtils.equals(a, this.f664c)) {
                    i++;
                } else {
                    C0590a.m801a("biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", new Object[]{a, this.f664c}));
                    return true;
                }
            }
            return false;
        }

        /* renamed from: b */
        public boolean mo8115b() {
            return this.f662a.versionCode < this.f663b;
        }
    }

    /* renamed from: a */
    public static String m1051a() {
        if (EnvUtils.isSandBox()) {
            return "com.eg.android.AlipayGphoneRC";
        }
        try {
            return ((C0614a) C0587i.f495a.get(0)).f569a;
        } catch (Throwable th) {
            return "com.eg.android.AlipayGphone";
        }
    }

    /* renamed from: a */
    public static String m1054a(String str) {
        if (EnvUtils.isSandBox() && TextUtils.equals(str, "com.eg.android.AlipayGphoneRC")) {
            return "com.eg.android.AlipayGphoneRC.IAlixPay";
        }
        return "com.eg.android.AlipayGphone.IAlixPay";
    }

    /* renamed from: b */
    public static Map<String, String> m1063b(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=", 1);
            if (-1 != indexOf) {
                hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public static Map<String, String> m1069c(String str) {
        HashMap hashMap = new HashMap(4);
        int indexOf = str.indexOf(63);
        if (indexOf != -1 && indexOf < str.length() - 1) {
            for (String str2 : str.substring(indexOf + 1).split("&")) {
                int indexOf2 = str2.indexOf(61, 1);
                if (indexOf2 != -1 && indexOf2 < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf2), C0657m.m1077e(str2.substring(indexOf2 + 1)));
                }
            }
        }
        return hashMap;
    }

    /* renamed from: d */
    public static JSONObject m1074d(String str) {
        try {
            return JSONObjectInstrumentation.init(str);
        } catch (Throwable th) {
            return new JSONObject();
        }
    }

    /* renamed from: e */
    public static String m1077e(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            C0590a.m802a("biz", "H5PayDataAnalysisError", e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m1055a(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            if (length <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, length);
            }
            if (i < 1) {
                return str3.substring(length);
            }
            return str3.substring(length, i);
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m1062b(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, length);
            }
            if (i < 1) {
                return str3.substring(length);
            }
            return str3.substring(length, i);
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m1056a(byte[] bArr) {
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (publicKey instanceof RSAPublicKey) {
                BigInteger modulus = ((RSAPublicKey) publicKey).getModulus();
                if (modulus != null) {
                    return modulus.toString(16);
                }
            }
        } catch (Exception e) {
            C0590a.m802a("auth", "GetPublicKeyFromSignEx", e);
        }
        return null;
    }

    /* renamed from: a */
    public static C0656a m1049a(Context context, List<C0614a> list) {
        if (list == null) {
            return null;
        }
        for (C0614a c0614a : list) {
            if (c0614a != null) {
                C0656a a = C0657m.m1048a(context, c0614a.f569a, c0614a.f570b, c0614a.f571c);
                if (!(a == null || a.mo8114a() || a.mo8115b())) {
                    return a;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private static C0656a m1048a(Context context, String str, int i, String str2) {
        PackageInfo d;
        if (EnvUtils.isSandBox() && "com.eg.android.AlipayGphone".equals(str)) {
            str = "com.eg.android.AlipayGphoneRC";
        }
        try {
            d = C0657m.m1071d(context, str);
            if (!C0657m.m1066b(d)) {
                try {
                    d = C0657m.m1075e(context, str);
                } catch (Throwable th) {
                    C0590a.m802a("auth", "GetInstalledPackagesEx", th);
                }
            }
        } catch (Throwable th2) {
            C0590a.m802a("auth", "GetInstalledPackagesEx", th2);
            d = null;
        }
        if (C0657m.m1066b(d)) {
            return C0657m.m1050a(d, i, str2);
        }
        return null;
    }

    /* renamed from: b */
    private static boolean m1066b(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            C0590a.m801a("auth", "NotIncludeSignatures", str);
        }
        return z;
    }

    /* renamed from: d */
    private static PackageInfo m1071d(Context context, String str) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    /* renamed from: e */
    private static PackageInfo m1075e(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(192)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static C0656a m1050a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new C0656a(packageInfo, i, str);
    }

    /* renamed from: a */
    public static boolean m1057a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m1065b(Context context, List<C0614a> list) {
        try {
            for (C0614a c0614a : list) {
                if (c0614a != null) {
                    String str = c0614a.f569a;
                    if (EnvUtils.isSandBox() && "com.eg.android.AlipayGphone".equals(str)) {
                        str = "com.eg.android.AlipayGphoneRC";
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (NameNotFoundException e) {
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            C0590a.m802a("biz", "CheckLaunchAppExistEx", th);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1058a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (TextUtils.equals(str, f665e[0]) || TextUtils.equals(str, f665e[1])) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m1064b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(C0657m.m1051a(), 128);
            if (packageInfo != null && packageInfo.versionCode < 99) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C0646c.m1016a(th);
            return false;
        }
    }

    /* renamed from: c */
    public static String m1068c(Context context) {
        String b = C0657m.m1060b();
        String c = C0657m.m1067c();
        String d = C0657m.m1073d(context);
        return " (" + b + ";" + c + ";" + d + ";" + ";" + C0657m.m1076e(context) + ")" + "(sdk android)";
    }

    /* renamed from: b */
    public static String m1060b() {
        return "Android " + VERSION.RELEASE;
    }

    /* renamed from: c */
    public static String m1067c() {
        String d = C0657m.m1072d();
        int indexOf = d.indexOf("-");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        indexOf = d.indexOf("\n");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        return "Linux " + d;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: d */
    public static java.lang.String m1072d() {
        /*
        r3 = 4;
        r0 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0030 }
        r1 = new java.io.FileReader;	 Catch:{ IOException -> 0x0030 }
        r2 = "/proc/version";
        r1.<init>(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r0.<init>(r1, r2);	 Catch:{ IOException -> 0x0030 }
        r1 = r0.readLine();	 Catch:{ all -> 0x002b }
        r0.close();	 Catch:{ IOException -> 0x0030 }
        r0 = "\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)";
        r0 = "\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)";
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ IOException -> 0x0030 }
        r0 = r0.matcher(r1);	 Catch:{ IOException -> 0x0030 }
        r1 = r0.matches();	 Catch:{ IOException -> 0x0030 }
        if (r1 != 0) goto L_0x0034;
    L_0x0028:
        r0 = "Unavailable";
    L_0x002a:
        return r0;
    L_0x002b:
        r1 = move-exception;
        r0.close();	 Catch:{ IOException -> 0x0030 }
        throw r1;	 Catch:{ IOException -> 0x0030 }
    L_0x0030:
        r0 = move-exception;
        r0 = "Unavailable";
        goto L_0x002a;
    L_0x0034:
        r1 = r0.groupCount();	 Catch:{ IOException -> 0x0030 }
        if (r1 >= r3) goto L_0x003d;
    L_0x003a:
        r0 = "Unavailable";
        goto L_0x002a;
    L_0x003d:
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0030 }
        r2 = 1;
        r2 = r0.group(r2);	 Catch:{ IOException -> 0x0030 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = "\n";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = 2;
        r2 = r0.group(r2);	 Catch:{ IOException -> 0x0030 }
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = " ";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = 3;
        r2 = r0.group(r2);	 Catch:{ IOException -> 0x0030 }
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = "\n";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = 4;
        r0 = r0.group(r2);	 Catch:{ IOException -> 0x0030 }
        r0 = r1.append(r0);	 Catch:{ IOException -> 0x0030 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0030 }
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.C0657m.m1072d():java.lang.String");
    }

    /* renamed from: d */
    public static String m1073d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    /* renamed from: e */
    public static String m1076e(Context context) {
        DisplayMetrics f = C0657m.m1078f(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f.widthPixels);
        stringBuilder.append("*");
        stringBuilder.append(f.heightPixels);
        return stringBuilder.toString();
    }

    /* renamed from: f */
    public static DisplayMetrics m1078f(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: g */
    public static String m1080g(Context context) {
        String a = C0655l.m1045a(context);
        return a.substring(0, a.indexOf("://"));
    }

    /* renamed from: h */
    public static String m1081h(Context context) {
        return "-1;-1";
    }

    /* renamed from: a */
    public static String m1052a(int i) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            switch (random.nextInt(3)) {
                case 0:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case 1:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case 2:
                    stringBuilder.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: f */
    public static boolean m1079f(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    /* renamed from: a */
    public static String m1053a(Context context, String str) {
        String str2 = "";
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
                String str3;
                if (runningAppProcessInfo.processName.equals(str)) {
                    str3 = str2 + "#M";
                } else if (runningAppProcessInfo.processName.startsWith(str + ":")) {
                    str3 = str2 + "#" + runningAppProcessInfo.processName.replace(str + ":", "");
                } else {
                    str3 = str2;
                }
                str2 = str3;
            }
        } catch (Throwable th) {
            str2 = "";
        }
        if (str2.length() > 0) {
            str2 = str2.substring(1);
        }
        if (str2.length() == 0) {
            return DCSProfile.INDICATOR_FALSE;
        }
        return str2;
    }

    /* renamed from: i */
    public static String m1082i(Context context) {
        try {
            List installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                if (C0657m.m1070c(packageInfo)) {
                    if (packageInfo.packageName.equals(C0657m.m1051a())) {
                        stringBuilder.append(packageInfo.packageName).append(packageInfo.versionCode).append("-");
                    } else if (!(packageInfo.packageName.contains("theme") || packageInfo.packageName.startsWith("com.google.") || packageInfo.packageName.startsWith("com.android."))) {
                        stringBuilder.append(packageInfo.packageName).append("-");
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            C0590a.m802a("biz", "GetInstalledAppEx", th);
            return "";
        }
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: c */
    private static boolean m1070c(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return (i & 1) == 0 && (i & 128) == 0;
    }

    /* renamed from: a */
    public static boolean m1059a(WebView webView, String str, Activity activity) {
        if (!TextUtils.isEmpty(str)) {
            if (str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) || str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
                try {
                    C0656a a = C0657m.m1049a((Context) activity, C0587i.f495a);
                    if (!(a == null || a.mo8115b() || a.mo8114a())) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (Throwable th) {
                }
            } else if (TextUtils.equals(str, "sdklite://h5quit") || TextUtils.equals(str, "http://m.alipay.com/?action=h5quit")) {
                C0588j.m790a(C0588j.m793c());
                activity.finish();
            } else if (str.startsWith("sdklite://h5quit?result=")) {
                try {
                    String substring = str.substring(str.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length());
                    int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf("&end_code=") + "&end_code=".length()));
                    if (parseInt == C0589k.SUCCEEDED.mo8007a() || parseInt == C0589k.PAY_WAITTING.mo8007a()) {
                        if (C0611a.f568s) {
                            StringBuilder stringBuilder = new StringBuilder();
                            String decode = URLDecoder.decode(str);
                            String decode2 = URLDecoder.decode(decode);
                            int indexOf = decode.indexOf("&return_url=") + "&return_url=".length();
                            stringBuilder.append(decode2.substring(decode2.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length(), decode2.lastIndexOf("&end_code=")).split("&return_url=")[0]).append("&return_url=").append(decode.substring(indexOf, decode.indexOf("&", indexOf))).append(decode.substring(decode.indexOf("&", indexOf)));
                            substring = stringBuilder.toString();
                        } else {
                            substring = URLDecoder.decode(str);
                            substring = substring.substring(substring.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length(), substring.lastIndexOf("&end_code="));
                        }
                        C0589k b = C0589k.m796b(parseInt);
                        C0588j.m790a(C0588j.m789a(b.mo8007a(), b.mo8008b(), substring));
                        activity.runOnUiThread(new C0659o(activity));
                    } else {
                        C0589k b2 = C0589k.m796b(C0589k.FAILED.mo8007a());
                        C0588j.m790a(C0588j.m789a(b2.mo8007a(), b2.mo8008b(), ""));
                        activity.runOnUiThread(new C0659o(activity));
                    }
                } catch (Exception e) {
                    C0588j.m790a(C0588j.m795e());
                }
            } else {
                webView.loadUrl(str);
            }
        }
        return true;
    }

    /* renamed from: j */
    public static String m1083j(Context context) {
        return C0657m.m1061b(context, context.getPackageName());
    }

    /* renamed from: b */
    public static String m1061b(Context context, String str) {
        String str2 = "";
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Throwable th) {
            C0590a.m802a("biz", "GetPackageInfoEx", th);
            return str2;
        }
    }
}
