package com.baidu.android.pushservice.message;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.b */
public class C1502b {
    /* renamed from: a */
    public String f5239a;
    /* renamed from: b */
    public String f5240b;
    /* renamed from: c */
    public String f5241c;
    /* renamed from: d */
    public String f5242d;
    /* renamed from: e */
    public String f5243e;
    /* renamed from: f */
    public String f5244f;
    /* renamed from: g */
    public String f5245g;
    /* renamed from: h */
    public String f5246h;
    /* renamed from: i */
    public String f5247i;
    /* renamed from: j */
    public int f5248j;
    /* renamed from: k */
    public String f5249k;
    /* renamed from: l */
    public String f5250l;
    /* renamed from: m */
    public String f5251m;
    /* renamed from: n */
    public int f5252n;
    /* renamed from: o */
    public String f5253o;

    /* renamed from: a */
    public PublicMsg mo13972a(Context context) {
        PublicMsg publicMsg = new PublicMsg();
        try {
            publicMsg.mMsgId = this.f5251m;
            publicMsg.mAppId = this.f5250l;
            if (TextUtils.isEmpty(this.f5246h) && TextUtils.isEmpty(this.f5247i)) {
                publicMsg.mTitle = this.f5244f;
                publicMsg.mDescription = this.f5245g;
                publicMsg.mUrl = this.f5240b;
                publicMsg.mPkgContent = this.f5242d;
                return publicMsg;
            }
            publicMsg.mTitle = this.f5246h;
            publicMsg.mDescription = this.f5247i;
            publicMsg.mUrl = this.f5241c;
            publicMsg.mPkgContent = this.f5243e;
            return publicMsg;
        } catch (Exception e) {
            C1426b.m6447b("HWPushMessage", "Public Message Parsing Fail:\r\n" + e.getMessage(), context.getApplicationContext());
            C1425a.m6440a("HWPushMessage", e);
            return null;
        }
    }

    /* renamed from: a */
    public String mo13973a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.isNull("extras")) {
                return null;
            }
            JSONArray jSONArray = init.getJSONArray("extras");
            if (jSONArray == null) {
                return null;
            }
            mo13974a(jSONArray);
            if (TextUtils.isEmpty(this.f5253o)) {
                return null;
            }
            init = JSONObjectInstrumentation.init(new String(this.f5253o));
            return !init.isNull("custom_content") ? init.getString("custom_content") : null;
        } catch (JSONException e) {
            C1425a.m6442c("HWPushMessage", "not receive correct huawei passthrough message");
            return null;
        }
    }

    /* renamed from: a */
    public void mo13974a(JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.isNull("Appid")) {
                    this.f5250l = jSONObject.getString("Appid");
                }
                if (!jSONObject.isNull("Msgid")) {
                    this.f5251m = jSONObject.getString("Msgid");
                }
                if (!jSONObject.isNull("Type")) {
                    this.f5252n = jSONObject.getInt("Type");
                }
                if (!jSONObject.isNull("push_type")) {
                    this.f5248j = jSONObject.getInt("push_type");
                }
                if (!jSONObject.isNull("gid")) {
                    this.f5249k = jSONObject.getString("gid");
                }
                if (!jSONObject.isNull("msgBody")) {
                    this.f5253o = jSONObject.getString("msgBody");
                }
                i++;
            } catch (Exception e) {
                C1425a.m6440a("HWPushMessage", e);
                return;
            }
        }
    }
}
