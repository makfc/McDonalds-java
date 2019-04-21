package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p034f.C1402a;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.p039k.C1473g;
import com.baidu.android.pushservice.util.C1548l;
import com.baidu.android.pushservice.util.C1549m;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.a.e */
public class C1486e extends C1481d {
    /* renamed from: b */
    private static final String f5205b = C1486e.class.getSimpleName();

    public C1486e(Context context) {
        super(context);
    }

    /* renamed from: a */
    public static void m6743a(Context context) {
        long b = C1550n.m6961b(context, "com.baidu.pushservice.internal.bind.LATEST_TIME");
        C1425a.m6442c(f5205b, "lastTime from SP is: " + b);
        final long currentTimeMillis = System.currentTimeMillis();
        C1425a.m6442c(f5205b, "System currentTimeMillis is: " + currentTimeMillis);
        b = currentTimeMillis - b;
        C1425a.m6442c(f5205b, "curIntervalTime is: " + b);
        if (C1548l.m6948a(context) && b > 259200000) {
            final Context context2 = context;
            C1462d.m6637a().mo13938a(new C1281c("uploadInternalBindApps", (short) 95) {
                /* renamed from: a */
                public void mo13487a() {
                    try {
                        C1425a.m6442c(C1486e.f5205b, "setSP LAST_INTERNAL_BIND_TIME, newLastTime is: " + currentTimeMillis);
                        C1550n.m6957a(context2, "com.baidu.pushservice.internal.bind.LATEST_TIME", currentTimeMillis);
                        String b = C1486e.m6747d(context2);
                        C1425a.m6442c(C1486e.f5205b, "bindParams is: " + b);
                        HashMap hashMap = new HashMap();
                        C1370b.m6206b(hashMap);
                        hashMap.put("device_type", "3");
                        hashMap.put("params", b);
                        int i = 0;
                        do {
                            i++;
                            C1425a.m6442c(C1486e.f5205b, "HttpUtil.execRequest! INTERNAL_BIND_URL: http://api.tuisong.baidu.com/rest/3.0/oem/upload_unbind_oem");
                            C1402a a = C1403b.m6260a("http://api.tuisong.baidu.com/rest/3.0/oem/upload_unbind_oem", "POST", hashMap, "BCCS_SDK/3.0");
                            if (a.mo13745b() == 200) {
                                C1425a.m6442c(C1486e.f5205b, "code == 200, HTTP POST success!");
                                return;
                            }
                            C1425a.m6442c(C1486e.f5205b, "code == " + a.mo13745b() + ", HTTP POST unsuccess!");
                        } while (i < 2);
                    } catch (Exception e) {
                        C1425a.m6443d(C1486e.f5205b, "warn: " + e.getMessage());
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private void m6744a(String str, String str2, Context context) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
        intent.addFlags(32);
        intent.putExtra("method_version", "V2");
        intent.putExtra("secret_key", str);
        intent.putExtra("pkg_name", str2);
        intent.putExtra("is_baidu_internal_bind", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        intent.putExtra("method", "method_bind");
        intent.putExtra("bind_name", Build.MODEL);
        intent.putExtra("bind_status", 0);
        intent.putExtra("push_sdk_version", C1328a.m6003a());
        intent.setFlags(intent.getFlags() | 32);
        if (VERSION.SDK_INT >= 19) {
            if (C1549m.m6954a(context)) {
                intent.putExtra("bind_notify_status", "1");
            } else {
                intent.putExtra("bind_notify_status", "0");
            }
        }
        C1425a.m6442c(f5205b, "a internal bind intent send");
        C1577u.m7045a(context, intent);
    }

    /* renamed from: c */
    private static String m6746c(Context context) {
        String str;
        ArrayList q = C1578v.m7139q(context);
        if (!q.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = q.iterator();
            while (it.hasNext()) {
                str = (String) it.next();
                if (C1578v.m7148u(context, str)) {
                    C1425a.m6442c(f5205b, str + " is Already Binded App.");
                } else {
                    Context s = C1578v.m7143s(context, str);
                    String t = C1578v.m7146t(s, str);
                    String a = C1578v.m7068a(s, str, "bp_reg_key");
                    C1425a.m6442c(f5205b, str + "->Manifest bp_reg_key corresponding apiKey is " + a);
                    if (!TextUtils.isEmpty(a)) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("packagename", str);
                            jSONObject.put("apikey", a);
                            jSONObject.put("installtime", t);
                            jSONObject.put("pkgMD5info", C1578v.m7138p(s, str));
                            jSONArray.put(jSONObject);
                        } catch (Exception e) {
                            C1425a.m6440a(f5205b, e);
                        }
                    }
                }
            }
            if (jSONArray.length() > 0) {
                str = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
                C1425a.m6442c(f5205b, "All can internal bind app info bindApps: " + str);
                return str;
            }
        }
        str = null;
        C1425a.m6442c(f5205b, "All can internal bind app info bindApps: " + str);
        return str;
    }

    /* renamed from: d */
    private static String m6747d(Context context) throws Exception {
        String c = C1486e.m6746c(context);
        if (TextUtils.isEmpty(c)) {
            throw new Exception("NO INTERNAL BIND APP INFOS！");
        }
        JSONObject jSONObject = new JSONObject();
        String a = PushSettings.m5874a(context);
        String a2 = C1471e.m6687a(context);
        jSONObject.put("channel_id", a);
        jSONObject.put("cuid", a2);
        jSONObject.put("aksinfo", c);
        return C1465b.m6678a(C1473g.m6718a((!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject)).getBytes(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/7VlVn9LIrZ71PL2RZMbK/Yxc\r\ndb046w/cXVylxS7ouPY06namZUFVhdbUnNRJzmGUZlzs3jUbvMO3l+4c9cw/n9aQ\r\nrm/brgaRDeZbeSrQYRZv60xzJIimuFFxsRM+ku6/dAyYmXiQXlRbgvFQ0MsVng4j\r\nv+cXhtTis2Kbwb8mQwIDAQAB\r\n", 1024), "utf-8");
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        C1425a.m6442c(f5205b, "Internal bind cmd from server, appid: " + str + "msgId: " + str2 + "msgType: " + i + "checkInfo: " + bArr.toString() + "msgBody: " + bArr2);
        try {
            JSONObject init = JSONObjectInstrumentation.init(new String(bArr2));
            if (!init.isNull("custom_content")) {
                String string = init.getString("custom_content");
                C1425a.m6442c(f5205b, "jsonString: " + string);
                if (!TextUtils.isEmpty(string)) {
                    JSONArray init2 = JSONArrayInstrumentation.init(string);
                    int length = init2.length();
                    if (length > 0) {
                        ArrayList q = C1578v.m7139q(this.f5198a);
                        int i3 = 0;
                        while (i3 < length) {
                            int i4;
                            init = (JSONObject) init2.get(i3);
                            String string2 = init.getString("package_name");
                            if (q.contains(string2)) {
                                string = init.getString("apikey");
                                if (!(TextUtils.isEmpty(string2) || TextUtils.isEmpty(string) || C1578v.m7148u(this.f5198a, string2))) {
                                    C1425a.m6442c(f5205b, "doInternalBind: packageName=" + string2 + ", apiKey=" + string);
                                    m6744a(string, string2, this.f5198a);
                                }
                                i4 = i2;
                            } else {
                                i4 = 8;
                                C1425a.m6442c(f5205b, "packageName: [" + string2 + "] is not in allPushPkgs, return ack 8.");
                            }
                            i3++;
                            i2 = i4;
                        }
                    }
                }
            }
        } catch (Exception e) {
            i2 = 2;
            C1425a.m6440a(f5205b, e);
        }
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
