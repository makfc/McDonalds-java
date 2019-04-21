package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.x */
public class C1391x extends C1363c {
    public C1391x(C1378l c1378l, Context context) {
        super(c1378l, context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "online");
        C1425a.m6442c("Online", "Online param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
