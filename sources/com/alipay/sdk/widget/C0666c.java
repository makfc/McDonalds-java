package com.alipay.sdk.widget;

import com.alipay.sdk.util.C0646c;

/* renamed from: com.alipay.sdk.widget.c */
class C0666c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0664a f691a;

    C0666c(C0664a c0664a) {
        this.f691a = c0664a;
    }

    public void run() {
        if (this.f691a.f682e != null && this.f691a.f682e.isShowing()) {
            try {
                this.f691a.f689l.removeMessages(1);
                this.f691a.f682e.dismiss();
            } catch (Exception e) {
                C0646c.m1016a(e);
            }
        }
    }
}
