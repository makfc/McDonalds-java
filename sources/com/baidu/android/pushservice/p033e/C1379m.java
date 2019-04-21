package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.m */
public class C1379m extends C1363c {
    /* renamed from: d */
    int f4841d = 1;
    /* renamed from: e */
    int f4842e = 1;

    public C1379m(C1378l c1378l, Context context, int i, int i2) {
        super(c1378l, context);
        this.f4841d = i;
        this.f4842e = i2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "fetch");
        hashMap.put("fetch_type", this.f4841d + "");
        hashMap.put("fetch_num", this.f4842e + "");
        C1425a.m6442c("Fetch", "FETCH param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
