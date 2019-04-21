package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.o */
public class C1381o extends C1363c {
    /* renamed from: d */
    protected String f4846d;

    public C1381o(C1378l c1378l, Context context, String str) {
        super(c1378l, context);
        this.f4846d = str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "gbind");
        hashMap.put("gid", this.f4846d);
        C1425a.m6442c("Gbind", "Gbind param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
