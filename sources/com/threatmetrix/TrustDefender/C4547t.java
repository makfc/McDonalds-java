package com.threatmetrix.TrustDefender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.threatmetrix.TrustDefender.C4532g.C4518b;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import com.threatmetrix.TrustDefender.C4532g.C4531o;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.t */
class C4547t extends C4485at {
    /* renamed from: d */
    private static final Method f7822d;
    /* renamed from: e */
    private static final Method f7823e;
    /* renamed from: f */
    private static final Method f7824f;
    /* renamed from: g */
    private static final Method f7825g;
    /* renamed from: h */
    private static final Method f7826h;
    /* renamed from: i */
    private static final String f7827i = C4549w.m8585a(C4547t.class);
    /* renamed from: m */
    private static final TreeMap<Integer, String> f7828m;
    /* renamed from: a */
    private WebView f7829a;
    /* renamed from: b */
    private boolean f7830b;
    /* renamed from: c */
    private C2625u f7831c;
    /* renamed from: j */
    private boolean f7832j;
    /* renamed from: k */
    private WebSettings f7833k;
    /* renamed from: l */
    private final boolean f7834l;

    static {
        Method a = C4485at.m8325a(WebView.class, "evaluateJavascript", String.class, ValueCallback.class);
        f7822d = a;
        if (a == null && C4516a.f7584c >= 19) {
            C4549w.m8587a(f7827i, "Failed to find expected function: evaluateJavascript");
        }
        a = C4485at.m8325a(WebSettings.class, "getDefaultUserAgent", Context.class);
        f7823e = a;
        if (a == null && C4516a.f7584c >= 17) {
            C4549w.m8587a(f7827i, "Failed to find expected function: getDefaultUserAgent");
        }
        a = C4485at.m8325a(WebSettings.class, "setPluginState", PluginState.class);
        f7824f = a;
        if (a == null && (C4516a.f7584c >= 8 || C4516a.f7584c <= 18)) {
            C4549w.m8587a(f7827i, "Failed to find expected function: setPluginState");
        }
        a = C4485at.m8325a(WebView.class, "removeJavascriptInterface", String.class);
        f7825g = a;
        if (a == null && C4516a.f7584c >= 11) {
            C4549w.m8587a(f7827i, "Failed to find expected function: removeJavascriptInterface");
        }
        a = C4485at.m8325a(WebView.class, "addJavascriptInterface", Object.class, String.class);
        f7826h = a;
        if (a == null && C4516a.f7584c >= 17) {
            C4549w.m8587a(f7827i, "Failed to find expected function: addJavascriptInterface");
        }
        TreeMap treeMap = new TreeMap();
        f7828m = treeMap;
        treeMap.put(Integer.valueOf(C4517b.f7587b), "533.1");
        f7828m.put(Integer.valueOf(C4517b.f7588c), "533.1");
        f7828m.put(Integer.valueOf(C4517b.f7589d), "533.1");
        f7828m.put(Integer.valueOf(C4517b.f7590e), "533.1");
        f7828m.put(Integer.valueOf(C4517b.f7591f), "534.13");
        f7828m.put(Integer.valueOf(C4517b.f7592g), "534.30");
        f7828m.put(Integer.valueOf(C4517b.f7593h), "534.30");
        f7828m.put(Integer.valueOf(C4517b.f7594i), "534.30");
        f7828m.put(Integer.valueOf(C4517b.f7595j), "534.30");
        f7828m.put(Integer.valueOf(C4517b.f7596k), "534.30");
        f7828m.put(Integer.valueOf(C4517b.f7597l), "537.36");
        f7828m.put(Integer.valueOf(C4517b.f7598m), "537.36");
        f7828m.put(Integer.valueOf(C4517b.f7599n), "537.36");
        f7828m.put(Integer.valueOf(C4517b.f7600o), "537.36");
        f7828m.put(Integer.valueOf(23), "537.36");
    }

    /* renamed from: a */
    public static boolean m8577a() {
        return f7822d != null;
    }

    /* renamed from: b */
    public static boolean m8578b() {
        try {
            return C4516a.f7582a.startsWith("2.3");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public final String mo34257a(Context context) {
        if (C4531o.m8529a()) {
            String userAgent = (String) C4485at.m8323a(null, f7823e, context);
            if (userAgent != null && !userAgent.isEmpty()) {
                return userAgent;
            }
            if (this.f7834l && this.f7833k != null) {
                userAgent = this.f7833k.getUserAgentString();
            }
            if (!(userAgent == null || userAgent.isEmpty())) {
                return userAgent;
            }
        }
        return C4547t.m8579c();
    }

    /* renamed from: c */
    public static String m8579c() {
        String webkitVersion;
        C4549w.m8594c(f7827i, "Generating a browser string");
        if (f7828m.containsKey(Integer.valueOf(C4516a.f7584c))) {
            webkitVersion = (String) f7828m.get(Integer.valueOf(C4516a.f7584c));
        } else {
            webkitVersion = ((String) f7828m.lastEntry().getValue()) + "+";
        }
        String lang = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if (!country.isEmpty()) {
            lang = lang + "-" + country;
        }
        return "Mozilla/5.0 (Linux; U; Android " + C4516a.f7582a + "; " + lang.toLowerCase(Locale.US) + "; " + C4518b.f7611j + " Build/" + C4518b.f7612k + ") AppleWebKit/" + webkitVersion + " (KHTML, like Gecko) Version/4.0 Mobile Safari/" + webkitVersion + " " + C4506ar.f7506a;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public C4547t(Context context, C2625u jsInterface, boolean needWebView) {
        String str;
        this.f7829a = null;
        this.f7830b = false;
        this.f7831c = null;
        this.f7832j = false;
        this.f7832j = C4547t.m8578b();
        String str2 = f7827i;
        StringBuilder append = new StringBuilder("JSExecutor() Build: ").append(C4516a.f7582a).append(this.f7832j ? " busted js interface " : " normal js interface ");
        if (C4547t.m8577a()) {
            str = " has async interface ";
        } else {
            str = " has no async interface ";
        }
        C4549w.m8594c(str2, append.append(str).toString());
        this.f7831c = jsInterface;
        this.f7834l = needWebView;
        if (C4531o.m8529a() && needWebView) {
            boolean hasWebView = C4489ag.m8332a();
            this.f7830b = false;
            this.f7829a = C4489ag.m8331a(context);
            if (this.f7829a != null) {
                if (hasWebView && !this.f7830b) {
                    C4549w.m8592b(f7827i, "WebView re-used from previous instance. Using a short-lived TrustDefender object is not recommended. Better profiling performance will be achieved by re-using a long-lived TrustDefender instance");
                }
                C4549w.m8594c(f7827i, "Webview " + (this.f7830b ? "init'd" : "un-init'd"));
                if (this.f7831c == null) {
                    this.f7831c = new C2625u(null);
                }
                WebViewClient m_webViewClient = new WebViewClient();
                this.f7833k = this.f7829a.getSettings();
                this.f7833k.setJavaScriptEnabled(true);
                C4485at.m8323a((Object) this.f7833k, f7824f, PluginState.ON);
                this.f7829a.setVisibility(4);
                if (!this.f7832j) {
                    C4485at.m8323a((Object) this.f7829a, f7825g, "androidJSInterface");
                }
                this.f7829a.setWebViewClient(m_webViewClient);
                if (C4547t.m8577a()) {
                    if (this.f7831c.f6077a == null) {
                        C4549w.m8587a(f7827i, "alternate JS interface but no global latch");
                    }
                    C4549w.m8594c(f7827i, "JSExecutor() alternate JS interface detected");
                } else if (this.f7832j) {
                    if (this.f7831c.f6077a == null) {
                        C4549w.m8587a(f7827i, "broken JS interface but no global latch");
                    }
                    C4549w.m8594c(f7827i, "JSExecutor() Broken JS interface detected, using workaround");
                    this.f7829a.setWebChromeClient(new C4507as(this.f7831c));
                } else {
                    C4485at.m8323a((Object) this.f7829a, f7826h, this.f7831c, "androidJSInterface");
                }
            }
        }
    }

    /* renamed from: d */
    public final void mo34260d() throws InterruptedException {
        if (C4531o.m8529a()) {
            C4549w.m8594c(f7827i, "init() - init'd = " + this.f7830b);
            if (!this.f7830b) {
                if (this.f7829a == null) {
                    C4549w.m8594c(f7827i, "init() - No web view, nothing needs to be done");
                    this.f7830b = true;
                    return;
                }
                String html;
                C4549w.m8594c(f7827i, "init() loading bogus page");
                CountDownLatch latch = null;
                if (this.f7832j || C4547t.m8577a()) {
                    html = "<html><head></head><body></body></html>";
                } else {
                    latch = new CountDownLatch(1);
                    C4549w.m8594c(f7827i, "Creating latch: " + latch.hashCode() + " with count: " + latch.getCount());
                    html = "<html><head></head><body onLoad='javascript:window.androidJSInterface.getString(1)'></body></html>";
                    this.f7831c.mo23199a(latch);
                    this.f7831c.f6078b = null;
                }
                if (!Thread.currentThread().isInterrupted()) {
                    this.f7829a.loadData(html, "text/html", null);
                    if (this.f7832j || latch == null || C4547t.m8577a()) {
                        this.f7830b = true;
                        return;
                    }
                    C4549w.m8594c(f7827i, "waiting for latch: " + latch.hashCode() + " with count: " + latch.getCount());
                    if (latch.await(5, TimeUnit.SECONDS)) {
                        this.f7830b = true;
                        C4549w.m8594c(f7827i, "in init() count = " + latch.getCount());
                        if (this.f7831c.f6078b == null) {
                            C4549w.m8594c(f7827i, "init() After latch: got null");
                            return;
                        } else {
                            C4549w.m8594c(f7827i, "init() After latch: got " + this.f7831c.f6078b);
                            return;
                        }
                    }
                    C4549w.m8587a(f7827i, "timed out waiting for javascript");
                }
            }
        }
    }

    /* renamed from: a */
    public final String mo34258a(String js) throws InterruptedException {
        if (!this.f7830b) {
            return null;
        }
        if (Thread.currentThread().isInterrupted()) {
            return "";
        }
        String fullJS;
        CountDownLatch latch = null;
        if (!(this.f7832j || C4547t.m8577a())) {
            latch = new CountDownLatch(1);
            this.f7831c.mo23199a(latch);
        }
        if (C4547t.m8577a()) {
            fullJS = "javascript:(function(){try{return " + js + " + \"\";}catch(js_eval_err){return '';}})();";
        } else if (this.f7832j) {
            fullJS = "javascript:alert((function(){try{return " + js + " + \"\";}catch(js_eval_err){return '';}})());";
        } else {
            fullJS = "javascript:window.androidJSInterface.getString((function(){try{return " + js + " + \"\";}catch(js_eval_err){return '';}})());";
        }
        C4549w.m8594c(f7827i, "getJSResult() attempting to execute: " + fullJS);
        this.f7831c.f6078b = null;
        boolean invokeFailed = false;
        if (C4547t.m8577a()) {
            try {
                f7822d.invoke(this.f7829a, new Object[]{fullJS, this.f7831c});
            } catch (IllegalAccessException e) {
                C4549w.m8588a(f7827i, "getJSResult() invoke failed: ", e);
                invokeFailed = true;
            } catch (IllegalArgumentException e2) {
                C4549w.m8588a(f7827i, "getJSResult() invoke failed: ", e2);
                invokeFailed = true;
            } catch (InvocationTargetException e22) {
                C4549w.m8588a(f7827i, "getJSResult() invoke failed: ", e22);
                invokeFailed = true;
            } catch (RuntimeException e222) {
                C4549w.m8588a(f7827i, "getJSResult() invoke failed: ", e222);
                invokeFailed = true;
            }
        } else {
            try {
                this.f7829a.loadUrl(fullJS);
            } catch (RuntimeException e2222) {
                C4549w.m8588a(f7827i, "getJSResult() invoke failed: ", e2222);
                invokeFailed = true;
            }
        }
        if (invokeFailed) {
            if (this.f7831c.f6077a == null) {
                return null;
            }
            C4549w.m8594c(f7827i, "getJSResult countdown for latch: " + this.f7831c.f6077a.hashCode() + " with count: " + this.f7831c.f6077a.getCount());
            this.f7831c.f6077a.countDown();
            return null;
        } else if (this.f7832j || C4547t.m8577a()) {
            return null;
        } else {
            if (latch != null) {
                C4549w.m8594c(f7827i, "getJSResult waiting for latch: " + latch.hashCode() + " with count: " + latch.getCount());
                if (!latch.await(5, TimeUnit.SECONDS)) {
                    C4549w.m8594c(f7827i, "getJSResult timeout waiting for latch: " + latch.hashCode() + " with count: " + latch.getCount());
                }
            } else {
                C4549w.m8587a(f7827i, "latch == null");
            }
            if (this.f7831c.f6078b == null) {
                C4549w.m8594c(f7827i, "getJSResult() After latch: got null");
            } else {
                C4549w.m8594c(f7827i, "getJSResult() After latch: got " + this.f7831c.f6078b);
            }
            return this.f7831c.f6078b;
        }
    }

    /* renamed from: a */
    public final boolean mo34259a(boolean needWebview) {
        return (needWebview == this.f7834l && this.f7830b) ? false : true;
    }
}
