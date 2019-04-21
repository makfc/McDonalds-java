package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1475k;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.p031c.C1331a;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1337f;
import com.baidu.android.pushservice.p031c.C1338g;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p031c.C1340i;
import com.baidu.android.pushservice.p031c.C1341j;
import com.baidu.android.pushservice.p031c.C1342k;
import com.baidu.android.pushservice.p031c.C1343l;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.e.d */
public class C1367d extends C1363c {
    /* renamed from: d */
    protected boolean f4817d = false;

    public C1367d(C1378l c1378l, Context context) {
        super(c1378l, context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo13723b(String str) {
        String jSONObject;
        String a;
        Intent intent;
        C1339h c1339h;
        C1578v.m7135o(this.f4799a.getApplicationContext());
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            JSONObject jSONObject2 = init.getJSONObject("response_params");
            String string = jSONObject2.getString("user_id");
            String string2 = jSONObject2.getString("appid");
            jSONObject2.put("channel_id", C1475k.m6721a(this.f4799a).mo13946a());
            this.f4800b.f4835g = string;
            this.f4800b.f4834f = string2;
            jSONObject = !(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init);
            C1337f c1337f;
            try {
                if (this.f4800b.f4829a.equals("method_deal_webapp_bind_intent") && !TextUtils.isEmpty(this.f4800b.f4837i)) {
                    C1342k c1342k = (C1342k) C1343l.m6060a(this.f4799a).mo13610c(this.f4800b.f4837i);
                    if (c1342k != null) {
                        c1342k.mo13586a(string2);
                        a = C1343l.m6060a(this.f4799a).mo13604a((C1331a) c1342k, this.f4817d);
                        intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
                        intent.putExtra("r_sync_rdata_v2", a);
                        intent.putExtra("r_sync_type", 1);
                        intent.putExtra("r_sync_from", this.f4799a.getPackageName());
                        intent.setFlags(32);
                        this.f4799a.sendBroadcast(intent);
                        return jSONObject;
                    }
                } else if (this.f4800b.f4829a.equals("method_deal_lapp_bind_intent") && !TextUtils.isEmpty(this.f4800b.f4837i)) {
                    c1337f = (C1337f) C1338g.m6051a(this.f4799a).mo13610c(this.f4800b.f4837i);
                    if (c1337f != null) {
                        c1337f.mo13586a(string2);
                        a = C1338g.m6051a(this.f4799a).mo13604a((C1331a) c1337f, this.f4817d);
                        intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
                        intent.putExtra("r_sync_rdata_v2", a);
                        intent.putExtra("r_sync_type", 2);
                        intent.putExtra("r_sync_from", this.f4799a.getPackageName());
                        intent.setFlags(32);
                        this.f4799a.sendBroadcast(intent);
                        return jSONObject;
                    }
                } else if (this.f4800b.f4829a.equals("method_sdk_bind")) {
                    C1340i c1340i = (C1340i) C1341j.m6054a(this.f4799a).mo13610c(this.f4800b.f4837i);
                    if (c1340i != null) {
                        c1340i.mo13586a(string2);
                        c1340i.mo13588b(this.f4800b.f4833e);
                        a = C1341j.m6054a(this.f4799a).mo13616a(c1340i);
                        intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
                        intent.putExtra("r_sync_rdata_v2", a);
                        intent.putExtra("r_sync_type", 3);
                        intent.putExtra("r_sync_sdk_from", this.f4799a.getPackageName());
                        intent.setFlags(32);
                        this.f4799a.sendBroadcast(intent);
                        return jSONObject;
                    }
                }
                C1425a.m6441b("BaseRegisterProcessor", "RegisterThread userId :  " + string);
                C1425a.m6441b("BaseRegisterProcessor", "RegisterThread appId :  " + string2);
                C1425a.m6441b("BaseRegisterProcessor", "RegisterThread content :  " + str);
                a = jSONObject;
            } catch (JSONException e) {
                try {
                    if (!(TextUtils.isEmpty(JSONObjectInstrumentation.init(str).getString("request_id")) || !this.f4800b.f4829a.equals("method_lapp_unbind") || TextUtils.isEmpty(this.f4800b.f4837i))) {
                        c1337f = (C1337f) C1338g.m6051a(this.f4799a).mo13610c(this.f4800b.f4837i);
                        if (c1337f != null) {
                            C1338g.m6051a(this.f4799a).mo13604a((C1331a) c1337f, false);
                            return jSONObject;
                        }
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                C1425a.m6442c("BaseRegisterProcessor", "Appid or user_id not found @: \r\n" + str);
                a = jSONObject;
                if (!!TextUtils.isEmpty(this.f4800b.f4830b)) {
                }
                if (!!TextUtils.isEmpty(this.f4800b.f4840l)) {
                }
                c1339h = new C1339h();
                c1339h.mo13588b(this.f4800b.f4833e);
                c1339h.mo13586a(this.f4800b.f4834f);
                c1339h.f4739e = this.f4800b.f4835g;
                c1339h.mo13613a(this.f4800b.f4839k);
                jSONObject = C1332b.m6020a(this.f4799a).mo13592a(c1339h, this.f4817d);
                intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
                intent.putExtra("r_sync_type", 0);
                intent.putExtra("r_sync_rdata_v2", jSONObject);
                intent.putExtra("r_sync_from", this.f4799a.getPackageName());
                intent.setFlags(32);
                C1578v.m7150v(this.f4799a, jSONObject);
                return a;
            }
        } catch (JSONException e3) {
            jSONObject = str;
        }
        if (!TextUtils.isEmpty(this.f4800b.f4830b) && this.f4800b.f4830b.equals("internal")) {
            return a;
        }
        if (!TextUtils.isEmpty(this.f4800b.f4840l) && ModeConfig.isProxyMode(this.f4799a)) {
            return a;
        }
        c1339h = new C1339h();
        c1339h.mo13588b(this.f4800b.f4833e);
        c1339h.mo13586a(this.f4800b.f4834f);
        c1339h.f4739e = this.f4800b.f4835g;
        c1339h.mo13613a(this.f4800b.f4839k);
        jSONObject = C1332b.m6020a(this.f4799a).mo13592a(c1339h, this.f4817d);
        intent = new Intent("com.baidu.android.pushservice.action.BIND_SYNC");
        intent.putExtra("r_sync_type", 0);
        intent.putExtra("r_sync_rdata_v2", jSONObject);
        intent.putExtra("r_sync_from", this.f4799a.getPackageName());
        intent.setFlags(32);
        C1578v.m7150v(this.f4799a, jSONObject);
        return a;
    }
}
