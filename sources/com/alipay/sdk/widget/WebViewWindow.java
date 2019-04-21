package com.alipay.sdk.widget;

import android.content.Context;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.util.C0653j;
import com.alipay.sdk.util.C0657m;

public class WebViewWindow extends LinearLayout {
    /* renamed from: f */
    private static Handler f669f = new Handler(Looper.getMainLooper());
    /* renamed from: a */
    private ImageView f670a;
    /* renamed from: b */
    private TextView f671b;
    /* renamed from: c */
    private ImageView f672c;
    /* renamed from: d */
    private ProgressBar f673d;
    /* renamed from: e */
    private WebView f674e;
    /* renamed from: g */
    private C0660a f675g;
    /* renamed from: h */
    private C0661b f676h;
    /* renamed from: i */
    private C0662c f677i;
    /* renamed from: j */
    private OnClickListener f678j;
    /* renamed from: k */
    private final float f679k;

    /* renamed from: com.alipay.sdk.widget.WebViewWindow$a */
    public interface C0660a {
        /* renamed from: a */
        void mo8118a(WebViewWindow webViewWindow, String str);

        /* renamed from: a */
        boolean mo8119a(WebViewWindow webViewWindow, String str, String str2, String str3, JsPromptResult jsPromptResult);
    }

    /* renamed from: com.alipay.sdk.widget.WebViewWindow$b */
    public interface C0661b {
        /* renamed from: a */
        boolean mo8120a(WebViewWindow webViewWindow, int i, String str, String str2);

        /* renamed from: a */
        boolean mo8121a(WebViewWindow webViewWindow, SslErrorHandler sslErrorHandler, SslError sslError);

        /* renamed from: b */
        boolean mo8122b(WebViewWindow webViewWindow, String str);

        /* renamed from: c */
        boolean mo8123c(WebViewWindow webViewWindow, String str);
    }

    /* renamed from: com.alipay.sdk.widget.WebViewWindow$c */
    public interface C0662c {
        /* renamed from: a */
        void mo8124a(WebViewWindow webViewWindow);

        /* renamed from: b */
        void mo8125b(WebViewWindow webViewWindow);
    }

    public WebViewWindow(Context context) {
        this(context, null);
    }

    public WebViewWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f678j = new C0681q(this);
        this.f679k = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        m1094a(context);
        m1097b(context);
        m1099c(context);
    }

    /* renamed from: a */
    private void m1094a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        this.f670a = new ImageView(context);
        this.f670a.setOnClickListener(this.f678j);
        this.f670a.setScaleType(ScaleType.CENTER);
        this.f670a.setImageDrawable(C0653j.m1038a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABIBAMAAACnw650AAAAFVBMVEUAAAARjusRkOkQjuoRkeoRj+oQjunya570AAAABnRSTlMAinWeSkk7CjRNAAAAZElEQVRIx+3MOw6AIBQF0YsrMDGx1obaLeGH/S9BQgkJ82rypp4ceTN1ilvyKizmZIAyU7FML0JVYig55BBAfQ2EU4V4CpZJ+2AiSj11C6rUoTannBpRn4W6xNQjLBSI2+TN0w/+3HT2wPClrQAAAABJRU5ErkJggg==", context));
        this.f670a.setPadding(m1092a(12), 0, m1092a(12), 0);
        linearLayout.addView(this.f670a, new LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LayoutParams(m1092a(1), m1092a(25)));
        this.f671b = new TextView(context);
        this.f671b.setTextColor(-15658735);
        this.f671b.setTextSize(17.0f);
        this.f671b.setMaxLines(1);
        this.f671b.setEllipsize(TruncateAt.END);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(m1092a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.f671b, layoutParams);
        this.f672c = new ImageView(context);
        this.f672c.setOnClickListener(this.f678j);
        this.f672c.setScaleType(ScaleType.CENTER);
        this.f672c.setImageDrawable(C0653j.m1038a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAmVBMVEUAAAARj+oQjuoRkOsVk/AQj+oRjuoQj+oSkO3///8Rj+kRj+oQkOsTk+whm/8Qj+oRj+oQj+oSkus2p/8QjuoQj+oQj+oQj+oQj+oRj+oTkuwRj+oQj+oRj+oRj+oSkOsSkO0ZlfMbk+8XnPgQj+oRj+oQj+oQj+sSj+sRkOoSkescqv8Rj+oQj+oSj+sXku4Rj+kQjuoQjumXGBCVAAAAMnRSTlMAxPtPF8ry7CoB9npbGwe6lm0wBODazb1+aSejm5GEYjcTDwvls6uJc0g/CdWfRCF20AXrk5QAAAJqSURBVFjD7ZfXmpswEIUFphmDCxi3talurGvm/R8uYSDe5FNBwlzsxf6XmvFBmiaZ/PCdWDk9CWn61OhHCMAaXfoRAth7wx6EkMXnWyrho4yg4bDpquI8Jy78Q7eoj9cmUFijsaLM0JsD9CD0uQAa9aNdPuCFvbA7B9t/Becap8Pu6Q/2jcyH81VHc/WCHDQZXwbvtUhQ61iDlqadncU6Rp31yGkZIzOAu7AjtPpYGREzq/pY5DRFHS1siyO6HfkOKTrMjdb2qevV4zosK7MbkFY2LmYk55hL6juCIFWMOI2KGzblmho3b18EIbxL1hs6r5m2Q2WaEElwS3NW4xh6ZZJuzTtUsBKT4G0h35s4y1mNgkNoS6TZ8SKBXTZQGBNYdPTozXGYKoyLAmOasttjThT4xT6Ch+2qIjRhV9Ja3NC87Kyo5We1vCNEMW1T+j1VLZ9UhE54Q1DL52r5piJ0YxdegvWlHOwTu76uKkJX+MOTHno4YFSEbHYdhViojsLrCTg/MKnhKWaEYzvkZFM8aOkPH7iTSvoFZKD7jGEJbarkRaxQyOeWvGVIbsji152jK7TbDgRzcIuz7SGj89BFU8d30TqWeDtrILxyTkD1IXfvmHseuU3lVHDz607bw0f3xDqejm5ncd0j8VDwfoibRy8RcgTkWHBvocbDbMlJsQAkGnAOHwGy90kLmQY1Wkob07/GaCNRIzdoWK7/+6y/XkLDJCcynOGFuUrKIMuCMonNr9VpSOQoIxBgJ0SacGbzZNy4ICrkscvU2fpElYz+U3sd+aQThjfVmjNa5i15kLcojM3Gz8kP34jf4VaV3X55gNEAAAAASUVORK5CYII=", context));
        this.f672c.setPadding(m1092a(12), 0, m1092a(12), 0);
        linearLayout.addView(this.f672c, new LayoutParams(-2, -2));
        addView(linearLayout, new LayoutParams(-1, m1092a(48)));
    }

    /* renamed from: b */
    private void m1097b(Context context) {
        this.f673d = new ProgressBar(context, null, 16973855);
        this.f673d.setProgressDrawable(context.getResources().getDrawable(17301612));
        this.f673d.setMax(100);
        this.f673d.setBackgroundColor(-218103809);
        addView(this.f673d, new LayoutParams(-1, m1092a(2)));
    }

    /* renamed from: c */
    private void m1099c(Context context) {
        this.f674e = new WebView(context);
        this.f674e.setVerticalScrollbarOverlay(true);
        mo8127a(this.f674e, context);
        WebSettings settings = this.f674e.getSettings();
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(-1);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        try {
            this.f674e.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f674e.removeJavascriptInterface("accessibility");
            this.f674e.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception e) {
        }
        addView(this.f674e, new LayoutParams(-1, -1));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8127a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        String packageName = context.getPackageName();
        webView.getSettings().setUserAgentString(userAgentString + " AlipaySDK(" + packageName + "/" + C0657m.m1083j(context) + "/" + "15.5.9" + ")");
    }

    public void setChromeProxy(C0660a c0660a) {
        this.f675g = c0660a;
        if (c0660a == null) {
            this.f674e.setWebChromeClient(null);
        } else {
            this.f674e.setWebChromeClient(new C0683s(this));
        }
    }

    public void setWebClientProxy(C0661b c0661b) {
        this.f676h = c0661b;
        if (c0661b == null) {
            this.f674e.setWebViewClient(null);
        } else {
            this.f674e.setWebViewClient(new C0684t(this));
        }
    }

    public void setWebEventProxy(C0662c c0662c) {
        this.f677i = c0662c;
    }

    public String getUrl() {
        return this.f674e.getUrl();
    }

    /* renamed from: a */
    public void mo8128a(String str) {
        this.f674e.loadUrl(str);
    }

    /* renamed from: a */
    public void mo8129a(String str, byte[] bArr) {
        this.f674e.postUrl(str, bArr);
    }

    public ImageView getBackButton() {
        return this.f670a;
    }

    public TextView getTitle() {
        return this.f671b;
    }

    public ImageView getRefreshButton() {
        return this.f672c;
    }

    public ProgressBar getProgressbar() {
        return this.f673d;
    }

    public WebView getWebView() {
        return this.f674e;
    }

    /* renamed from: a */
    public void mo8126a() {
        removeAllViews();
        this.f674e.removeAllViews();
        this.f674e.setWebViewClient(null);
        this.f674e.setWebChromeClient(null);
        this.f674e.destroy();
    }

    /* renamed from: a */
    private int m1092a(int i) {
        return (int) (((float) i) * this.f679k);
    }
}
