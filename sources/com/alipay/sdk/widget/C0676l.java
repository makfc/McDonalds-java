package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.C0674j.C0673a;

/* renamed from: com.alipay.sdk.widget.l */
class C0676l extends C0673a {
    /* renamed from: a */
    final /* synthetic */ WebViewWindow f706a;
    /* renamed from: b */
    final /* synthetic */ C0674j f707b;

    C0676l(C0674j c0674j, WebViewWindow webViewWindow) {
        this.f707b = c0674j;
        this.f706a = webViewWindow;
        super(c0674j, null);
    }

    public void onAnimationEnd(Animation animation) {
        this.f706a.mo8126a();
        this.f707b.f701v = false;
    }
}
