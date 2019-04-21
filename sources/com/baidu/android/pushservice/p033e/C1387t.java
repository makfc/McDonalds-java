package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.p035g.C1411e;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1438d;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.e.t */
public class C1387t extends C1367d {
    /* renamed from: e */
    private int f4857e = 2;
    /* renamed from: f */
    private C1411e f4858f;
    /* renamed from: g */
    private String f4859g = "";
    /* renamed from: h */
    private String f4860h = "";
    /* renamed from: i */
    private String f4861i = "";
    /* renamed from: j */
    private String f4862j = "";
    /* renamed from: k */
    private String f4863k = "";
    /* renamed from: l */
    private String f4864l = "";
    /* renamed from: m */
    private String f4865m = "DEFAULT";
    /* renamed from: n */
    private String f4866n = "";

    public C1387t(C1378l c1378l, int i, String str, String str2, String str3, C1411e c1411e, Context context) {
        super(c1378l, context);
        this.f4857e = i;
        this.f4858f = c1411e;
        this.f4866n = str2;
        if (!TextUtils.isEmpty(str)) {
            this.f4865m = str;
        }
        this.f4859g = c1378l.f4834f;
    }

    public C1387t(C1378l c1378l, int i, String str, String str2, String str3, String str4, C1411e c1411e, Context context) {
        super(c1378l, context);
        this.f4857e = i;
        this.f4858f = c1411e;
        this.f4862j = str;
        if (!TextUtils.isEmpty(str2)) {
            this.f4863k = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            this.f4864l = str3;
        }
        if (!TextUtils.isEmpty(str4)) {
            this.f4865m = str4;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13718a(int i) {
        mo13719a(i, PushConstants.m5756a(i).getBytes());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13719a(int i, byte[] bArr) {
        try {
            if (this.f4858f != null) {
                HashMap hashMap = new HashMap();
                if (i == 0) {
                    hashMap.put("app_id", this.f4859g);
                    hashMap.put("channel_id", this.f4861i);
                    hashMap.put("user_id", this.f4860h);
                } else {
                    try {
                        hashMap.put("error_msg", JSONObjectInstrumentation.init(new String(bArr)).getString("error_msg"));
                    } catch (Exception e) {
                        C1425a.m6442c("BaseApiProcessor", "sendResult E: " + e);
                    }
                }
                this.f4858f.mo13788a(i, hashMap);
            }
            C1438d c1438d = new C1438d();
            if (this.f4857e == 2) {
                c1438d.f5036f = "020701";
            } else if (this.f4857e == 1) {
                c1438d.f5036f = "020703";
            } else if (this.f4857e == 3) {
                c1438d.f5036f = "020702";
            } else if (this.f4857e == 4) {
                c1438d.f5036f = "020705";
            } else if (this.f4857e == 5) {
                c1438d.f5036f = "020707";
            }
            c1438d.f5037g = System.currentTimeMillis();
            c1438d.f5038h = C1432b.m6486c(this.f4799a);
            c1438d.f5040j = this.f4859g;
            if (i == 0) {
                c1438d.f5065c = this.f4861i;
                C1449n c1449n = new C1449n(this.f4859g);
                c1449n.mo13918c(C1449n.f5117b);
                C1456u.m6612a(this.f4799a, c1449n);
            } else {
                c1438d.f5039i = i;
                try {
                    c1438d.f5063a = JSONObjectInstrumentation.init(new String(bArr)).getString("error_msg");
                } catch (JSONException e2) {
                    C1425a.m6442c("BaseApiProcessor", "insert BE: " + e2);
                }
            }
            C1456u.m6609a(this.f4799a, c1438d);
        } catch (Exception e3) {
            C1425a.m6443d("BaseApiProcessor", "e: " + e3);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "lightapp_bind");
        Object obj = Build.MODEL;
        if (!TextUtils.isEmpty(obj)) {
            obj = "unknown";
        }
        hashMap.put("bind_name", obj);
        if (this.f4857e == 1 || this.f4857e == 4 || this.f4857e == 5) {
            hashMap.put("nonce", this.f4863k);
            if (!TextUtils.isEmpty(this.f4864l)) {
                hashMap.put("referer", this.f4864l);
            }
            if (this.f4857e == 5) {
                hashMap.put("push_type", "5");
            } else {
                hashMap.put("push_type", "1");
            }
        } else if (this.f4857e == 2 || this.f4857e == 3) {
            hashMap.put("push_type", "3");
        }
        if (TextUtils.isEmpty(this.f4862j)) {
            hashMap.put("push_hash", this.f4866n);
            hashMap.put("appid", this.f4859g);
        } else {
            hashMap.put("csrftoken", this.f4862j);
            hashMap.put("cuid", C1471e.m6687a(this.f4799a));
        }
        hashMap.put("host_app", this.f4865m);
        hashMap.put("push_sdk_version", "" + C1328a.m6003a());
        C1425a.m6442c("BaseApiProcessor", "L BIND url: " + this.f4801c);
        C1425a.m6442c("BaseApiProcessor", "L BIND param -- " + C1370b.m6202a((HashMap) hashMap));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        String b = super.mo13723b(str);
        try {
            JSONObject jSONObject = JSONObjectInstrumentation.init(b).getJSONObject("response_params");
            this.f4859g = jSONObject.getString("appid");
            this.f4860h = jSONObject.getString("user_id");
            this.f4861i = jSONObject.getString("channel_id");
        } catch (JSONException e) {
            C1425a.m6442c("BaseApiProcessor", "bindData Exception: " + e);
        }
        return b;
    }
}
