package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.p035g.C1411e;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1471e;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.v */
public class C1389v extends C1367d {
    /* renamed from: e */
    private int f4875e;
    /* renamed from: f */
    private C1411e f4876f;
    /* renamed from: g */
    private String f4877g = "";
    /* renamed from: h */
    private String f4878h = "";
    /* renamed from: i */
    private String f4879i = "";
    /* renamed from: j */
    private String f4880j = "DEFAULT";
    /* renamed from: k */
    private String f4881k;
    /* renamed from: l */
    private String f4882l;
    /* renamed from: m */
    private String f4883m;

    public C1389v(C1378l c1378l, int i, String str, String str2, C1411e c1411e, Context context) {
        super(c1378l, context);
        this.f4875e = i;
        this.f4876f = c1411e;
        if (!TextUtils.isEmpty(str)) {
            this.f4880j = str;
        }
        this.f4882l = str2;
        this.f4883m = c1378l.f4834f;
    }

    public C1389v(C1378l c1378l, int i, String str, String str2, String str3, String str4, String str5, C1411e c1411e, Context context) {
        super(c1378l, context);
        this.f4875e = i;
        this.f4876f = c1411e;
        this.f4877g = str;
        if (!TextUtils.isEmpty(str2)) {
            this.f4878h = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            this.f4879i = str3;
        }
        if (!TextUtils.isEmpty(str4)) {
            this.f4880j = str4;
        }
        if (!TextUtils.isEmpty(c1378l.f4837i)) {
            this.f4881k = c1378l.f4837i;
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
        if (this.f4876f != null) {
            HashMap hashMap = new HashMap();
            try {
                hashMap.put("error_msg", JSONObjectInstrumentation.init(new String(bArr)).getString("error_msg"));
            } catch (Exception e) {
                C1425a.m6440a("BaseRegisterProcessor", e);
            }
            this.f4876f.mo13788a(i, hashMap);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "lightapp_unbind");
        if (this.f4875e == 1) {
            hashMap.put("push_type", "1");
            hashMap.put("nonce", this.f4878h);
            hashMap.put("referer", this.f4879i);
        } else if (this.f4875e == 2 || this.f4875e == 3) {
            hashMap.put("push_type", "3");
        }
        if (TextUtils.isEmpty(this.f4877g)) {
            hashMap.put("push_hash", this.f4882l);
            hashMap.put("appid", this.f4883m);
        } else {
            hashMap.put("csrftoken", this.f4877g);
            hashMap.put("cuid", C1471e.m6687a(this.f4799a));
        }
        hashMap.put("host_app", this.f4880j);
        hashMap.put("push_sdk_version", "" + C1328a.m6003a());
        C1425a.m6442c("BaseRegisterProcessor", "L BIND url: " + this.f4801c);
        C1425a.m6442c("BaseRegisterProcessor", "L UNBIND param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
