package com.amap.api.mapcore2d;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import java.net.URLDecoder;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.mapcore2d.cn */
public class AuthConfigManager {
    /* renamed from: a */
    public static int f2731a = -1;
    /* renamed from: b */
    public static String f2732b = "";

    /* compiled from: AuthConfigManager */
    /* renamed from: com.amap.api.mapcore2d.cn$a */
    public static class C0961a {
        /* renamed from: a */
        public String f2713a;
        /* renamed from: b */
        public int f2714b = -1;
        /* renamed from: c */
        public JSONObject f2715c;
        /* renamed from: d */
        public JSONObject f2716d;
        /* renamed from: e */
        public JSONObject f2717e;
        /* renamed from: f */
        public JSONObject f2718f;
        /* renamed from: g */
        public JSONObject f2719g;
        /* renamed from: h */
        public JSONObject f2720h;
        /* renamed from: i */
        public JSONObject f2721i;
        /* renamed from: j */
        public JSONObject f2722j;
        /* renamed from: k */
        public JSONObject f2723k;
        /* renamed from: l */
        public C0958a f2724l;
        /* renamed from: m */
        public C0960c f2725m;
        /* renamed from: n */
        public C0959b f2726n;

        /* compiled from: AuthConfigManager */
        /* renamed from: com.amap.api.mapcore2d.cn$a$a */
        public static class C0958a {
            /* renamed from: a */
            public boolean f2706a;
            /* renamed from: b */
            public boolean f2707b;
        }

        /* compiled from: AuthConfigManager */
        /* renamed from: com.amap.api.mapcore2d.cn$a$b */
        public static class C0959b {
            /* renamed from: a */
            public String f2708a;
            /* renamed from: b */
            public String f2709b;
        }

        /* compiled from: AuthConfigManager */
        /* renamed from: com.amap.api.mapcore2d.cn$a$c */
        public static class C0960c {
            /* renamed from: a */
            public String f2710a;
            /* renamed from: b */
            public String f2711b;
            /* renamed from: c */
            public String f2712c;
        }
    }

    /* compiled from: AuthConfigManager */
    /* renamed from: com.amap.api.mapcore2d.cn$b */
    static class C0962b extends BinaryRequest {
        /* renamed from: f */
        private String f2729f;
        /* renamed from: g */
        private Map<String, String> f2730g;

        C0962b(Context context, C0977cv c0977cv, String str, Map<String, String> map) {
            super(context, c0977cv);
            this.f2729f = str;
            this.f2730g = map;
        }

        /* renamed from: e */
        public Map<String, String> mo10071e() {
            return null;
        }

        /* renamed from: g */
        public String mo10073g() {
            return "https://restapi.amap.com/v3/iasdkauth";
        }

        /* renamed from: a */
        public byte[] mo10156a() {
            return null;
        }

        /* renamed from: b */
        public byte[] mo10158b() {
            return C0978cw.m4050a(C0978cw.m4043a(m3918j()));
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: c */
        public String mo10159c() {
            return "3.0";
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x00ab  */
        /* renamed from: j */
        private java.util.Map<java.lang.String, java.lang.String> m3918j() {
            /*
            r5 = this;
            r0 = r5.f2727a;
            r0 = com.amap.api.mapcore2d.C0968cq.m3974q(r0);
            r1 = android.text.TextUtils.isEmpty(r0);
            if (r1 != 0) goto L_0x001d;
        L_0x000c:
            r1 = new java.lang.StringBuilder;
            r1.<init>(r0);
            r0 = r1.reverse();
            r0 = r0.toString();
            r0 = com.amap.api.mapcore2d.C0970cs.m3995b(r0);
        L_0x001d:
            r2 = new java.util.HashMap;
            r2.<init>();
            r1 = "authkey";
            r3 = r5.f2729f;
            r2.put(r1, r3);
            r1 = "plattype";
            r3 = "android";
            r2.put(r1, r3);
            r1 = "product";
            r3 = r5.f2728b;
            r3 = r3.mo10172a();
            r2.put(r1, r3);
            r1 = "version";
            r3 = r5.f2728b;
            r3 = r3.mo10174b();
            r2.put(r1, r3);
            r1 = "output";
            r3 = "json";
            r2.put(r1, r3);
            r1 = "androidversion";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = android.os.Build.VERSION.SDK_INT;
            r3 = r3.append(r4);
            r4 = "";
            r3 = r3.append(r4);
            r3 = r3.toString();
            r2.put(r1, r3);
            r1 = "deviceId";
            r2.put(r1, r0);
            r0 = r5.f2730g;
            if (r0 == 0) goto L_0x007e;
        L_0x0071:
            r0 = r5.f2730g;
            r0 = r0.isEmpty();
            if (r0 != 0) goto L_0x007e;
        L_0x0079:
            r0 = r5.f2730g;
            r2.putAll(r0);
        L_0x007e:
            r1 = 0;
            r0 = android.os.Build.VERSION.SDK_INT;
            r3 = 21;
            if (r0 < r3) goto L_0x00c6;
        L_0x0085:
            r0 = r5.f2727a;	 Catch:{ Throwable -> 0x00be }
            r0 = r0.getApplicationInfo();	 Catch:{ Throwable -> 0x00be }
            r3 = android.content.pm.ApplicationInfo.class;
            r3 = r3.getName();	 Catch:{ Throwable -> 0x00be }
            r3 = java.lang.Class.forName(r3);	 Catch:{ Throwable -> 0x00be }
            r4 = "primaryCpuAbi";
            r3 = r3.getDeclaredField(r4);	 Catch:{ Throwable -> 0x00be }
            r4 = 1;
            r3.setAccessible(r4);	 Catch:{ Throwable -> 0x00be }
            r0 = r3.get(r0);	 Catch:{ Throwable -> 0x00be }
            r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x00be }
        L_0x00a5:
            r1 = android.text.TextUtils.isEmpty(r0);
            if (r1 == 0) goto L_0x00ad;
        L_0x00ab:
            r0 = android.os.Build.CPU_ABI;
        L_0x00ad:
            r1 = "abitype";
            r2.put(r1, r0);
            r0 = "ext";
            r1 = r5.f2728b;
            r1 = r1.mo10176d();
            r2.put(r0, r1);
            return r2;
        L_0x00be:
            r0 = move-exception;
            r3 = "ConfigManager";
            r4 = "getcpu";
            com.amap.api.mapcore2d.C0982da.m4076a(r0, r3, r4);
        L_0x00c6:
            r0 = r1;
            goto L_0x00a5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.AuthConfigManager$C0962b.m3918j():java.util.Map");
        }
    }

    /* renamed from: a */
    public static boolean m3928a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split("/");
            if (split[split.length - 1].charAt(4) % 2 == 1) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return z;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* renamed from: a */
    public static com.amap.api.mapcore2d.AuthConfigManager.C0961a m3924a(android.content.Context r9, com.amap.api.mapcore2d.C0977cv r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
        r3 = 0;
        r8 = 1;
        r1 = new com.amap.api.mapcore2d.cn$a;
        r1.<init>();
        r0 = new com.amap.api.mapcore2d.ea;	 Catch:{ cl -> 0x0055, IllegalBlockSizeException -> 0x005f, Throwable -> 0x0063 }
        r0.<init>();	 Catch:{ cl -> 0x0055, IllegalBlockSizeException -> 0x005f, Throwable -> 0x0063 }
        r2 = new com.amap.api.mapcore2d.cn$b;	 Catch:{ cl -> 0x0055, IllegalBlockSizeException -> 0x005f, Throwable -> 0x0063 }
        r2.<init>(r9, r10, r11, r12);	 Catch:{ cl -> 0x0055, IllegalBlockSizeException -> 0x005f, Throwable -> 0x0063 }
        r2 = r0.mo10269a(r2);	 Catch:{ cl -> 0x0055, IllegalBlockSizeException -> 0x005f, Throwable -> 0x0063 }
        r0 = 16;
        r0 = new byte[r0];	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r4 = r2.length;	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r4 = r4 + -16;
        r4 = new byte[r4];	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r5 = 0;
        r6 = 0;
        r7 = 16;
        java.lang.System.arraycopy(r2, r5, r0, r6, r7);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r5 = 16;
        r6 = 0;
        r7 = r2.length;	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r7 = r7 + -16;
        java.lang.System.arraycopy(r2, r5, r4, r6, r7);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r5 = new javax.crypto.spec.SecretKeySpec;	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r6 = "AES";
        r5.<init>(r0, r6);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r0 = "AES/CBC/PKCS5Padding";
        r0 = javax.crypto.Cipher.getInstance(r0);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r6 = new javax.crypto.spec.IvParameterSpec;	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r7 = com.amap.api.mapcore2d.C0978cw.m4049a();	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r6.<init>(r7);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r7 = 2;
        r0.init(r7, r5, r6);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r0 = r0.doFinal(r4);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r3 = com.amap.api.mapcore2d.C0978cw.m4044a(r0);	 Catch:{ cl -> 0x01d7, IllegalBlockSizeException -> 0x01d4, Throwable -> 0x01d1 }
        r0 = r3;
    L_0x0051:
        if (r2 != 0) goto L_0x006e;
    L_0x0053:
        r0 = r1;
    L_0x0054:
        return r0;
    L_0x0055:
        r0 = move-exception;
        r2 = r3;
    L_0x0057:
        r0 = r0.mo10154a();
        r1.f2713a = r0;
        r0 = r3;
        goto L_0x0051;
    L_0x005f:
        r0 = move-exception;
        r2 = r3;
    L_0x0061:
        r0 = r3;
        goto L_0x0051;
    L_0x0063:
        r0 = move-exception;
        r2 = r3;
    L_0x0065:
        r4 = "ConfigManager";
        r5 = "loadConfig";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r4, r5);
        r0 = r3;
        goto L_0x0051;
    L_0x006e:
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 == 0) goto L_0x0078;
    L_0x0074:
        r0 = com.amap.api.mapcore2d.C0978cw.m4044a(r2);
    L_0x0078:
        r2 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01c6 }
        r2 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r0);	 Catch:{ Throwable -> 0x01c6 }
        r0 = "status";
        r0 = r2.has(r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x01ce;
    L_0x0086:
        r0 = "status";
        r0 = r2.getInt(r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 != r8) goto L_0x019c;
    L_0x008e:
        r0 = 1;
        f2731a = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0091:
        r0 = "ver";
        r0 = r2.has(r0);	 Catch:{ Throwable -> 0x01bc }
        if (r0 == 0) goto L_0x00a3;
    L_0x009a:
        r0 = "ver";
        r0 = r2.getInt(r0);	 Catch:{ Throwable -> 0x01bc }
        r1.f2714b = r0;	 Catch:{ Throwable -> 0x01bc }
    L_0x00a3:
        r0 = "result";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0199;
    L_0x00ab:
        r0 = new com.amap.api.mapcore2d.cn$a$a;	 Catch:{ Throwable -> 0x01c6 }
        r0.<init>();	 Catch:{ Throwable -> 0x01c6 }
        r3 = 0;
        r0.f2706a = r3;	 Catch:{ Throwable -> 0x01c6 }
        r3 = 0;
        r0.f2707b = r3;	 Catch:{ Throwable -> 0x01c6 }
        r1.f2724l = r0;	 Catch:{ Throwable -> 0x01c6 }
        r3 = "result";
        r2 = r2.getJSONObject(r3);	 Catch:{ Throwable -> 0x01c6 }
        r3 = "11K";
        r3 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r3);	 Catch:{ Throwable -> 0x01c6 }
        if (r3 == 0) goto L_0x00d9;
    L_0x00c6:
        r3 = "11K";
        r3 = r2.getJSONObject(r3);	 Catch:{ Throwable -> 0x01c6 }
        r4 = "able";
        r3 = r3.getString(r4);	 Catch:{ Throwable -> 0x01c6 }
        r4 = 0;
        r3 = com.amap.api.mapcore2d.AuthConfigManager.m3928a(r3, r4);	 Catch:{ Throwable -> 0x01c6 }
        r0.f2706a = r3;	 Catch:{ Throwable -> 0x01c6 }
    L_0x00d9:
        r0 = "11B";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x00e9;
    L_0x00e1:
        r0 = "11B";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2715c = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x00e9:
        r0 = "11C";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x00f9;
    L_0x00f1:
        r0 = "11C";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2716d = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x00f9:
        r0 = "11I";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0109;
    L_0x0101:
        r0 = "11I";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2717e = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0109:
        r0 = "11H";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0119;
    L_0x0111:
        r0 = "11H";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2718f = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0119:
        r0 = "11E";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0129;
    L_0x0121:
        r0 = "11E";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2719g = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0129:
        r0 = "11F";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0139;
    L_0x0131:
        r0 = "11F";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2720h = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0139:
        r0 = "11G";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0149;
    L_0x0141:
        r0 = "11G";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2721i = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0149:
        r0 = "001";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0161;
    L_0x0151:
        r0 = "001";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r3 = new com.amap.api.mapcore2d.cn$a$c;	 Catch:{ Throwable -> 0x01c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x01c6 }
        com.amap.api.mapcore2d.AuthConfigManager.m3927a(r0, r3);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2725m = r3;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0161:
        r0 = "002";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0179;
    L_0x0169:
        r0 = "002";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r3 = new com.amap.api.mapcore2d.cn$a$b;	 Catch:{ Throwable -> 0x01c6 }
        r3.<init>();	 Catch:{ Throwable -> 0x01c6 }
        com.amap.api.mapcore2d.AuthConfigManager.m3926a(r0, r3);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2726n = r3;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0179:
        r0 = "006";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0189;
    L_0x0181:
        r0 = "006";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2722j = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0189:
        r0 = "010";
        r0 = com.amap.api.mapcore2d.C0978cw.m4048a(r2, r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x0199;
    L_0x0191:
        r0 = "010";
        r0 = r2.getJSONObject(r0);	 Catch:{ Throwable -> 0x01c6 }
        r1.f2723k = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x0199:
        r0 = r1;
        goto L_0x0054;
    L_0x019c:
        if (r0 != 0) goto L_0x0091;
    L_0x019e:
        r0 = 0;
        f2731a = r0;	 Catch:{ Throwable -> 0x01c6 }
        r0 = "info";
        r0 = r2.has(r0);	 Catch:{ Throwable -> 0x01c6 }
        if (r0 == 0) goto L_0x01b1;
    L_0x01a9:
        r0 = "info";
        r0 = r2.getString(r0);	 Catch:{ Throwable -> 0x01c6 }
        f2732b = r0;	 Catch:{ Throwable -> 0x01c6 }
    L_0x01b1:
        r0 = f2731a;	 Catch:{ Throwable -> 0x01c6 }
        if (r0 != 0) goto L_0x0091;
    L_0x01b5:
        r0 = f2732b;	 Catch:{ Throwable -> 0x01c6 }
        r1.f2713a = r0;	 Catch:{ Throwable -> 0x01c6 }
        r0 = r1;
        goto L_0x0054;
    L_0x01bc:
        r0 = move-exception;
        r3 = "AuthConfigManager";
        r4 = "loadConfigVer";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r3, r4);	 Catch:{ Throwable -> 0x01c6 }
        goto L_0x00a3;
    L_0x01c6:
        r0 = move-exception;
        r2 = "AuthConfigManager";
        r3 = "loadConfig";
        com.amap.api.mapcore2d.C0982da.m4076a(r0, r2, r3);
    L_0x01ce:
        r0 = r1;
        goto L_0x0054;
    L_0x01d1:
        r0 = move-exception;
        goto L_0x0065;
    L_0x01d4:
        r0 = move-exception;
        goto L_0x0061;
    L_0x01d7:
        r0 = move-exception;
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.AuthConfigManager.m3924a(android.content.Context, com.amap.api.mapcore2d.cv, java.lang.String, java.util.Map):com.amap.api.mapcore2d.cn$a");
    }

    /* renamed from: a */
    public static String m3925a(JSONObject jSONObject, String str) throws JSONException {
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
    private static void m3926a(JSONObject jSONObject, C0959b c0959b) {
        if (jSONObject != null) {
            try {
                String a = AuthConfigManager.m3925a(jSONObject, "md5");
                String a2 = AuthConfigManager.m3925a(jSONObject, NativeProtocol.IMAGE_URL_KEY);
                c0959b.f2709b = a;
                c0959b.f2708a = a2;
            } catch (Throwable th) {
                C0982da.m4076a(th, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    /* renamed from: a */
    private static void m3927a(JSONObject jSONObject, C0960c c0960c) {
        if (jSONObject != null) {
            try {
                String a = AuthConfigManager.m3925a(jSONObject, "md5");
                String a2 = AuthConfigManager.m3925a(jSONObject, NativeProtocol.IMAGE_URL_KEY);
                String a3 = AuthConfigManager.m3925a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    c0960c.f2710a = a2;
                    c0960c.f2711b = a;
                    c0960c.f2712c = a3;
                }
            } catch (Throwable th) {
                C0982da.m4076a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }
}
