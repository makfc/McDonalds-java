package com.admaster.jice.p004a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.admaster.jice.p005b.C0476e;
import com.admaster.jice.p005b.C0477f;
import com.admaster.jice.p005b.JicePushConfig;
import com.admaster.jice.p007d.ManagerUtils;

/* renamed from: com.admaster.jice.a.o */
public class JicePushView {
    /* renamed from: j */
    private static JiceViewVisitor f71j = null;
    /* renamed from: a */
    private Context f72a;
    /* renamed from: b */
    private ViewGroup f73b;
    /* renamed from: c */
    private ViewGroup f74c;
    /* renamed from: d */
    private boolean f75d = false;
    /* renamed from: e */
    private JicePushConfig f76e = null;
    /* renamed from: f */
    private C0476e f77f = null;
    /* renamed from: g */
    private C0477f f78g = null;
    /* renamed from: h */
    private ImageView f79h;
    /* renamed from: i */
    private WebView f80i = null;
    /* renamed from: k */
    private final OnTouchListener f81k = new C0466p(this);
    /* renamed from: l */
    private OnClickListener f82l = new C0467q(this);

    public JicePushView(Context context, JicePushConfig jicePushConfig, JiceViewVisitor jiceViewVisitor) {
        this.f72a = context;
        this.f76e = jicePushConfig;
        f71j = jiceViewVisitor;
        m112f();
    }

    /* renamed from: f */
    private void m112f() {
        this.f73b = (ViewGroup) ((Activity) this.f72a).getWindow().getDecorView().findViewById(16908290);
        this.f74c = new RelativeLayout(this.f72a);
        this.f74c.setLayoutParams(new LayoutParams(-1, -1));
        this.f74c.setClickable(true);
        this.f74c.setBackgroundColor(Color.argb(120, 0, 0, 0));
        this.f74c.setTag(Integer.valueOf(1));
        this.f74c.setFocusable(true);
        this.f74c.setFocusableInTouchMode(true);
        this.f74c.setOnKeyListener(new C0468r(this));
        int c = ManagerUtils.m237c(this.f72a);
        if (c == 1) {
            this.f77f = this.f76e.getVertical();
        } else if (c == 2) {
            this.f77f = this.f76e.getHorizontal();
        }
        this.f78g = this.f77f.getType();
        if (this.f78g == C0477f.IMAGE) {
            try {
                m103a(this.f77f.getCacheBitmap());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        String url = this.f77f.getUrl();
        if (TextUtils.isEmpty(url)) {
            String str = "the orientation:" + ManagerUtils.m231a(c) + "  image resource is null!";
            m110b(str);
            if (f71j != null) {
                f71j.mo7616a(str);
                return;
            }
            return;
        }
        m107a(url);
    }

    /* renamed from: a */
    private void m103a(Bitmap bitmap) {
        this.f79h = new ImageView(this.f72a);
        this.f74c.addView(this.f79h);
        m109b(bitmap);
        this.f79h.setOnClickListener(this.f82l);
    }

    /* renamed from: a */
    private void m107a(String str) {
        this.f80i = new WebView(this.f72a);
        this.f80i.setLayoutParams(new LayoutParams(-1, -1));
        this.f74c.addView(this.f80i);
        Bitmap a = ManagerUtils.m230a("iVBORw0KGgoAAAANSUhEUgAAAGwAAABsCAYAAACPZlfNAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAxZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6M0JENDhEODAwMzk3MTFFNkFCRENDMkQ2RUJBQTI3MkUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6M0JENDhEN0YwMzk3MTFFNkFCRENDMkQ2RUJBQTI3MkUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgTWFjaW50b3NoIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9IjUyM0UzN0UwMzMxOEU1QkNDRTYzNzA4Njg0MTBENkNFIiBzdFJlZjpkb2N1bWVudElEPSI1MjNFMzdFMDMzMThFNUJDQ0U2MzcwODY4NDEwRDZDRSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PprAUIQAAAmrSURBVHja7J1rTJRXGsePIDeFVRFqs3Wt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");
        int width = a.getWidth();
        int height = a.getHeight();
        m110b("build webview close btn:" + a + "  width:" + width + "  height:" + height);
        if (a != null) {
            ImageView imageView = new ImageView(this.f72a);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
            layoutParams.addRule(11, -1);
            layoutParams.rightMargin = 10;
            layoutParams.topMargin = 10;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageBitmap(a);
            imageView.setOnClickListener(this.f82l);
            this.f74c.addView(imageView);
        }
        m105a(this.f80i, str);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    /* renamed from: a */
    private void m105a(WebView webView, String str) {
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

    /* renamed from: a */
    private void m104a(View view) {
        this.f73b.addView(view);
        this.f74c.requestFocus();
    }

    /* renamed from: a */
    public boolean mo7623a() {
        if (this.f73b.findViewWithTag(Integer.valueOf(1)) == this.f74c) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public void mo7624b() {
        if (!this.f75d) {
            this.f73b.post(new C0469s(this));
            this.f75d = true;
        }
    }

    /* renamed from: c */
    public void mo7625c() {
        if (mo7623a()) {
            m110b("show failed:push view isShowing");
        } else {
            m104a(this.f74c);
        }
    }

    /* renamed from: a */
    public void mo7622a(Boolean bool) {
        if (bool.booleanValue()) {
            this.f74c.setOnTouchListener(this.f81k);
        } else {
            this.f74c.setOnTouchListener(null);
        }
    }

    /* renamed from: b */
    private void m110b(String str) {
        Log.d("JicePushView", str);
    }

    /* renamed from: b */
    private void m109b(Bitmap bitmap) {
        int i;
        float f = (float) this.f72a.getResources().getDisplayMetrics().widthPixels;
        float f2 = (float) this.f72a.getResources().getDisplayMetrics().heightPixels;
        int c = ManagerUtils.m237c(this.f72a);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f3;
        if (c == 1) {
            f3 = (float) ((int) (((double) f2) * 0.7d));
            f2 = (float) ((int) (((double) f) * 0.8d));
            f = f3;
        } else if (c == 2) {
            f3 = (float) ((int) (((double) f2) * 0.8d));
            f2 = (float) ((int) (((double) f) * 0.7d));
            f = f3;
        } else {
            f3 = f2;
            f2 = f;
            f = f3;
        }
        if (((float) width) > f2) {
            if (((float) height) > f) {
                float f4 = f2 / ((float) width);
                float f5 = f / ((float) height);
                if (((float) width) > f2) {
                    i = (int) (((float) height) * f4);
                    height = (int) (((float) width) * f4);
                } else {
                    i = (int) (((float) height) * f5);
                    height = (int) (((float) width) * f5);
                }
            } else {
                int i2 = (int) f2;
                i = (int) ((f2 * ((float) height)) / ((float) width));
                height = i2;
            }
        } else if (((float) height) > f) {
            i = (int) f;
            height = (int) ((f * ((float) width)) / ((float) height));
        } else {
            i = height;
            height = width;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(height, i);
        layoutParams.addRule(13, -1);
        this.f79h.setLayoutParams(layoutParams);
        this.f79h.setImageBitmap(bitmap);
    }

    /* renamed from: d */
    public void mo7626d() {
        this.f72a = null;
        this.f73b = null;
        this.f74c = null;
        this.f79h = null;
        this.f77f = null;
        f71j = null;
    }
}
