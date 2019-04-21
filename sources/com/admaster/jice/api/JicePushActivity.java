package com.admaster.jice.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.p000v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.admaster.jice.p004a.JiceViewVisitor;
import com.admaster.jice.p005b.C0476e;
import com.admaster.jice.p005b.C0477f;
import com.admaster.jice.p005b.JicePushConfig;
import com.admaster.jice.p007d.ManagerUtils;
import com.facebook.widget.WebDialog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class JicePushActivity extends Activity implements TraceFieldInterface {
    /* renamed from: d */
    private static JiceViewVisitor f100d = null;
    public Trace _nr_trace;
    /* renamed from: a */
    OnClickListener f101a = new C0473a(this);
    /* renamed from: b */
    private JicePushConfig f102b = null;
    /* renamed from: c */
    private C0476e f103c = null;
    /* renamed from: e */
    private C0477f f104e = null;
    /* renamed from: f */
    private ImageView f105f = null;
    /* renamed from: g */
    private WebView f106g = null;

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
        TraceMachine.startTracing("JicePushActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "JicePushActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "JicePushActivity#onCreate", null);
            }
        }
        super.onCreate(bundle);
        setTheme(WebDialog.DEFAULT_THEME);
        this.f102b = (JicePushConfig) getIntent().getSerializableExtra("pushviewconfig");
        m144a("JicePushActivity on init:" + this.f102b + "   jiceViewCallback:" + f100d);
        if (this.f102b == null) {
            m144a("jicePushActivity Constructor found JicePushConfig is null.");
            finish();
            TraceMachine.exitMethod();
            return;
        }
        int c = ManagerUtils.m237c(this);
        if (c == 1) {
            this.f103c = this.f102b.getVertical();
        } else if (c == 2) {
            this.f103c = this.f102b.getHorizontal();
        }
        if (this.f103c == null) {
            m144a("jicePushActivity constructor found JicePushView current Screen Materical is null!");
            finish();
            TraceMachine.exitMethod();
            return;
        }
        this.f104e = this.f103c.getType();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(Color.argb(120, 0, 0, 0));
        setContentView(relativeLayout);
        relativeLayout.setOnClickListener(this.f101a);
        if (this.f104e == C0477f.IMAGE) {
            Bitmap cacheBitmap;
            try {
                cacheBitmap = this.f103c.getCacheBitmap();
            } catch (Exception e2) {
                e2.printStackTrace();
                cacheBitmap = null;
            }
            if (cacheBitmap == null) {
                m144a("the orientation:" + ManagerUtils.m231a(c) + "  image resource is null!");
                finish();
                TraceMachine.exitMethod();
                return;
            }
            m141a(relativeLayout, cacheBitmap);
        } else {
            String url = this.f103c.getUrl();
            if (TextUtils.isEmpty(url)) {
                m144a("the orientation:" + ManagerUtils.m231a(c) + "  image resource is null!");
                finish();
                TraceMachine.exitMethod();
                return;
            }
            m142a(relativeLayout, url);
        }
        if (f100d != null) {
            f100d.mo7618b(String.valueOf(this.f102b.getPushId()));
        }
        TraceMachine.exitMethod();
    }

    /* renamed from: a */
    public static void m143a(JiceViewVisitor jiceViewVisitor) {
        f100d = jiceViewVisitor;
    }

    /* renamed from: a */
    private void m141a(RelativeLayout relativeLayout, Bitmap bitmap) {
        this.f105f = new ImageView(this);
        m139a(bitmap);
        relativeLayout.addView(this.f105f);
        this.f105f.setOnClickListener(this.f101a);
    }

    /* renamed from: a */
    private void m139a(Bitmap bitmap) {
        float f;
        float f2;
        int i;
        int i2;
        float f3 = getResources().getDisplayMetrics().density;
        float f4 = (float) getResources().getDisplayMetrics().widthPixels;
        float f5 = (float) getResources().getDisplayMetrics().heightPixels;
        int c = ManagerUtils.m237c(this);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (c == 1) {
            f = (float) ((int) (((double) f4) * 0.8d));
            f2 = (float) ((int) (((double) f5) * 0.7d));
        } else if (c == 2) {
            f = (float) ((int) (((double) f4) * 0.7d));
            f2 = (float) ((int) (((double) f5) * 0.8d));
        } else {
            f2 = f5;
            f = f4;
        }
        m144a("************************************************");
        m144a("density:" + f3 + "\n");
        m144a("widthPix:" + f4 + "\n");
        m144a("heightPix:" + f5 + "\n");
        m144a("orientation:" + c + "\n");
        m144a("mShowType:" + this.f104e + "\n");
        m144a("bitmapWidth:" + width + "   bitmapHeight:" + height + "\n");
        m144a("bitmapWPx:" + ManagerUtils.m229a((Context) this, (float) width) + "   bitmapHPx:" + ManagerUtils.m229a((Context) this, (float) height) + "\n");
        m144a("maxWidth:" + f + "   maxHeight:" + f2 + "\n");
        m144a("************************************************");
        if (((float) width) > f) {
            if (((float) height) > f2) {
                f4 = f / ((float) width);
                f3 = f2 / ((float) height);
                if (((float) width) > f) {
                    i = (int) (((float) width) * f4);
                    i2 = (int) (((float) height) * f4);
                } else {
                    i = (int) (((float) width) * f3);
                    i2 = (int) (((float) height) * f3);
                }
                m144a("scaleX:" + f4 + "  scaleY:" + f3 + " adjustW:" + i + "  adjustH:" + i2);
            } else {
                i = (int) f;
                i2 = (int) ((((float) height) * f) / ((float) width));
            }
        } else if (((float) height) > f2) {
            int i3 = (int) f2;
            i = (int) ((f2 * ((float) width)) / ((float) height));
            i2 = i3;
        } else {
            i2 = height;
            i = width;
        }
        m144a("adjsutW:" + i + "   adjustH:" + i2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i2);
        layoutParams.addRule(13, -1);
        this.f105f.setLayoutParams(layoutParams);
        this.f105f.setImageBitmap(bitmap);
        this.f105f.setBackgroundColor(SupportMenu.CATEGORY_MASK);
    }

    /* renamed from: a */
    private void m142a(RelativeLayout relativeLayout, String str) {
        this.f106g = new WebView(this);
        this.f106g.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.addView(this.f106g);
        Bitmap a = ManagerUtils.m230a("iVBORw0KGgoAAAANSUhEUgAAAGwAAABsCAYAAACPZlfNAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAxZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6M0JENDhEODAwMzk3MTFFNkFCRENDMkQ2RUJBQTI3MkUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6M0JENDhEN0YwMzk3MTFFNkFCRENDMkQ2RUJBQTI3MkUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgTWFjaW50b3NoIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9IjUyM0UzN0UwMzMxOEU1QkNDRTYzNzA4Njg0MTBENkNFIiBzdFJlZjpkb2N1bWVudElEPSI1MjNFMzdFMDMzMThFNUJDQ0U2MzcwODY4NDEwRDZDRSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PprAUIQAAAmrSURBVHja7J1rTJRXGsePIDeFVRFqs3Wt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");
        int width = a.getWidth();
        int height = a.getHeight();
        m144a("build webview close btn:" + a + "  width:" + width + "  height:" + height);
        if (a != null) {
            ImageView imageView = new ImageView(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
            layoutParams.addRule(11, -1);
            layoutParams.rightMargin = 10;
            layoutParams.topMargin = 10;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageBitmap(a);
            imageView.setOnClickListener(this.f101a);
            relativeLayout.addView(imageView);
        }
        m140a(this.f106g, str);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    /* renamed from: a */
    private void m140a(WebView webView, String str) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(PluginState.ON);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(2);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.clearHistory();
        webView.clearCache(true);
        if (str != null) {
            webView.loadUrl(str);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m144a("onConfigurationChanged:" + configuration.orientation);
        if (configuration.orientation == 1) {
            this.f103c = this.f102b.getVertical();
        } else {
            this.f103c = this.f102b.getHorizontal();
        }
        if (this.f103c == null) {
            m144a("JicePushView does not support current screen orientation:" + configuration.orientation);
            m146b();
            return;
        }
        try {
            if (this.f104e == C0477f.IMAGE) {
                Bitmap cacheBitmap = this.f103c.getCacheBitmap();
                if (cacheBitmap != null && this.f105f != null) {
                    m139a(cacheBitmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (f100d != null) {
            f100d.mo7615a();
        }
        m146b();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        m146b();
    }

    /* renamed from: b */
    private void m146b() {
        this.f102b = null;
        this.f103c = null;
        f100d = null;
        this.f104e = null;
        this.f105f = null;
        this.f106g = null;
        finish();
        overridePendingTransition(17432576, 17432577);
    }

    /* renamed from: a */
    private void m144a(String str) {
    }
}
