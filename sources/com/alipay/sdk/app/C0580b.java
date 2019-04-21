package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.util.C0657m;
import com.alipay.sdk.widget.C0664a;
import java.lang.ref.WeakReference;

/* renamed from: com.alipay.sdk.app.b */
public class C0580b extends WebViewClient {
    /* renamed from: a */
    private Activity f480a;
    /* renamed from: b */
    private boolean f481b;
    /* renamed from: c */
    private Handler f482c = new Handler(this.f480a.getMainLooper());
    /* renamed from: d */
    private C0664a f483d;
    /* renamed from: e */
    private boolean f484e;

    /* renamed from: com.alipay.sdk.app.b$a */
    private static final class C0579a implements Runnable {
        /* renamed from: a */
        private final WeakReference<C0580b> f479a;

        C0579a(C0580b c0580b) {
            this.f479a = new WeakReference(c0580b);
        }

        public void run() {
            C0580b c0580b = (C0580b) this.f479a.get();
            if (c0580b != null) {
                c0580b.m780d();
            }
        }
    }

    public C0580b(Activity activity) {
        this.f480a = activity;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f484e = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        C0590a.m801a("net", "SSLError", "证书错误");
        if (this.f481b) {
            sslErrorHandler.proceed();
            this.f481b = false;
            return;
        }
        this.f480a.runOnUiThread(new C0581c(this, sslErrorHandler));
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return C0657m.m1059a(webView, str, this.f480a);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Activity activity = this.f480a;
        if (!(this.f482c == null || activity == null || activity.isFinishing())) {
            m779c();
            this.f482c.postDelayed(new C0579a(this), 30000);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        Activity activity = this.f480a;
        if (this.f482c != null && activity != null && !activity.isFinishing()) {
            m780d();
            this.f482c.removeCallbacksAndMessages(null);
        }
    }

    /* renamed from: c */
    private void m779c() {
        if (this.f483d == null) {
            this.f483d = new C0664a(this.f480a, "正在加载");
            this.f483d.mo8140a(true);
        }
        this.f483d.mo8141b();
    }

    /* renamed from: d */
    private void m780d() {
        if (this.f483d != null) {
            this.f483d.mo8142c();
        }
        this.f483d = null;
    }

    /* renamed from: a */
    public void mo7995a() {
        this.f482c = null;
        this.f480a = null;
    }

    /* renamed from: b */
    public boolean mo7996b() {
        return this.f484e;
    }
}
