package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.app.C0587i;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.cons.C0611a;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.tid.C0643b;
import com.alipay.sdk.util.C0644a;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0657m;
import com.newrelic.agent.android.payload.PayloadController;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: com.alipay.sdk.data.c */
public class C0617c {
    /* renamed from: d */
    private static C0617c f580d;
    /* renamed from: e */
    private String f581e;
    /* renamed from: f */
    private String f582f = "sdk-and-lite";
    /* renamed from: g */
    private String f583g;

    private C0617c() {
        String a = C0587i.m785a();
        if (!C0587i.m787b()) {
            this.f582f += '_' + a;
        }
    }

    /* renamed from: b */
    public static synchronized C0617c m879b() {
        C0617c c0617c;
        synchronized (C0617c.class) {
            if (f580d == null) {
                f580d = new C0617c();
            }
            c0617c = f580d;
        }
        return c0617c;
    }

    /* renamed from: a */
    public synchronized void mo8059a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(C0640b.m974a().mo8088b()).edit().putString("trideskey", str).commit();
            C0611a.f567c = str;
        }
    }

    /* renamed from: e */
    private String m884e() {
        return "1";
    }

    /* renamed from: b */
    private String m880b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    /* renamed from: f */
    private String m885f() {
        return "-1;-1";
    }

    /* renamed from: a */
    public String mo8058a(C0643b c0643b) {
        String b;
        String c;
        String d;
        String g;
        String e;
        Context b2 = C0640b.m974a().mo8088b();
        C0644a a = C0644a.m1004a(b2);
        if (TextUtils.isEmpty(this.f581e)) {
            b = C0657m.m1060b();
            c = C0657m.m1067c();
            d = C0657m.m1073d(b2);
            g = C0657m.m1080g(b2);
            e = C0657m.m1076e(b2);
            this.f581e = "Msp/15.5.9" + " (" + b + ";" + c + ";" + d + ";" + g + ";" + e + ";" + m880b(b2);
        }
        String b3 = C0644a.m1005b(b2).mo8109b();
        b = C0657m.m1081h(b2);
        c = m884e();
        d = a.mo8102a();
        g = a.mo8104b();
        e = mo8061d();
        String c2 = mo8060c();
        if (c0643b != null) {
            this.f583g = c0643b.mo8098b();
        }
        String replace = Build.MANUFACTURER.replace(";", " ");
        String replace2 = Build.MODEL.replace(";", " ");
        boolean d2 = C0640b.m975d();
        String d3 = a.mo8107d();
        String c3 = m882c(b2);
        String d4 = m883d(b2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f581e).append(";").append(b3).append(";").append(b).append(";").append(c).append(";").append(d).append(";").append(g).append(";").append(this.f583g).append(";").append(replace).append(";").append(replace2).append(";").append(d2).append(";").append(d3).append(";").append(m885f()).append(";").append(this.f582f).append(";").append(e).append(";").append(c2).append(";").append(c3).append(";").append(d4);
        if (c0643b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("tid", C0643b.m990a(b2).mo8096a());
            hashMap.put("utdid", C0640b.m974a().mo8090e());
            String b4 = m881b(b2, hashMap);
            if (!TextUtils.isEmpty(b4)) {
                stringBuilder.append(";").append(b4);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: c */
    public String mo8060c() {
        Context b = C0640b.m974a().mo8088b();
        SharedPreferences sharedPreferences = b.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imei", null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C0643b.m990a(b).mo8096a())) {
                string = m886g();
            } else {
                string = C0644a.m1004a(b).mo8104b();
            }
            sharedPreferences.edit().putString("virtual_imei", string).commit();
        }
        return string;
    }

    /* renamed from: d */
    public String mo8061d() {
        Context b = C0640b.m974a().mo8088b();
        SharedPreferences sharedPreferences = b.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imsi", null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C0643b.m990a(b).mo8096a())) {
                string = C0640b.m974a().mo8090e();
                if (TextUtils.isEmpty(string)) {
                    string = m886g();
                } else {
                    string = string.substring(3, 18);
                }
            } else {
                string = C0644a.m1004a(b).mo8102a();
            }
            sharedPreferences.edit().putString("virtual_imsi", string).commit();
        }
        return string;
    }

    /* renamed from: g */
    private String m886g() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    /* renamed from: c */
    private String m882c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getSSID();
        }
        return "-1";
    }

    /* renamed from: d */
    private String m883d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getBSSID();
        }
        return "00";
    }

    /* renamed from: a */
    private String m877a(Context context, HashMap<String, String> hashMap) {
        CharSequence charSequence = "";
        try {
            charSequence = SecurityClientMobile.GetApdid(context, hashMap);
        } catch (Throwable th) {
            C0646c.m1016a(th);
            C0590a.m802a("third", "GetApdidEx", th);
        }
        if (TextUtils.isEmpty(charSequence)) {
            C0590a.m801a("third", "GetApdidNull", "apdid == null");
        }
        C0646c.m1019d("msp", "apdid:" + charSequence);
        return charSequence;
    }

    /* renamed from: b */
    private String m881b(Context context, HashMap<String, String> hashMap) {
        Future submit = Executors.newFixedThreadPool(2).submit(new C0618d(this, context, hashMap));
        String str = "";
        try {
            return (String) submit.get(PayloadController.PAYLOAD_COLLECTOR_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            C0590a.m802a("third", "GetApdidTimeout", th);
            return str;
        }
    }

    /* renamed from: a */
    public String mo8057a(Context context) {
        if (context != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String packageName = context.getPackageName();
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                stringBuilder.append("(");
                stringBuilder.append(packageName);
                stringBuilder.append(";");
                stringBuilder.append(packageInfo.versionCode);
                stringBuilder.append(")");
                return stringBuilder.toString();
            } catch (Exception e) {
            }
        }
        return "";
    }
}
