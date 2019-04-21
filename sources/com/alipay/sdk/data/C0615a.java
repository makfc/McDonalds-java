package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0652i;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.data.a */
public class C0615a {
    /* renamed from: q */
    private static C0615a f572q;
    /* renamed from: l */
    public boolean f573l = false;
    /* renamed from: m */
    private int f574m = 3500;
    /* renamed from: n */
    private String f575n = "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&";
    /* renamed from: o */
    private int f576o = 10;
    /* renamed from: p */
    private List<C0614a> f577p = null;

    /* renamed from: com.alipay.sdk.data.a$a */
    public static final class C0614a {
        /* renamed from: a */
        public final String f569a;
        /* renamed from: b */
        public final int f570b;
        /* renamed from: c */
        public final String f571c;

        public C0614a(String str, int i, String str2) {
            this.f569a = str;
            this.f570b = i;
            this.f571c = str2;
        }

        /* renamed from: a */
        public static C0614a m861a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C0614a(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        /* renamed from: a */
        public static List<C0614a> m862a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            List<C0614a> arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C0614a a = C0614a.m861a(jSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        }

        /* renamed from: a */
        public static JSONObject m864a(C0614a c0614a) {
            JSONObject jSONObject = null;
            if (c0614a == null) {
                return jSONObject;
            }
            try {
                return new JSONObject().put("pn", c0614a.f569a).put("v", c0614a.f570b).put("pk", c0614a.f571c);
            } catch (JSONException e) {
                C0646c.m1016a(e);
                return jSONObject;
            }
        }

        /* renamed from: a */
        public static JSONArray m863a(List<C0614a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (C0614a a : list) {
                jSONArray.put(C0614a.m864a(a));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(C0614a.m864a(this));
        }
    }

    /* renamed from: a */
    public int mo8051a() {
        if (this.f574m < 1000 || this.f574m > 20000) {
            C0646c.m1017b("", "DynamicConfig::getJumpTimeout(default) >3500");
            return 3500;
        }
        C0646c.m1017b("", "DynamicConfig::getJumpTimeout >" + this.f574m);
        return this.f574m;
    }

    /* renamed from: b */
    public String mo8053b() {
        return this.f575n;
    }

    /* renamed from: c */
    public int mo8054c() {
        return this.f576o;
    }

    /* renamed from: d */
    public List<C0614a> mo8055d() {
        return this.f577p;
    }

    /* renamed from: e */
    public static C0615a m869e() {
        if (f572q == null) {
            f572q = new C0615a();
            f572q.m870f();
        }
        return f572q;
    }

    /* renamed from: f */
    private void m870f() {
        m867a(C0652i.m1036b(C0640b.m974a().mo8088b(), "alipay_cashier_dynamic_config", null));
    }

    /* renamed from: a */
    private void m867a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                this.f574m = init.optInt("timeout", 3500);
                this.f575n = init.optString("tbreturl", "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&").trim();
                this.f576o = init.optInt("configQueryInterval", 10);
                this.f577p = C0614a.m862a(init.optJSONArray("launchAppSwitch"));
            } catch (Throwable th) {
                C0646c.m1016a(th);
            }
        }
    }

    /* renamed from: g */
    private void m871g() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timeout", mo8051a());
            jSONObject.put("tbreturl", mo8053b());
            jSONObject.put("configQueryInterval", mo8054c());
            jSONObject.put("launchAppSwitch", C0614a.m863a(mo8055d()));
            C0652i.m1035a(C0640b.m974a().mo8088b(), "alipay_cashier_dynamic_config", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e) {
            C0646c.m1016a(e);
        }
    }

    /* renamed from: b */
    private void m868b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = JSONObjectInstrumentation.init(str).optJSONObject("st_sdk_config");
                if (optJSONObject != null) {
                    this.f574m = optJSONObject.optInt("timeout", 3500);
                    this.f575n = optJSONObject.optString("tbreturl", "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&").trim();
                    this.f576o = optJSONObject.optInt("configQueryInterval", 10);
                    this.f577p = C0614a.m862a(optJSONObject.optJSONArray("launchAppSwitch"));
                    return;
                }
                C0646c.m1019d("msp", "config is null");
            } catch (Throwable th) {
                C0646c.m1016a(th);
            }
        }
    }

    /* renamed from: a */
    public void mo8052a(Context context) {
        new Thread(new C0616b(this, context)).start();
    }
}
