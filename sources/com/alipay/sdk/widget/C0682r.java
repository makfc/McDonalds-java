package com.alipay.sdk.widget;

import android.view.View;

/* renamed from: com.alipay.sdk.widget.r */
class C0682r implements Runnable {
    /* renamed from: a */
    final /* synthetic */ View f716a;
    /* renamed from: b */
    final /* synthetic */ C0681q f717b;

    C0682r(C0681q c0681q, View view) {
        this.f717b = c0681q;
        this.f716a = view;
    }

    public void run() {
        this.f716a.setEnabled(true);
    }
}
