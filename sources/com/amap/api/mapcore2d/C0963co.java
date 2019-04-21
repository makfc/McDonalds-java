package com.amap.api.mapcore2d;

import android.content.Context;
import android.util.Log;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* compiled from: AuthManager */
/* renamed from: com.amap.api.mapcore2d.co */
public class C0963co {
    /* renamed from: a */
    public static int f2733a = -1;
    /* renamed from: b */
    public static String f2734b = "";
    /* renamed from: c */
    private static C0977cv f2735c;
    /* renamed from: d */
    private static String f2736d = "http://apiinit.amap.com/v3/log/init";
    /* renamed from: e */
    private static String f2737e = null;

    /* renamed from: a */
    private static boolean m3933a(Context context, C0977cv c0977cv, boolean z) {
        boolean z2 = true;
        f2735c = c0977cv;
        try {
            String a = C0963co.m3929a();
            Map hashMap = new HashMap();
            hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded");
            hashMap.put("Accept-Encoding", "gzip");
            hashMap.put("Connection", "Keep-Alive");
            hashMap.put("User-Agent", f2735c.mo10175c());
            hashMap.put("X-INFO", C0966cp.m3939a(context, f2735c, null, z));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{f2735c.mo10174b(), f2735c.mo10172a()}));
            C1021ea a2 = C1021ea.m4260a();
            C0979cx c0979cx = new C0979cx();
            c0979cx.mo10068a(C0971ct.m4002a(context));
            c0979cx.mo10179a(hashMap);
            c0979cx.mo10180b(C0963co.m3930a(context));
            c0979cx.mo10178a(a);
            return C0963co.m3934a(a2.mo10270b(c0979cx));
        } catch (Throwable th) {
            C0982da.m4076a(th, "Auth", "getAuth");
            return z2;
        }
    }

    @Deprecated
    /* renamed from: a */
    public static synchronized boolean m3932a(Context context, C0977cv c0977cv) {
        boolean a;
        synchronized (C0963co.class) {
            a = C0963co.m3933a(context, c0977cv, false);
        }
        return a;
    }

    /* renamed from: a */
    public static void m3931a(String str) {
        C0957cm.m3901a(str);
    }

    /* renamed from: a */
    private static String m3929a() {
        return f2736d;
    }

    /* renamed from: a */
    private static boolean m3934a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject init = JSONObjectInstrumentation.init(C0978cw.m4044a(bArr));
            if (init.has("status")) {
                int i = init.getInt("status");
                if (i == 1) {
                    f2733a = 1;
                } else if (i == 0) {
                    f2733a = 0;
                }
            }
            if (init.has("info")) {
                f2734b = init.getString("info");
            }
            if (f2733a == 0) {
                Log.i("AuthFailure", f2734b);
            }
            if (f2733a != 1) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            C0982da.m4076a(e, "Auth", "lData");
            return false;
        } catch (Throwable e2) {
            C0982da.m4076a(e2, "Auth", "lData");
            return false;
        }
    }

    /* renamed from: a */
    private static Map<String, String> m3930a(Context context) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", Utf8Charset.NAME);
            String a = C0966cp.m3935a();
            hashMap.put("ts", a);
            hashMap.put(Parameters.API_KEY, C0957cm.m3906f(context));
            hashMap.put("scode", C0966cp.m3940a(context, a, C0978cw.m4055d("resType=json&encode=UTF-8&key=" + C0957cm.m3906f(context))));
        } catch (Throwable th) {
            C0982da.m4076a(th, "Auth", "gParams");
        }
        return hashMap;
    }
}
