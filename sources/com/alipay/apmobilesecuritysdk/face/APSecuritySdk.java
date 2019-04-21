package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.apmobilesecuritysdk.p013a.C0549a;
import com.alipay.apmobilesecuritysdk.p014b.C0550a;
import com.alipay.apmobilesecuritysdk.p017e.C0559a;
import com.alipay.apmobilesecuritysdk.p017e.C0562d;
import com.alipay.apmobilesecuritysdk.p017e.C0565g;
import com.alipay.apmobilesecuritysdk.p017e.C0566h;
import com.alipay.apmobilesecuritysdk.p017e.C0567i;
import com.alipay.apmobilesecuritysdk.p018f.C0569b;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;
import java.util.Map;

public class APSecuritySdk {
    /* renamed from: a */
    private static APSecuritySdk f444a;
    /* renamed from: c */
    private static Object f445c = new Object();
    /* renamed from: b */
    private Context f446b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;
    }

    private APSecuritySdk(Context context) {
        this.f446b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f444a == null) {
            synchronized (f445c) {
                if (f444a == null) {
                    f444a = new APSecuritySdk(context);
                }
            }
        }
        return f444a;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public String getApdidToken() {
        String a = C0549a.m632a(this.f446b, "");
        if (C0689a.m1169a(a)) {
            initToken(0, new HashMap(), null);
        }
        return a;
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAY";
    }

    public String getSdkVersion() {
        return "3.2.2-20180331";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        Object obj = null;
        synchronized (this) {
            tokenResult = new TokenResult();
            try {
                tokenResult.apdidToken = C0549a.m632a(this.f446b, "");
                tokenResult.clientKey = C0566h.m708f(this.f446b);
                tokenResult.apdid = C0549a.m631a(this.f446b);
                tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.f446b);
                if (C0689a.m1169a(tokenResult.apdid)) {
                    obj = 1;
                }
                if (obj != null || C0689a.m1169a(tokenResult.apdidToken) || C0689a.m1169a(tokenResult.clientKey)) {
                    initToken(0, new HashMap(), null);
                }
            } catch (Throwable th) {
            }
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        C0550a.m638a().mo7933a(i);
        String b = C0566h.m700b(this.f446b);
        String c = C0550a.m638a().mo7935c();
        if (C0689a.m1172b(b) && !C0689a.m1170a(b, c)) {
            C0559a.m660a(this.f446b);
            C0562d.m675a(this.f446b);
            C0565g.m693a(this.f446b);
            C0567i.m729h();
        }
        if (!C0689a.m1170a(b, c)) {
            C0566h.m702c(this.f446b, c);
        }
        Object a = C0689a.m1168a(map, "utdid", "");
        c = C0689a.m1168a(map, "tid", "");
        String a2 = C0689a.m1168a(map, AnalyticAttribute.USER_ID_ATTRIBUTE, "");
        if (C0689a.m1169a((String) a)) {
            a = UtdidWrapper.getUtdid(this.f446b);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", a);
        hashMap.put("tid", c);
        hashMap.put(AnalyticAttribute.USER_ID_ATTRIBUTE, a2);
        hashMap.put(AnalyticAttribute.APP_NAME_ATTRIBUTE, "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "8");
        C0569b.m734a().mo7954a(new Runnable() {
            public void run() {
                new C0549a(APSecuritySdk.this.f446b).mo7932a(hashMap);
                if (initResultListener != null) {
                    initResultListener.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }
}
