package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.ad */
public class C1369ad extends C1367d {
    public C1369ad(C1378l c1378l, Context context) {
        super(c1378l, context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        C1370b.m6206b(hashMap);
        hashMap.put("method", "unbindapp");
        hashMap.put("appid", this.f4800b.f4834f);
        C1425a.m6442c("UnbindApp", "UNBINDAPP param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
