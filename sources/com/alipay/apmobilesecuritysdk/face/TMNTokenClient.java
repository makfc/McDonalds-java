package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.apmobilesecuritysdk.p013a.C0549a;
import com.alipay.apmobilesecuritysdk.p018f.C0569b;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;

public class TMNTokenClient {
    /* renamed from: a */
    private static TMNTokenClient f451a = null;
    /* renamed from: b */
    private Context f452b = null;

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
        }
        this.f452b = context;
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f451a == null) {
            synchronized (TMNTokenClient.class) {
                if (f451a == null) {
                    f451a = new TMNTokenClient(context);
                }
            }
        }
        return f451a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (C0689a.m1169a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (C0689a.m1169a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", UtdidWrapper.getUtdid(this.f452b));
        hashMap.put("tid", "");
        hashMap.put(AnalyticAttribute.USER_ID_ATTRIBUTE, "");
        hashMap.put(AnalyticAttribute.APP_NAME_ATTRIBUTE, str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put("sessionId", str3);
        hashMap.put("rpcVersion", "8");
        C0569b.m734a().mo7954a(new Runnable() {
            public void run() {
                int a = new C0549a(TMNTokenClient.this.f452b).mo7932a(hashMap);
                if (initResultListener != null) {
                    if (a == 0) {
                        initResultListener.onResult(C0549a.m632a(TMNTokenClient.this.f452b, str), 0);
                        return;
                    }
                    initResultListener.onResult("", a);
                }
            }
        });
    }
}
