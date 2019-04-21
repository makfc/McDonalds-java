package com.alipay.sdk.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.alipay.sdk.widget.WebViewWindow.C0662c;

/* renamed from: com.alipay.sdk.widget.q */
class C0681q implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ WebViewWindow f715a;

    C0681q(WebViewWindow webViewWindow) {
        this.f715a = webViewWindow;
    }

    public void onClick(View view) {
        C0662c a = this.f715a.f677i;
        if (a != null) {
            view.setEnabled(false);
            WebViewWindow.f669f.postDelayed(new C0682r(this, view), 256);
            if (view == this.f715a.f670a) {
                a.mo8124a(this.f715a);
            } else if (view == this.f715a.f672c) {
                a.mo8125b(this.f715a);
            }
        }
    }
}
