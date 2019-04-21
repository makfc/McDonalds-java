package com.amap.api.mapcore.util;

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
/* renamed from: com.amap.api.mapcore.util.dm */
public class C0811dm {
    /* renamed from: a */
    public static int f1758a = -1;
    /* renamed from: b */
    public static String f1759b = "";
    /* renamed from: c */
    private static SDKInfo f1760c;
    /* renamed from: d */
    private static String f1761d = "http://apiinit.amap.com/v3/log/init";
    /* renamed from: e */
    private static String f1762e = null;

    /* renamed from: a */
    private static boolean m2393a(Context context, SDKInfo sDKInfo, boolean z) {
        boolean z2 = true;
        f1760c = sDKInfo;
        try {
            String a = C0811dm.m2389a();
            Map hashMap = new HashMap();
            hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded");
            hashMap.put("Accept-Encoding", "gzip");
            hashMap.put("Connection", "Keep-Alive");
            hashMap.put("User-Agent", f1760c.mo9295c());
            hashMap.put("X-INFO", ClientInfo.m2400a(context, f1760c, null, z));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{f1760c.mo9294b(), f1760c.mo9292a()}));
            BaseNetManager a2 = BaseNetManager.m2800a();
            AuthRequest authRequest = new AuthRequest();
            authRequest.mo8903a(ProxyUtil.m2471a(context));
            authRequest.mo9299a(hashMap);
            authRequest.mo9300b(C0811dm.m2390a(context));
            authRequest.mo9298a(a);
            return C0811dm.m2394a(a2.mo9415b(authRequest));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "Auth", "getAuth");
            return z2;
        }
    }

    @Deprecated
    /* renamed from: a */
    public static synchronized boolean m2392a(Context context, SDKInfo sDKInfo) {
        boolean a;
        synchronized (C0811dm.class) {
            a = C0811dm.m2393a(context, sDKInfo, false);
        }
        return a;
    }

    /* renamed from: a */
    public static void m2391a(String str) {
        AppInfo.m2382a(str);
    }

    /* renamed from: a */
    private static String m2389a() {
        return f1761d;
    }

    /* renamed from: a */
    private static boolean m2394a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject init = JSONObjectInstrumentation.init(Utils.m2509a(bArr));
            if (init.has("status")) {
                int i = init.getInt("status");
                if (i == 1) {
                    f1758a = 1;
                } else if (i == 0) {
                    f1758a = 0;
                }
            }
            if (init.has("info")) {
                f1759b = init.getString("info");
            }
            if (f1758a == 0) {
                Log.i("AuthFailure", f1759b);
            }
            if (f1758a != 1) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            BasicLogHandler.m2542a(e, "Auth", "lData");
            return false;
        } catch (Throwable e2) {
            BasicLogHandler.m2542a(e2, "Auth", "lData");
            return false;
        }
    }

    /* renamed from: a */
    private static Map<String, String> m2390a(Context context) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", Utf8Charset.NAME);
            String a = ClientInfo.m2396a();
            hashMap.put("ts", a);
            hashMap.put(Parameters.API_KEY, AppInfo.m2387f(context));
            hashMap.put("scode", ClientInfo.m2401a(context, a, Utils.m2521d("resType=json&encode=UTF-8&key=" + AppInfo.m2387f(context))));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "Auth", "gParams");
        }
        return hashMap;
    }
}
