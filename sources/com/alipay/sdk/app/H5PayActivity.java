package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0657m;
import com.alipay.sdk.widget.C0670g;
import com.alipay.sdk.widget.C0671h;
import com.alipay.sdk.widget.C0674j;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class H5PayActivity extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;
    /* renamed from: a */
    private C0670g f456a;
    /* renamed from: b */
    private String f457b;
    /* renamed from: c */
    private String f458c;
    /* renamed from: d */
    private String f459d;
    /* renamed from: e */
    private String f460e;
    /* renamed from: f */
    private boolean f461f;
    /* renamed from: g */
    private String f462g;

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
        TraceMachine.startTracing("H5PayActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "H5PayActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "H5PayActivity#onCreate", null);
            }
        }
        m747b();
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            this.f457b = extras.getString(NativeProtocol.IMAGE_URL_KEY, null);
            if (C0657m.m1079f(this.f457b)) {
                this.f459d = extras.getString("cookie", null);
                this.f458c = extras.getString("method", null);
                this.f460e = extras.getString(PushConstants.TITLE_KEY, null);
                this.f462g = extras.getString("version", "v1");
                this.f461f = extras.getBoolean("backisexit", false);
                try {
                    if ("v2".equals(this.f462g)) {
                        C0674j c0674j = new C0674j(this);
                        setContentView(c0674j);
                        c0674j.mo8155a(this.f460e, this.f458c, this.f461f);
                        c0674j.mo8148a(this.f457b);
                        this.f456a = c0674j;
                    } else {
                        this.f456a = new C0671h(this);
                        setContentView(this.f456a);
                        this.f456a.mo8149a(this.f457b, this.f459d);
                        this.f456a.mo8148a(this.f457b);
                    }
                    TraceMachine.exitMethod();
                    return;
                } catch (Throwable th) {
                    C0590a.m802a("biz", "GetInstalledAppEx", th);
                    finish();
                    TraceMachine.exitMethod();
                    return;
                }
            }
            finish();
            TraceMachine.exitMethod();
        } catch (Exception e2) {
            finish();
            TraceMachine.exitMethod();
        }
    }

    /* renamed from: b */
    private void m747b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            C0646c.m1016a(th);
        }
    }

    public void onBackPressed() {
        if (this.f456a instanceof C0671h) {
            this.f456a.mo8150b();
            return;
        }
        if (!this.f456a.mo8150b()) {
            super.onBackPressed();
        }
        C0588j.m790a(C0588j.m793c());
        finish();
    }

    public void finish() {
        mo7969a();
        super.finish();
    }

    /* renamed from: a */
    public void mo7969a() {
        Object obj = PayTask.f469a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f456a.mo8147a();
    }
}
