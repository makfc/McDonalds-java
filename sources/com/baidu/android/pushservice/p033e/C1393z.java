package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.z */
public class C1393z extends C1371e {
    /* renamed from: d */
    protected String f4886d = null;
    /* renamed from: e */
    protected String f4887e = null;
    /* renamed from: f */
    protected String f4888f = null;
    /* renamed from: g */
    protected String f4889g = null;

    public C1393z(C1378l c1378l, Context context, String str, String str2, String str3, String str4) {
        super(c1378l, context);
        this.f4886d = str;
        this.f4887e = str2;
        this.f4888f = str3;
        this.f4889g = str4;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "sendmsgtouser");
        hashMap.put("appid", this.f4886d);
        hashMap.put("user_id", this.f4887e);
        if (this.f4889g != null && this.f4888f != null) {
            C1425a.m6442c("user_id", this.f4887e);
            StringBuilder stringBuilder = new StringBuilder("[\"");
            stringBuilder.append(this.f4888f).append("\"]");
            StringBuilder stringBuilder2 = new StringBuilder("[\"");
            stringBuilder2.append(this.f4889g).append("\"]");
            hashMap.put("msg_keys", stringBuilder.toString());
            hashMap.put("messages", stringBuilder2.toString());
            C1425a.m6442c("Send", "key:" + this.f4888f.toString() + " messages:" + this.f4889g.toString());
            C1425a.m6442c("Send", "sendMsgToUser param -- " + C1370b.m6202a((HashMap) hashMap));
        }
    }
}
