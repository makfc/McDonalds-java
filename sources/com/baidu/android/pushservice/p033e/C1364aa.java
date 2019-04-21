package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.content.Intent;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.aa */
public class C1364aa extends C1363c {
    /* renamed from: d */
    protected String f4803d;
    /* renamed from: e */
    protected C1362a f4804e;

    /* renamed from: com.baidu.android.pushservice.e.aa$a */
    public interface C1362a {
        /* renamed from: a */
        void mo13726a(int i);
    }

    public C1364aa(C1378l c1378l, Context context, String str) {
        super(c1378l, context);
        this.f4803d = str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13720a(Intent intent) {
        super.mo13720a(intent);
        if (intent != null) {
            int intExtra = intent.getIntExtra("error_msg", -1);
            if (intExtra != 0 && this.f4804e != null) {
                this.f4804e.mo13726a(intExtra);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "settags");
        hashMap.put("tags", this.f4803d);
        C1425a.m6442c("SetTags", "SetTags param -- " + C1370b.m6202a((HashMap) hashMap));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        if (this.f4804e != null) {
            this.f4804e.mo13726a(0);
        }
        return super.mo13723b(str);
    }
}
