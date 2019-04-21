package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.baidu.android.pushservice.e.w */
public class C1390w extends C1363c {
    /* renamed from: d */
    private ArrayList<String> f4884d = new ArrayList();

    public C1390w(C1378l c1378l, Context context) {
        super(c1378l, context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13719a(int i, byte[] bArr) {
        Intent intent = new Intent();
        if (this.f4800b.f4829a.equals("method_list_lapp_tags")) {
            intent.setAction("com.baidu.android.pushservice.action.lapp.RECEIVE");
        } else if (this.f4800b.f4829a.equals("method_list_sdk_tags")) {
            intent.setAction("com.baidu.android.pushservice.action.sdk.RECEIVE");
        } else {
            intent.setAction("com.baidu.android.pushservice.action.RECEIVE");
        }
        intent.putExtra("method", this.f4800b.f4829a);
        intent.putExtra("error_msg", i);
        intent.putExtra("content", bArr);
        if (!this.f4884d.isEmpty()) {
            intent.putStringArrayListExtra("tags_list", this.f4884d);
        }
        intent.setFlags(32);
        mo13720a(intent);
        if (TextUtils.isEmpty(this.f4800b.f4833e)) {
            if (!(this.f4800b.f4829a.equals("method_list_lapp_tags") || this.f4800b.f4829a.equals("method_list_sdk_tags"))) {
                return;
            }
        } else if (!(this.f4800b.f4829a.equals("method_list_lapp_tags") || this.f4800b.f4829a.equals("method_list_sdk_tags"))) {
            intent.setPackage(this.f4800b.f4833e);
        }
        C1425a.m6442c("Glist", "> sendResult to " + this.f4800b.f4837i + " ,method:" + this.f4800b.f4829a + " ,errorCode : " + i + " ,content : " + new String(bArr));
        C1578v.m7094b(this.f4799a, intent, intent.getAction(), intent.getPackage());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "glist");
        C1425a.m6442c("Glist", "Glist param -- " + C1370b.m6202a((HashMap) hashMap));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        String b = super.mo13723b(str);
        try {
            JSONArray jSONArray = JSONObjectInstrumentation.init(b).getJSONObject("response_params").getJSONArray("groups");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.f4884d.add(jSONArray.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            C1425a.m6444e("Glist", "error " + e.getMessage());
        }
        return b;
    }
}
