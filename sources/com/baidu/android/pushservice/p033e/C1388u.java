package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.p035g.C1411e;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1438d;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.baidu.android.pushservice.e.u */
public class C1388u extends C1363c {
    /* renamed from: d */
    protected String f4867d;
    /* renamed from: e */
    private C1411e f4868e;
    /* renamed from: f */
    private String f4869f = null;
    /* renamed from: g */
    private String f4870g = "";
    /* renamed from: h */
    private String f4871h = "";
    /* renamed from: i */
    private String f4872i = "";
    /* renamed from: j */
    private String f4873j = "";
    /* renamed from: k */
    private int f4874k = 0;

    public C1388u(C1378l c1378l, String str, String str2, String str3, String str4, String str5, C1411e c1411e, Context context, int i) {
        super(c1378l, context);
        this.f4867d = str;
        this.f4870g = str2;
        this.f4871h = str3;
        this.f4872i = str4;
        this.f4873j = str5;
        this.f4868e = c1411e;
        this.f4874k = i;
    }

    /* renamed from: b */
    private void m6234b(int i) {
        C1438d c1438d = new C1438d();
        if (this.f4874k == 1) {
            c1438d.f5036f = "020706";
        } else if (this.f4874k == 0) {
            c1438d.f5036f = "020704";
        } else if (this.f4874k == 2) {
            c1438d.f5036f = "020708";
        }
        c1438d.f5037g = System.currentTimeMillis();
        c1438d.f5038h = C1432b.m6486c(this.f4799a);
        c1438d.f5040j = this.f4867d;
        if (i == 0) {
            c1438d.f5065c = this.f4873j;
            C1449n c1449n = new C1449n(this.f4867d);
            c1449n.mo13918c(C1449n.f5117b);
            C1456u.m6612a(this.f4799a, c1449n);
        } else {
            c1438d.f5039i = i;
            c1438d.f5063a = this.f4869f;
        }
        C1456u.m6609a(this.f4799a, c1438d);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13718a(int i) {
        if (this.f4868e != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("details", this.f4869f);
            this.f4868e.mo13788a(i, hashMap);
        }
        m6234b(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13719a(int i, byte[] bArr) {
        if (this.f4868e != null) {
            HashMap hashMap = new HashMap();
            if (i == 0) {
                hashMap.put("details", this.f4869f);
            } else {
                try {
                    hashMap.put("error_msg", JSONObjectInstrumentation.init(new String(bArr)).getString("error_msg"));
                } catch (Exception e) {
                    C1425a.m6442c("BaseApiProcessor", "sendResult E: " + e);
                }
            }
            this.f4868e.mo13788a(i, hashMap);
        }
        m6234b(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "lightapp_settags");
        hashMap.put("tags", this.f4867d);
        hashMap.put("cuid", C1471e.m6687a(this.f4799a));
        hashMap.put("csrftoken", this.f4870g);
        hashMap.put("nonce", this.f4871h);
        if (this.f4874k == 1 || this.f4874k == 0) {
            hashMap.put("push_type", "2");
        } else if (this.f4874k == 2) {
            hashMap.put("push_type", "6");
        }
        if (!TextUtils.isEmpty(this.f4872i)) {
            hashMap.put("referer", this.f4872i);
        }
        C1425a.m6442c("BaseApiProcessor", "lightapp_subscribe_service param -- " + C1370b.m6202a((HashMap) hashMap));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        String b = super.mo13723b(str);
        try {
            JSONArray jSONArray = JSONObjectInstrumentation.init(b).getJSONObject("response_params").getJSONArray("details");
            this.f4869f = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
        } catch (JSONException e) {
            C1425a.m6444e("BaseApiProcessor", "error " + e.getMessage());
        }
        return b;
    }
}
