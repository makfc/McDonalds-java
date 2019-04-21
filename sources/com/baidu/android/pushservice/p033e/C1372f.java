package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.util.C1578v;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.f */
public class C1372f extends C1367d {
    /* renamed from: e */
    protected int f4818e = 0;
    /* renamed from: f */
    private String f4819f;
    /* renamed from: g */
    private int f4820g;
    /* renamed from: h */
    private String f4821h;
    /* renamed from: i */
    private String f4822i;
    /* renamed from: j */
    private String f4823j;

    public C1372f(C1378l c1378l, Context context, int i, String str, int i2) {
        super(c1378l, context);
        this.f4818e = i;
        this.f4819f = str;
        this.f4820g = i2;
        if (this.f4818e == 0) {
            this.f4817d = true;
        }
    }

    public C1372f(C1378l c1378l, Context context, int i, String str, int i2, String str2) {
        super(c1378l, context);
        this.f4818e = i;
        this.f4819f = str;
        this.f4820g = i2;
        this.f4821h = str2;
        this.f4822i = c1378l.f4838j;
        this.f4823j = c1378l.f4840l;
        if (this.f4818e == 0) {
            this.f4817d = true;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13720a(Intent intent) {
        intent.putExtra("bind_status", this.f4818e);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "bind");
        hashMap.put("bind_name", TextUtils.isEmpty(this.f4819f) ? Build.MODEL : this.f4819f);
        hashMap.put("bind_status", this.f4818e + "");
        hashMap.put("push_sdk_version", this.f4820g + "");
        if (!TextUtils.isEmpty(this.f4822i) && this.f4822i.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            hashMap.put("is_baidu_internal_bind", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        if (!TextUtils.isEmpty(this.f4821h)) {
            hashMap.put("bind_notify_status", this.f4821h);
        }
        if (!TextUtils.isEmpty(this.f4800b.f4840l) && ModeConfig.isProxyMode(this.f4799a)) {
            hashMap.put("push_proxy", this.f4800b.f4840l);
            try {
                hashMap.put("manufacture", Build.MANUFACTURER);
                hashMap.put("model", Build.MODEL);
                hashMap.put("sdk_int", VERSION.SDK_INT + "");
                hashMap.put("rom", C1578v.m7054C(this.f4799a));
            } catch (Exception e) {
                C1425a.m6440a("Bind", e);
            }
        }
        C1425a.m6442c("Bind", "BIND param -- " + C1370b.m6202a((HashMap) hashMap));
        if (C1328a.m6006b() > 0) {
            C1456u.m6614a(this.f4799a, "039903", 0, this.f4800b.f4837i);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        String b = super.mo13723b(str);
        if (!TextUtils.isEmpty(this.f4800b.f4833e)) {
            C1332b.m6020a(this.f4799a).mo13602f(this.f4800b.f4833e);
            if (!TextUtils.isEmpty(this.f4800b.f4837i)) {
                C1332b.m6020a(this.f4799a).mo13594a(this.f4800b.f4833e, new C1373g(this.f4800b.f4837i, b));
            }
        }
        return b;
    }
}
