package com.baidu.android.pushservice.richmedia;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class MediaViewActivity extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;
    /* renamed from: a */
    public WebView f5330a;
    /* renamed from: b */
    private RelativeLayout f5331b;
    /* renamed from: c */
    private WebChromeClient f5332c = new C15221();
    /* renamed from: d */
    private WebViewClient f5333d = new C15232();

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaViewActivity$1 */
    class C15221 extends WebChromeClient {
        C15221() {
        }

        public void onHideCustomView() {
        }

        public void onProgressChanged(WebView webView, int i) {
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        }
    }

    /* renamed from: com.baidu.android.pushservice.richmedia.MediaViewActivity$2 */
    class C15232 extends WebViewClient {
        C15232() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Intent intent;
            if (str.startsWith("tel:")) {
                try {
                    intent = new Intent("android.intent.action.DIAL");
                    intent.setData(Uri.parse(str));
                    MediaViewActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    C1425a.m6444e("MediaViewActivity", "Error dialing " + str + ": " + e.toString());
                }
            } else if (str.startsWith("geo:")) {
                try {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    MediaViewActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e2) {
                    C1425a.m6444e("MediaViewActivity", "Error showing map " + str + ": " + e2.toString());
                }
            } else if (str.startsWith("mailto:")) {
                try {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    MediaViewActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e22) {
                    C1425a.m6444e("MediaViewActivity", "Error sending email " + str + ": " + e22.toString());
                }
            } else if (str.startsWith("sms:")) {
                try {
                    String substring;
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    int indexOf = str.indexOf(63);
                    if (indexOf == -1) {
                        substring = str.substring(4);
                    } else {
                        substring = str.substring(4, indexOf);
                        String query = Uri.parse(str).getQuery();
                        if (query != null && query.startsWith("body=")) {
                            intent2.putExtra("sms_body", query.substring(5));
                        }
                    }
                    intent2.setData(Uri.parse("sms:" + substring));
                    intent2.putExtra("address", substring);
                    intent2.setType("vnd.android-dir/mms-sms");
                    MediaViewActivity.this.startActivity(intent2);
                } catch (ActivityNotFoundException e222) {
                    C1425a.m6444e("MediaViewActivity", "Error sending sms " + str + ":" + e222.toString());
                }
            }
            if (VERSION.SDK_INT > 17) {
                try {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    MediaViewActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e2222) {
                    C1425a.m6439a("MediaViewActivity", "Error loading url " + str, e2222);
                }
            } else {
                webView.loadUrl(str);
            }
            return true;
        }
    }

    @TargetApi(11)
    /* renamed from: a */
    private void m6865a() {
        this.f5330a.removeJavascriptInterface("searchBoxJavaBridge_");
        this.f5330a.removeJavascriptInterface("accessibility");
        this.f5330a.removeJavascriptInterface("accessibilityTraversal");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MediaViewActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MediaViewActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MediaViewActivity#onCreate", null);
            }
        }
        super.onCreate(bundle);
        Intent intent = getIntent();
        getWindow().requestFeature(1);
        LayoutParams layoutParams = new LayoutParams(-1, -1, 0.0f);
        this.f5331b = new RelativeLayout(this);
        this.f5331b.setLayoutParams(layoutParams);
        this.f5331b.setGravity(1);
        this.f5330a = new WebView(this);
        if (VERSION.SDK_INT >= 11) {
            m6865a();
        }
        this.f5330a.requestFocusFromTouch();
        this.f5330a.setLongClickable(true);
        WebSettings settings = this.f5330a.getSettings();
        settings.setCacheMode(1);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLightTouchEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setSavePassword(false);
        this.f5330a.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f5330a.setWebChromeClient(this.f5332c);
        this.f5330a.setWebViewClient(this.f5333d);
        this.f5331b.addView(this.f5330a);
        setContentView(this.f5331b);
        if (this.f5331b == null || this.f5330a == null) {
            C1425a.m6444e("MediaViewActivity", "Set up Layout error.");
            finish();
        }
        C1425a.m6442c("MediaViewActivity", "uri=" + intent.getData().toString());
        this.f5330a.loadUrl(intent.getData().toString());
        TraceMachine.exitMethod();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        C1425a.m6442c("MediaViewActivity", "uri=" + intent.getData().toString());
        this.f5330a.loadUrl(intent.getData().toString());
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
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
}
