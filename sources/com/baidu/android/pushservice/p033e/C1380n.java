package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.n */
public class C1380n extends C1363c {
    /* renamed from: d */
    int f4843d = 1;
    /* renamed from: e */
    int f4844e = 1;
    /* renamed from: f */
    String f4845f;

    public C1380n(C1378l c1378l, Context context, String str, int i, int i2) {
        super(c1378l, context);
        this.f4845f = str;
        this.f4843d = i;
        this.f4844e = i2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "fetchgmsg");
        hashMap.put("gid", this.f4845f);
        hashMap.put("fetch_type", this.f4843d + "");
        hashMap.put("fetch_num", this.f4844e + "");
        C1425a.m6442c("FetchGmsg", "FETCHGmsg param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
