package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.i */
public class C1375i extends C1363c {
    /* renamed from: d */
    protected String f4826d;

    public C1375i(C1378l c1378l, Context context, String str) {
        super(c1378l, context);
        this.f4826d = str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "gmsgcount");
        hashMap.put("gid", this.f4826d);
        C1425a.m6442c("CountGmsg", "CountGmsg param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
