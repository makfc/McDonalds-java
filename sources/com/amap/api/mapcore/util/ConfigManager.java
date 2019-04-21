package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* renamed from: com.amap.api.mapcore.util.dp */
public class ConfigManager {

    @Deprecated
    /* compiled from: ConfigManager */
    /* renamed from: com.amap.api.mapcore.util.dp$a */
    public static class C0817a {
        /* renamed from: a */
        public JSONObject f1790a;
        /* renamed from: b */
        public JSONObject f1791b;
        /* renamed from: c */
        public JSONObject f1792c;
        /* renamed from: d */
        public JSONObject f1793d;
        @Deprecated
        /* renamed from: e */
        public JSONObject f1794e;
        /* renamed from: f */
        public JSONObject f1795f;
        /* renamed from: g */
        public C0814a f1796g;
        /* renamed from: h */
        public C0816c f1797h;
        /* renamed from: i */
        public C0815b f1798i;

        /* compiled from: ConfigManager */
        /* renamed from: com.amap.api.mapcore.util.dp$a$a */
        public static class C0814a {
            /* renamed from: a */
            public boolean f1783a;
            /* renamed from: b */
            public boolean f1784b;
        }

        /* compiled from: ConfigManager */
        /* renamed from: com.amap.api.mapcore.util.dp$a$b */
        public static class C0815b {
            /* renamed from: a */
            public String f1785a;
            /* renamed from: b */
            public String f1786b;
        }

        /* compiled from: ConfigManager */
        /* renamed from: com.amap.api.mapcore.util.dp$a$c */
        public static class C0816c {
            /* renamed from: a */
            public String f1787a;
            /* renamed from: b */
            public String f1788b;
            /* renamed from: c */
            public String f1789c;
        }
    }

    /* compiled from: ConfigManager */
    /* renamed from: com.amap.api.mapcore.util.dp$b */
    static class C0818b extends Request {
        /* renamed from: a */
        private Context f1799a;
        /* renamed from: b */
        private SDKInfo f1800b;
        /* renamed from: c */
        private String f1801c = "";

        C0818b(Context context, SDKInfo sDKInfo, String str) {
            this.f1799a = context;
            this.f1800b = sDKInfo;
            this.f1801c = str;
        }

        /* renamed from: c */
        public Map<String, String> mo8907c() {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", this.f1800b.mo9295c());
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.f1800b.mo9294b(), this.f1800b.mo9292a()}));
            hashMap.put("logversion", "2.0");
            return hashMap;
        }

        /* renamed from: b */
        public Map<String, String> mo8905b() {
            Object q = C0820dq.m2443q(this.f1799a);
            if (!TextUtils.isEmpty(q)) {
                q = C0822ds.m2464b(new StringBuilder(q).reverse().toString());
            }
            Map hashMap = new HashMap();
            hashMap.put(Parameters.API_KEY, AppInfo.m2387f(this.f1799a));
            hashMap.put("opertype", this.f1801c);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.f1800b.mo9292a());
            hashMap.put("version", this.f1800b.mo9294b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", VERSION.SDK_INT + "");
            hashMap.put("deviceId", q);
            hashMap.put("abitype", Build.CPU_ABI);
            hashMap.put("ext", this.f1800b.mo9296d());
            String a = ClientInfo.m2396a();
            String a2 = ClientInfo.m2401a(this.f1799a, a, Utils.m2517b(hashMap));
            hashMap.put("ts", a);
            hashMap.put("scode", a2);
            return hashMap;
        }

        /* renamed from: a */
        public String mo8901a() {
            return "https://restapi.amap.com/v3/fastconnect";
        }
    }

    @Deprecated
    /* renamed from: a */
    public static C0817a m2414a(byte[] bArr) {
        boolean z = false;
        C0817a c0817a = new C0817a();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    JSONObject init = JSONObjectInstrumentation.init(Utils.m2509a(bArr));
                    if ("1".equals(ConfigManager.m2415a(init, "status")) && init.has("result")) {
                        JSONObject jSONObject = init.getJSONObject("result");
                        if (jSONObject != null) {
                            boolean b;
                            JSONObject jSONObject2;
                            if (Utils.m2513a(jSONObject, "exception")) {
                                b = ConfigManager.m2420b(jSONObject.getJSONObject("exception"));
                            } else {
                                b = false;
                            }
                            if (Utils.m2513a(jSONObject, "common")) {
                                z = ConfigManager.m2419a(jSONObject.getJSONObject("common"));
                            }
                            C0814a c0814a = new C0814a();
                            c0814a.f1783a = b;
                            c0814a.f1784b = z;
                            c0817a.f1796g = c0814a;
                            if (jSONObject.has("sdkupdate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkupdate");
                                C0816c c0816c = new C0816c();
                                ConfigManager.m2417a(jSONObject2, c0816c);
                                c0817a.f1797h = c0816c;
                            }
                            if (Utils.m2513a(jSONObject, "sdkcoordinate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkcoordinate");
                                C0815b c0815b = new C0815b();
                                ConfigManager.m2416a(jSONObject2, c0815b);
                                c0817a.f1798i = c0815b;
                            }
                            if (Utils.m2513a(jSONObject, "callamap")) {
                                c0817a.f1794e = jSONObject.getJSONObject("callamap");
                            }
                            if (Utils.m2513a(jSONObject, "ca")) {
                                c0817a.f1795f = jSONObject.getJSONObject("ca");
                            }
                            if (Utils.m2513a(jSONObject, "locate")) {
                                c0817a.f1793d = jSONObject.getJSONObject("locate");
                            }
                            if (Utils.m2513a(jSONObject, "callamappro")) {
                                c0817a.f1792c = jSONObject.getJSONObject("callamappro");
                            }
                            if (Utils.m2513a(jSONObject, "opflag")) {
                                c0817a.f1791b = jSONObject.getJSONObject("opflag");
                            }
                            if (Utils.m2513a(jSONObject, "amappushflag")) {
                                c0817a.f1790a = jSONObject.getJSONObject("amappushflag");
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "ConfigManager", "loadConfig");
            }
        }
        return c0817a;
    }

    @Deprecated
    /* renamed from: a */
    public static C0817a m2413a(Context context, SDKInfo sDKInfo, String str) {
        try {
            return ConfigManager.m2414a(new BaseNetManager().mo9414a(new C0818b(context, sDKInfo, str)));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "ConfigManager", "loadConfig");
            return new C0817a();
        }
    }

    /* renamed from: a */
    private static boolean m2418a(String str) {
        if (str != null && str.equals("1")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static String m2415a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        if (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return str2;
        }
        return jSONObject.optString(str);
    }

    /* renamed from: a */
    private static void m2416a(JSONObject jSONObject, C0815b c0815b) {
        if (jSONObject != null) {
            try {
                String a = ConfigManager.m2415a(jSONObject, "md5");
                String a2 = ConfigManager.m2415a(jSONObject, NativeProtocol.IMAGE_URL_KEY);
                c0815b.f1786b = a;
                c0815b.f1785a = a2;
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    /* renamed from: a */
    private static void m2417a(JSONObject jSONObject, C0816c c0816c) {
        if (jSONObject != null) {
            try {
                String a = ConfigManager.m2415a(jSONObject, "md5");
                String a2 = ConfigManager.m2415a(jSONObject, NativeProtocol.IMAGE_URL_KEY);
                String a3 = ConfigManager.m2415a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    c0816c.f1787a = a2;
                    c0816c.f1788b = a;
                    c0816c.f1789c = a3;
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    /* renamed from: a */
    private static boolean m2419a(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject == null) {
            return z;
        }
        try {
            return ConfigManager.m2418a(ConfigManager.m2415a(jSONObject.getJSONObject("commoninfo"), "com_isupload"));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "ConfigManager", "parseCommon");
            return z;
        }
    }

    /* renamed from: b */
    private static boolean m2420b(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject == null) {
            return z;
        }
        try {
            return ConfigManager.m2418a(ConfigManager.m2415a(jSONObject.getJSONObject("exceptinfo"), "ex_isupload"));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "ConfigManager", "parseException");
            return z;
        }
    }
}
