package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.util.C0646c;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Instrumented
public final class PayResultActivity extends Activity implements TraceFieldInterface {
    /* renamed from: b */
    public static final HashMap<String, Object> f465b = new HashMap();
    public Trace _nr_trace;

    /* renamed from: com.alipay.sdk.app.PayResultActivity$a */
    public static final class C0575a {
        /* renamed from: a */
        public static volatile String f463a;
        /* renamed from: b */
        public static volatile String f464b;
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("PayResultActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "PayResultActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "PayResultActivity#onCreate", null);
            }
        }
        super.onCreate(bundle);
        Intent intent = getIntent();
        if ((!TextUtils.isEmpty(intent.getStringExtra("orderSuffix")) ? 1 : 0) != 0) {
            C0575a.f463a = intent.getStringExtra("phonecashier.pay.hash");
            m751a(this, C0575a.f463a, intent.getStringExtra("orderSuffix"), intent.getStringExtra("externalPkgName"));
            m750a((Activity) this, 300);
            TraceMachine.exitMethod();
            return;
        }
        String stringExtra = intent.getStringExtra("phonecashier.pay.result");
        int intExtra = intent.getIntExtra("phonecashier.pay.resultOrderHash", 0);
        if (intExtra == 0 || !TextUtils.equals(C0575a.f463a, String.valueOf(intExtra))) {
            C0590a.m801a("biz", "SchemePayWrongHashEx", "Expected " + C0575a.f463a + ", got " + intExtra);
            m752a(C0575a.f463a);
            m750a((Activity) this, 300);
            TraceMachine.exitMethod();
            return;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            m752a(C0575a.f463a);
        } else {
            m753a(stringExtra, C0575a.f463a);
        }
        C0575a.f463a = "";
        m750a((Activity) this, 300);
        TraceMachine.exitMethod();
    }

    /* renamed from: a */
    private static void m751a(Activity activity, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            Intent intent = new Intent();
            try {
                intent.setPackage("hk.alipay.wallet");
                intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, Utf8Charset.NAME) + "&orderSuffix=" + URLEncoder.encode(str2, Utf8Charset.NAME) + "&packageName=" + URLEncoder.encode(str3, Utf8Charset.NAME) + "&externalPkgName=" + URLEncoder.encode(str3, Utf8Charset.NAME)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                C0646c.m1017b("msp", "PayTask:payReuslt: UnsupportedEncodingException:" + e);
            }
            if (activity != null) {
                activity.startActivity(intent);
            }
        }
    }

    /* renamed from: a */
    private static void m752a(String str) {
        C0575a.f464b = C0588j.m793c();
        m754a(f465b, str);
    }

    /* renamed from: a */
    private static void m753a(String str, String str2) {
        C0575a.f464b = str;
        m754a(f465b, str2);
    }

    /* renamed from: a */
    private static void m750a(Activity activity, int i) {
        new Handler().postDelayed(new C0584f(activity), (long) i);
    }

    /* renamed from: a */
    private static boolean m754a(HashMap<String, Object> hashMap, String str) {
        if (hashMap == null || str == null) {
            return false;
        }
        Object obj = hashMap.get(str);
        if (obj == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}
