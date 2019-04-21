package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.threatmetrix.TrustDefender.C4532g.C4531o;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.threatmetrix.TrustDefender.ag */
class C4489ag {
    /* renamed from: a */
    private static volatile WebView f7379a = null;
    /* renamed from: b */
    private static final Lock f7380b = new ReentrantLock();
    /* renamed from: c */
    private static Context f7381c = null;
    /* renamed from: d */
    private static final String f7382d = C4549w.m8585a(C4489ag.class);

    private C4489ag() {
    }

    /* renamed from: a */
    public static WebView m8331a(Context context) {
        if (!C4531o.m8529a()) {
            return null;
        }
        if (f7381c != null && f7381c != context) {
            C4549w.m8587a(f7382d, "Mismatched context: Only application context should be used, and it should always be consistent between calls");
            return null;
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            return null;
        } else {
            if (f7379a == null) {
                try {
                    f7380b.lock();
                    if (f7379a == null) {
                        f7379a = new WebView(context);
                        f7381c = context;
                    }
                    f7380b.unlock();
                } catch (Throwable th) {
                    f7380b.unlock();
                }
            } else {
                C4549w.m8594c(f7382d, "Reusing webview");
            }
            return f7379a;
        }
    }

    /* renamed from: a */
    public static boolean m8332a() {
        try {
            f7380b.lock();
            boolean z = f7379a != null;
            f7380b.unlock();
            return z;
        } catch (Throwable th) {
            f7380b.unlock();
        }
    }

    /* renamed from: b */
    public static void m8333b() {
        if (C4531o.m8529a()) {
            try {
                f7380b.lock();
                if (f7379a != null) {
                    final WebView tempWebView = f7379a;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            tempWebView.removeAllViews();
                            tempWebView.destroy();
                        }
                    });
                }
                f7379a = null;
            } finally {
                f7380b.unlock();
            }
        }
    }
}
