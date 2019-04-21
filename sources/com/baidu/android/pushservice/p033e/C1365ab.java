package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.p000v4.media.TransportMediator;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1427h;
import com.baidu.android.pushservice.p033e.C1361a.C1360a;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.e.ab */
public class C1365ab implements Runnable {
    /* renamed from: a */
    private Context f4805a;
    /* renamed from: b */
    private int f4806b = 3;
    /* renamed from: c */
    private int f4807c = 0;
    /* renamed from: d */
    private boolean f4808d = false;
    /* renamed from: e */
    private boolean f4809e = true;
    /* renamed from: f */
    private C1360a f4810f;

    public C1365ab(Context context, C1360a c1360a) {
        this.f4805a = context.getApplicationContext();
        this.f4810f = c1360a;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:37:0x01a6=Splitter:B:37:0x01a6, B:28:0x016d=Splitter:B:28:0x016d} */
    /* renamed from: b */
    private boolean m6192b() {
        /*
        r9 = this;
        r1 = 1;
        r2 = 0;
        r0 = com.baidu.android.pushservice.C1457i.m6632e();
        r3 = r9.f4807c;
        r4 = 2;
        if (r3 <= r4) goto L_0x004e;
    L_0x000b:
        r3 = r9.f4805a;
        r4 = r9.f4809e;
        r3 = com.baidu.android.pushservice.C1457i.m6626b(r3, r4);
        r4 = "TokenRequester";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = " --- token request use httpIp: ";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r4, r5);
        r9.f4809e = r2;
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x004e;
    L_0x0033:
        r4 = com.baidu.android.pushservice.C1457i.m6625b();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "http://";
        r5 = r5.append(r6);
        r3 = r5.append(r3);
        r3 = r3.toString();
        r0 = r0.replace(r4, r3);
    L_0x004e:
        r3 = "TokenRequester";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = ">>> token request:";
        r4 = r4.append(r5);
        r4 = r4.append(r0);
        r4 = r4.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r4);
        r3 = 0;
        r4 = r9.m6193c();	 Catch:{ IOException -> 0x01dc, Exception -> 0x01a2, all -> 0x01ca }
        r5 = "POST";
        r0 = com.baidu.android.pushservice.p034f.C1403b.m6259a(r0, r5, r4);	 Catch:{ IOException -> 0x01dc, Exception -> 0x01a2, all -> 0x01ca }
        r5 = r0.mo13745b();	 Catch:{ IOException -> 0x01dc, Exception -> 0x01a2, all -> 0x01ca }
        r4 = r0.mo13742a();	 Catch:{ IOException -> 0x01dc, Exception -> 0x01a2, all -> 0x01ca }
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 != r0) goto L_0x0135;
    L_0x007d:
        r0 = com.baidu.android.pushservice.p037i.p038a.C1432b.m6481a(r4);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = "TokenRequester";
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "<<< RequestToken return string :  ";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5 = r5.append(r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = new org.json.JSONObject;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5 = "response_params";
        r3 = r3.getJSONObject(r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        if (r3 == 0) goto L_0x011b;
    L_0x00a7:
        r0 = "channel_id";
        r0 = r3.getString(r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5 = "channel_token";
        r5 = r3.getString(r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "expires_time";
        r3 = r3.getString(r6);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "TokenRequester";
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r8 = "RequestToken channelId :  ";
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7 = r7.append(r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7 = r7.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r6, r7);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "TokenRequester";
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r8 = "RequestToken mChannelToken :  ";
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7 = r7.append(r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7 = r7.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r6, r7);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "TokenRequester";
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r8 = "RequestToken expiresTime :  ";
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = r7.append(r3);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r6, r3);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = r9.f4805a;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = com.baidu.android.pushservice.C1475k.m6721a(r3);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3.mo13948a(r0, r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r0 = r1;
    L_0x010b:
        r3 = 0;
        r9.f4807c = r3;	 Catch:{ IOException -> 0x01a0, Exception -> 0x01da }
        if (r4 != 0) goto L_0x019b;
    L_0x0110:
        r3 = 1;
        r9.f4808d = r3;	 Catch:{ IOException -> 0x01a0, Exception -> 0x01da }
    L_0x0113:
        r1 = new java.io.Closeable[r1];
        r1[r2] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
    L_0x011a:
        return r0;
    L_0x011b:
        r3 = "TokenRequester";
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "RequestToken failed :  ";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r0 = r5.append(r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
    L_0x0133:
        r0 = r2;
        goto L_0x010b;
    L_0x0135:
        r0 = com.baidu.android.pushservice.p037i.p038a.C1432b.m6481a(r4);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = "TokenRequester";
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r7 = "RequestToken request failed  ";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5 = r6.append(r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r5);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r3 = "TokenRequester";
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r5.<init>();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r6 = "<<< RequestToken return string :  ";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r0 = r5.append(r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r0);	 Catch:{ IOException -> 0x016a, Exception -> 0x01d6 }
        goto L_0x0133;
    L_0x016a:
        r0 = move-exception;
        r3 = r0;
        r0 = r2;
    L_0x016d:
        r5 = "TokenRequester";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d4 }
        r6.<init>();	 Catch:{ all -> 0x01d4 }
        r7 = "error : ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x01d4 }
        r3 = r3.getMessage();	 Catch:{ all -> 0x01d4 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x01d4 }
        r3 = r3.toString();	 Catch:{ all -> 0x01d4 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r5, r3);	 Catch:{ all -> 0x01d4 }
        r3 = "TokenRequester";
        r5 = "io exception, schedule retry";
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r3, r5);	 Catch:{ all -> 0x01d4 }
        r3 = 1;
        r9.f4808d = r3;	 Catch:{ all -> 0x01d4 }
        r1 = new java.io.Closeable[r1];
        r1[r2] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        goto L_0x011a;
    L_0x019b:
        r3 = 0;
        r9.f4808d = r3;	 Catch:{ IOException -> 0x01a0, Exception -> 0x01da }
        goto L_0x0113;
    L_0x01a0:
        r3 = move-exception;
        goto L_0x016d;
    L_0x01a2:
        r0 = move-exception;
        r4 = r3;
        r3 = r0;
        r0 = r2;
    L_0x01a6:
        r5 = "TokenRequester";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d4 }
        r6.<init>();	 Catch:{ all -> 0x01d4 }
        r7 = "Connect Exception:";
        r6 = r6.append(r7);	 Catch:{ all -> 0x01d4 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x01d4 }
        r3 = r3.toString();	 Catch:{ all -> 0x01d4 }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r5, r3);	 Catch:{ all -> 0x01d4 }
        r3 = 0;
        r9.f4808d = r3;	 Catch:{ all -> 0x01d4 }
        r1 = new java.io.Closeable[r1];
        r1[r2] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        goto L_0x011a;
    L_0x01ca:
        r0 = move-exception;
        r4 = r3;
    L_0x01cc:
        r1 = new java.io.Closeable[r1];
        r1[r2] = r4;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        throw r0;
    L_0x01d4:
        r0 = move-exception;
        goto L_0x01cc;
    L_0x01d6:
        r0 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x01a6;
    L_0x01da:
        r3 = move-exception;
        goto L_0x01a6;
    L_0x01dc:
        r0 = move-exception;
        r4 = r3;
        r3 = r0;
        r0 = r2;
        goto L_0x016d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p033e.C1365ab.m6192b():boolean");
    }

    /* renamed from: c */
    private HashMap<String, String> m6193c() throws Exception {
        int i = 1;
        HashMap hashMap = new HashMap();
        hashMap.put("method", ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN);
        C1370b.m6206b(hashMap);
        hashMap.put("device_type", "3");
        hashMap.put("device_id", C1471e.m6699b(this.f4805a));
        hashMap.put("device_name", Build.MODEL);
        int b = C1550n.m6960b(this.f4805a, "com.baidu.android.pushservice.PushManager.LOGIN_TYPE", -1);
        String a = C1550n.m6955a(this.f4805a, "com.baidu.android.pushservice.PushManager.LONGIN_VALUE");
        if (b == 2) {
            hashMap.put("bduss", C1550n.m6955a(this.f4805a, "com.baidu.android.pushservice.PushManager.BDUSS"));
            hashMap.put("appid", a);
        } else if (b == 1) {
            hashMap.put("access_token", a);
        } else {
            hashMap.put("apikey", a);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("api_level", VERSION.SDK_INT);
        int[] b2 = C1578v.m7099b(this.f4805a);
        jSONObject.put("screen_height", b2[0]);
        jSONObject.put("screen_width", b2[1]);
        Object obj = Build.MODEL;
        if (obj.length() > 128) {
            obj = obj.substring(0, TransportMediator.KEYCODE_MEDIA_PAUSE);
        }
        jSONObject.put("model", obj);
        jSONObject.put("isroot", C1578v.m7081a(this.f4805a) ? 1 : 0);
        a = "is_baidu_app";
        if (!C1578v.m7116e(this.f4805a, this.f4805a.getPackageName())) {
            i = 0;
        }
        jSONObject.put(a, i);
        jSONObject.put("push_sdk_version", C1328a.m6003a());
        obj = Build.MANUFACTURER;
        if (obj.length() > 128) {
            obj = obj.substring(0, TransportMediator.KEYCODE_MEDIA_PAUSE);
        }
        jSONObject.put("manufacturer", obj);
        hashMap.put("info", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        C1425a.m6442c("TokenRequester", "TOKEN param -- " + C1370b.m6202a(hashMap));
        return hashMap;
    }

    /* renamed from: d */
    private void m6194d() {
        this.f4807c++;
        if (this.f4807c < this.f4806b) {
            int i = ((1 << (this.f4807c - 1)) * 5) * 1000;
            C1425a.m6441b("TokenRequester", "schedule retry-- retry times: " + this.f4807c + "time delay: " + i);
            try {
                Thread.sleep((long) i);
                return;
            } catch (InterruptedException e) {
                C1425a.m6444e("TokenRequester", "error : " + e.getMessage());
                return;
            }
        }
        C1425a.m6441b("TokenRequester", "hava reconnect " + this.f4806b + " times, all failed.");
        this.f4808d = false;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13727a() {
        boolean b;
        do {
            b = m6192b();
            if (this.f4808d) {
                m6194d();
            }
            if (this.f4806b <= 0) {
                break;
            }
        } while (this.f4808d);
        if (this.f4810f != null) {
            this.f4810f.mo13717a(Boolean.valueOf(b));
            C1425a.m6442c("TokenRequester", "get ChannelToken result = " + b);
        } else {
            C1578v.m7095b("TokenRequester#execute-->mListener is null !!!!!", this.f4805a);
        }
        C1425a.m6441b("TokenRequester", "RequestTokenThread connectResult: " + b);
        C1578v.m7095b("TokenRequester#execute-->RequestTokenThread connectResult: " + b, this.f4805a);
        if (b) {
            C1370b.m6203a(this.f4805a);
            if (C1427h.f4998a != null) {
                C1425a.m6441b("TokenRequester", "TokenRequester start PushService after Request finish.");
                C1578v.m7095b("TokenRequester#execute-->TokenRequester start PushService after Request finish. ", this.f4805a);
                C1577u.m7045a(this.f4805a, new Intent());
                return;
            }
            return;
        }
        C1578v.m7121h(this.f4805a);
    }

    /* renamed from: a */
    public void mo13728a(int i) {
        this.f4806b = i;
    }

    public void run() {
        mo13727a();
    }
}
