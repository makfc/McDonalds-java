package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0657m;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.sys.a */
public class C0639a {
    /* renamed from: n */
    private String f616n = "";
    /* renamed from: o */
    private String f617o = "";
    /* renamed from: p */
    private Context f618p = null;

    public C0639a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f616n = packageInfo.versionName;
            this.f617o = packageInfo.packageName;
            this.f618p = context.getApplicationContext();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public String mo8085a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (m969b(str)) {
            return m970c(str);
        }
        return m971d(str);
    }

    /* renamed from: b */
    private boolean m969b(String str) {
        return !str.contains("\"&");
    }

    /* renamed from: c */
    private String m970c(String str) {
        try {
            String a = m966a(str, "&", "bizcontext=");
            if (TextUtils.isEmpty(a)) {
                return str + "&" + m968b("bizcontext=", "");
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + m967a(a, "bizcontext=", "", true) + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    /* renamed from: d */
    private String m971d(String str) {
        try {
            String a = m966a(str, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty(a)) {
                return str + "&" + m968b("bizcontext=\"", "\"");
            }
            if (!a.endsWith("\"")) {
                a = a + "\"";
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + m967a(a, "bizcontext=\"", "\"", false) + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    /* renamed from: a */
    private String m966a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str4;
        String[] split = str.split(str2);
        int i = 0;
        while (i < split.length) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                str4 = split[i];
                break;
            }
            i++;
        }
        str4 = null;
        return str4;
    }

    /* renamed from: b */
    private String m968b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + mo8086a("", "") + str2;
    }

    /* renamed from: a */
    public String mo8086a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", "2014052600006128");
            jSONObject.put("ty", "and_lite");
            jSONObject.put("sv", "h.a.3.5.9");
            if (!(this.f617o.contains("setting") && C0657m.m1064b(this.f618p))) {
                jSONObject.put("an", this.f617o);
            }
            jSONObject.put("av", this.f616n);
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        } catch (Throwable th) {
            C0646c.m1016a(th);
            return "";
        }
    }

    /* renamed from: a */
    private String m967a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        String substring = str.substring(str2.length());
        JSONObject init = JSONObjectInstrumentation.init(substring.substring(0, substring.length() - str3.length()));
        if (!init.has("appkey")) {
            init.put("appkey", "2014052600006128");
        }
        if (!init.has("ty")) {
            init.put("ty", "and_lite");
        }
        if (!init.has("sv")) {
            init.put("sv", "h.a.3.5.9");
        }
        if (!(init.has("an") || (this.f617o.contains("setting") && C0657m.m1064b(this.f618p)))) {
            init.put("an", this.f617o);
        }
        if (!init.has("av")) {
            init.put("av", this.f616n);
        }
        if (!init.has("sdk_start_time")) {
            init.put("sdk_start_time", System.currentTimeMillis());
        }
        return str2 + (!(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init)) + str3;
    }
}
