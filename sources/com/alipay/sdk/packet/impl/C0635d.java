package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.alipay.sdk.packet.C0628b;
import com.alipay.sdk.packet.C0631e;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p041io.github.inflationx.viewpump.BuildConfig;

/* renamed from: com.alipay.sdk.packet.impl.d */
public class C0635d extends C0631e {
    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public Map<String, String> mo8079a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msp-gzip", String.valueOf(z));
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put("des-mode", "CBC");
        return hashMap;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public String mo8082c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put("api_name", "/sdk/log");
        hashMap.put("api_version", BuildConfig.VERSION_NAME);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("log_v", "1.0");
        return mo8078a(hashMap, hashMap2);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public JSONObject mo8080a() throws JSONException {
        return null;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo8077a(String str, JSONObject jSONObject) {
        return str;
    }

    /* renamed from: a */
    public C0628b mo8074a(Context context, String str) throws Throwable {
        return mo8076a(context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
