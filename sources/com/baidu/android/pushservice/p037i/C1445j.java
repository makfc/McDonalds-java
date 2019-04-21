package com.baidu.android.pushservice.p037i;

import android.content.Context;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1544h;
import com.baidu.android.pushservice.util.C1545i;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.baidu.android.pushservice.i.j */
public class C1445j extends C1443q {
    /* renamed from: e */
    private static C1445j f5102e = null;
    /* renamed from: c */
    private String f5103c;
    /* renamed from: d */
    private C1544h f5104d;

    private C1445j(Context context) {
        super(context);
        this.f5103c = "LbsSender";
        this.f5104d = null;
        this.f5097b = "https://lbsonline.pushct.baidu.com/lbsupload";
    }

    /* renamed from: a */
    public static C1445j m6565a(Context context) {
        if (f5102e == null) {
            f5102e = new C1445j(context);
        }
        return f5102e;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public String mo13893a(boolean z) {
        return C1545i.m6931a(this.f5096a, z);
    }

    /* renamed from: a */
    public void mo13903a(C1544h c1544h) {
        this.f5104d = c1544h;
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x003f  */
    /* renamed from: a */
    public void mo13894a(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = r5.f5096a;
        r2 = java.lang.System.currentTimeMillis();
        com.baidu.android.pushservice.util.C1545i.m6932a(r0, r2);
        r0 = r5.f5103c;
        r2 = "<<< Location info send result return OK!";
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r0, r2);
        r0 = r5.f5103c;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Lbs upload respponse: ";
        r2 = r2.append(r3);
        r2 = r2.append(r6);
        r2 = r2.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r2);
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0058 }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r6);	 Catch:{ JSONException -> 0x0058 }
        r2 = "lbsInfo";
        r2 = r0.has(r2);	 Catch:{ JSONException -> 0x0058 }
        if (r2 == 0) goto L_0x0075;
    L_0x0037:
        r2 = "lbsInfo";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x0058 }
    L_0x003d:
        if (r0 == 0) goto L_0x0057;
    L_0x003f:
        r2 = r5.f5096a;
        r0 = com.baidu.android.pushservice.util.C1545i.m6930a(r2, r0);
        r2 = r5.f5104d;
        if (r2 == 0) goto L_0x0057;
    L_0x0049:
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x0057;
    L_0x004f:
        r2 = r5.f5104d;
        r3 = 0;
        r2.mo14065a(r3, r0);
        r5.f5104d = r1;
    L_0x0057:
        return;
    L_0x0058:
        r0 = move-exception;
        r2 = r5.f5103c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = " e ";
        r3 = r3.append(r4);
        r0 = r0.getMessage();
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r0);
    L_0x0075:
        r0 = r1;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p037i.C1445j.mo13894a(java.lang.String):void");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo13895a(String str, List<NameValuePair> list) {
        list.add(new BasicNameValuePair("method", "uploadGeo"));
        C1425a.m6442c(this.f5103c, "Sending LBS data: " + str);
        list.add(new BasicNameValuePair(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, str));
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public boolean mo13896a() {
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public boolean mo13899b() {
        return true;
    }
}
