package com.alipay.sdk.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.alipay.sdk.app.C0587i;
import com.alipay.sdk.authjs.C0606a;
import com.alipay.sdk.authjs.C0608d;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0657m;
import com.alipay.sdk.util.C0657m.C0656a;
import com.alipay.sdk.widget.C0664a;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import org.json.JSONException;

@Instrumented
@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public class AuthActivity extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;
    /* renamed from: c */
    private WebView f528c;
    /* renamed from: d */
    private String f529d;
    /* renamed from: e */
    private C0664a f530e;
    /* renamed from: f */
    private Handler f531f;
    /* renamed from: g */
    private boolean f532g;
    /* renamed from: h */
    private boolean f533h;

    /* renamed from: com.alipay.sdk.auth.AuthActivity$a */
    private static final class C0593a implements Runnable {
        /* renamed from: a */
        private final WeakReference<AuthActivity> f525a;

        /* synthetic */ C0593a(AuthActivity authActivity, C0596a c0596a) {
            this(authActivity);
        }

        private C0593a(AuthActivity authActivity) {
            this.f525a = new WeakReference(authActivity);
        }

        public void run() {
            AuthActivity authActivity = (AuthActivity) this.f525a.get();
            if (authActivity != null) {
                authActivity.m828b();
            }
        }
    }

    /* renamed from: com.alipay.sdk.auth.AuthActivity$b */
    private class C0594b extends WebChromeClient {
        private C0594b() {
        }

        /* synthetic */ C0594b(AuthActivity authActivity, C0596a c0596a) {
            this();
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (TextUtils.isEmpty(message)) {
                return super.onConsoleMessage(consoleMessage);
            }
            String str = null;
            if (message.startsWith("h5container.message: ")) {
                str = message.replaceFirst("h5container.message: ", "");
            }
            if (TextUtils.isEmpty(str)) {
                return super.onConsoleMessage(consoleMessage);
            }
            AuthActivity.this.m830b(str);
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* renamed from: com.alipay.sdk.auth.AuthActivity$c */
    private class C0595c extends WebViewClient {
        private C0595c() {
        }

        /* synthetic */ C0595c(AuthActivity authActivity, C0596a c0596a) {
            this();
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            AuthActivity.this.f533h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (AuthActivity.this.f532g) {
                sslErrorHandler.proceed();
                AuthActivity.this.f532g = false;
                return;
            }
            AuthActivity.this.runOnUiThread(new C0600d(this, sslErrorHandler));
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) || str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
                try {
                    C0656a a = C0657m.m1049a(AuthActivity.this, C0587i.f495a);
                    if (a == null || a.mo8114a() || a.mo8115b()) {
                        return true;
                    }
                    if (str.startsWith("intent://platformapi/startapp")) {
                        str = str.replaceFirst("intent://platformapi/startapp?", "alipays://platformapi/startApp?");
                    }
                    AuthActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (Throwable th) {
                    return true;
                }
            } else if (!AuthActivity.this.m826a(str)) {
                return super.shouldOverrideUrlLoading(webView, str);
            } else {
                webView.stopLoading();
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthActivity.this.m820a();
            AuthActivity.this.f531f.postDelayed(new C0593a(AuthActivity.this, null), 30000);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            AuthActivity.this.m828b();
            AuthActivity.this.f531f.removeCallbacksAndMessages(null);
        }
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
        TraceMachine.startTracing("AuthActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "AuthActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "AuthActivity#onCreate", null);
            }
        }
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                TraceMachine.exitMethod();
                return;
            }
            try {
                this.f529d = extras.getString("redirectUri");
                String string = extras.getString("params");
                if (C0657m.m1079f(string)) {
                    Method method;
                    super.requestWindowFeature(1);
                    this.f531f = new Handler(getMainLooper());
                    LinearLayout linearLayout = new LinearLayout(this);
                    LayoutParams layoutParams = new LayoutParams(-1, -1);
                    linearLayout.setOrientation(1);
                    setContentView(linearLayout, layoutParams);
                    this.f528c = new WebView(this);
                    layoutParams.weight = 1.0f;
                    this.f528c.setVisibility(0);
                    linearLayout.addView(this.f528c, layoutParams);
                    WebSettings settings = this.f528c.getSettings();
                    settings.setUserAgentString(settings.getUserAgentString() + C0657m.m1068c(getApplicationContext()));
                    settings.setRenderPriority(RenderPriority.HIGH);
                    settings.setSupportMultipleWindows(true);
                    settings.setJavaScriptEnabled(true);
                    settings.setSavePassword(false);
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);
                    settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
                    settings.setAllowFileAccess(false);
                    settings.setAllowFileAccessFromFileURLs(false);
                    settings.setAllowUniversalAccessFromFileURLs(false);
                    settings.setAllowContentAccess(false);
                    settings.setTextSize(TextSize.NORMAL);
                    this.f528c.setVerticalScrollbarOverlay(true);
                    this.f528c.setWebViewClient(new C0595c(this, null));
                    this.f528c.setWebChromeClient(new C0594b(this, null));
                    this.f528c.setDownloadListener(new C0596a(this));
                    this.f528c.loadUrl(string);
                    if (VERSION.SDK_INT >= 7) {
                        try {
                            method = this.f528c.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                            if (method != null) {
                                method.invoke(this.f528c.getSettings(), new Object[]{Boolean.valueOf(true)});
                            }
                        } catch (Exception e2) {
                        }
                    }
                    try {
                        this.f528c.removeJavascriptInterface("searchBoxJavaBridge_");
                        this.f528c.removeJavascriptInterface("accessibility");
                        this.f528c.removeJavascriptInterface("accessibilityTraversal");
                    } catch (Throwable th) {
                    }
                    if (VERSION.SDK_INT >= 19) {
                        this.f528c.getSettings().setCacheMode(1);
                    }
                    TraceMachine.exitMethod();
                    return;
                }
                finish();
                TraceMachine.exitMethod();
            } catch (Exception e3) {
                finish();
                TraceMachine.exitMethod();
            }
        } catch (Exception e4) {
            finish();
            TraceMachine.exitMethod();
        }
    }

    public void onBackPressed() {
        if (!this.f528c.canGoBack()) {
            C0603g.m841a(this, this.f529d + "?resultCode=150");
            finish();
        } else if (this.f533h) {
            C0603g.m841a(this, this.f529d + "?resultCode=150");
            finish();
        }
    }

    /* renamed from: a */
    private boolean m826a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.f529d)) {
                str = str + "?resultCode=150";
            }
            C0603g.m841a(this, str);
        }
        finish();
        return true;
    }

    /* renamed from: b */
    private void m830b(String str) {
        new C0608d(getApplicationContext(), new C0598b(this)).mo8047a(str);
    }

    /* renamed from: a */
    private void m822a(C0606a c0606a) {
        if (this.f528c != null && c0606a != null) {
            try {
                String str = "AlipayJSBridge._invokeJS(%s)";
                runOnUiThread(new C0599c(this, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{c0606a.mo8045g()})));
            } catch (JSONException e) {
                C0646c.m1015a("msp", e);
            }
        }
    }

    /* renamed from: a */
    private void m820a() {
        try {
            if (this.f530e == null) {
                this.f530e = new C0664a(this, "正在加载");
            }
            this.f530e.mo8141b();
        } catch (Exception e) {
            this.f530e = null;
        }
    }

    /* renamed from: b */
    private void m828b() {
        if (this.f530e != null) {
            this.f530e.mo8142c();
        }
        this.f530e = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f528c != null) {
            this.f528c.removeAllViews();
            try {
                this.f528c.destroy();
            } catch (Throwable th) {
            }
            this.f528c = null;
        }
    }
}
