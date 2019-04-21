package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.HashMap;
import org.json.JSONArray;

/* renamed from: com.baidu.android.pushservice.e.k */
public class C1377k extends C1363c {
    /* renamed from: d */
    String[] f4828d;

    public C1377k(C1378l c1378l, Context context, String[] strArr) {
        super(c1378l, context);
        this.f4828d = strArr;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        if (r2 == 0) {
            mo13718a(30602);
            C1425a.m6442c("Delete", "Delete param -- msgIds == null");
            return;
        }
        hashMap.put("method", "delete");
        JSONArray jSONArray = new JSONArray();
        for (Object put : this.f4828d) {
            jSONArray.put(put);
        }
        hashMap.put("msg_ids", !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray));
        C1425a.m6442c("Delete", "Delete param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
