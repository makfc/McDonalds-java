package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.C0674j.C0673a;

/* renamed from: com.alipay.sdk.widget.m */
class C0677m extends C0673a {
    /* renamed from: a */
    final /* synthetic */ WebViewWindow f708a;
    /* renamed from: b */
    final /* synthetic */ String f709b;
    /* renamed from: c */
    final /* synthetic */ C0674j f710c;

    C0677m(C0674j c0674j, WebViewWindow webViewWindow, String str) {
        this.f710c = c0674j;
        this.f708a = webViewWindow;
        this.f709b = str;
        super(c0674j, null);
    }

    public void onAnimationEnd(Animation animation) {
        this.f710c.removeView(this.f708a);
        this.f710c.f703x.mo8128a(this.f709b);
        this.f710c.f701v = false;
    }
}
