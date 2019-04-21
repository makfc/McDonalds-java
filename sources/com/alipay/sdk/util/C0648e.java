package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.C0587i;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.data.C0615a;
import com.alipay.sdk.util.C0657m.C0656a;
import java.util.List;

/* renamed from: com.alipay.sdk.util.e */
public class C0648e {
    /* renamed from: b */
    private Activity f652b;
    /* renamed from: c */
    private IAlixPay f653c;
    /* renamed from: d */
    private final Object f654d = IAlixPay.class;
    /* renamed from: e */
    private boolean f655e;
    /* renamed from: f */
    private C0577a f656f;
    /* renamed from: g */
    private ServiceConnection f657g = new C0649f(this);
    /* renamed from: h */
    private IRemoteServiceCallback f658h = new C0650g(this);

    /* renamed from: com.alipay.sdk.util.e$a */
    public interface C0577a {
        /* renamed from: a */
        void mo7992a();

        /* renamed from: b */
        void mo7993b();
    }

    public C0648e(Activity activity, C0577a c0577a) {
        this.f652b = activity;
        this.f656f = c0577a;
    }

    /* renamed from: a */
    public String mo8110a(String str) {
        String a;
        Throwable th;
        String str2 = "";
        try {
            List d = C0615a.m869e().mo8055d();
            if (!C0615a.m869e().f573l || d == null) {
                d = C0587i.f495a;
            }
            C0656a a2 = C0657m.m1049a(this.f652b, d);
            if (a2 == null || a2.mo8114a() || a2.mo8115b()) {
                return "failed";
            }
            if (C0657m.m1058a(a2.f662a)) {
                return "failed";
            }
            if (a2.f662a == null || "com.eg.android.AlipayGphone".equals(a2.f662a.packageName)) {
                a = C0657m.m1051a();
            } else {
                a = a2.f662a.packageName;
            }
            try {
                m1026a(a2);
            } catch (Throwable th2) {
                th = th2;
                C0590a.m802a("biz", "CheckClientSignEx", th);
                return m1025a(str, a);
            }
            return m1025a(str, a);
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = str2;
            th = th4;
        }
    }

    /* renamed from: a */
    private void m1026a(C0656a c0656a) throws InterruptedException {
        if (c0656a != null) {
            PackageInfo packageInfo = c0656a.f662a;
            if (packageInfo != null) {
                String str = packageInfo.packageName;
                Intent intent = new Intent();
                intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
                try {
                    this.f652b.startActivity(intent);
                } catch (Throwable th) {
                    C0590a.m802a("biz", "StartLaunchAppTransEx", th);
                }
                Thread.sleep(200);
            }
        }
    }

    /* renamed from: a */
    private String m1025a(String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(str2);
        intent.setAction(C0657m.m1054a(str2));
        String a = C0657m.m1053a(this.f652b, str2);
        try {
            String a2;
            if (this.f652b.getApplicationContext().bindService(intent, this.f657g, 1)) {
                synchronized (this.f654d) {
                    if (this.f653c == null) {
                        try {
                            this.f654d.wait((long) C0615a.m869e().mo8051a());
                        } catch (InterruptedException e) {
                            C0590a.m802a("biz", "BindWaitTimeoutEx", e);
                        }
                    }
                }
                try {
                    if (this.f653c == null) {
                        a2 = C0657m.m1053a(this.f652b, str2);
                        String i = C0657m.m1082i(this.f652b);
                        C0646c.m1017b("", "说明：当前发生了绑定支付宝服务异常的情况。为了分析哪些场景将导致绑定失败，支付宝需要采集当前运行的 App 名称进行安全分析。");
                        C0590a.m801a("biz", "ClientBindFailed", a + "|" + a2 + "|" + i);
                        a2 = "failed";
                        try {
                            this.f653c.unregisterCallback(this.f658h);
                        } catch (Throwable th) {
                            C0646c.m1016a(th);
                        }
                        try {
                            this.f652b.getApplicationContext().unbindService(this.f657g);
                        } catch (Throwable th2) {
                            C0646c.m1016a(th2);
                        }
                        this.f656f = null;
                        this.f658h = null;
                        this.f657g = null;
                        this.f653c = null;
                        if (!this.f655e || this.f652b == null) {
                            return a2;
                        }
                        this.f652b.setRequestedOrientation(0);
                        this.f655e = false;
                        return a2;
                    }
                    if (this.f656f != null) {
                        this.f656f.mo7992a();
                    }
                    if (this.f652b.getRequestedOrientation() == 0) {
                        this.f652b.setRequestedOrientation(1);
                        this.f655e = true;
                    }
                    this.f653c.registerCallback(this.f658h);
                    a2 = this.f653c.Pay(str);
                    try {
                        this.f653c.unregisterCallback(this.f658h);
                    } catch (Throwable th22) {
                        C0646c.m1016a(th22);
                    }
                    try {
                        this.f652b.getApplicationContext().unbindService(this.f657g);
                    } catch (Throwable th222) {
                        C0646c.m1016a(th222);
                    }
                    this.f656f = null;
                    this.f658h = null;
                    this.f657g = null;
                    this.f653c = null;
                    if (!this.f655e || this.f652b == null) {
                        return a2;
                    }
                    this.f652b.setRequestedOrientation(0);
                    this.f655e = false;
                    return a2;
                } catch (Throwable th2222) {
                    C0646c.m1016a(th2222);
                }
            } else {
                throw new Throwable("bindService fail");
            }
            this.f652b.getApplicationContext().unbindService(this.f657g);
            this.f656f = null;
            this.f658h = null;
            this.f657g = null;
            this.f653c = null;
            if (!this.f655e && this.f652b != null) {
                this.f652b.setRequestedOrientation(0);
                this.f655e = false;
                return a2;
            }
            this.f656f = null;
            this.f658h = null;
            this.f657g = null;
            this.f653c = null;
            return !this.f655e ? a2 : a2;
        } catch (Throwable e2) {
            C0590a.m802a("biz", "ClientBindServiceFailed", e2);
            return "failed";
        }
    }

    /* renamed from: a */
    public void mo8111a() {
        this.f652b = null;
    }
}
