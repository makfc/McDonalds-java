package com.alipay.sdk.widget;

import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.widget.C0664a.C0663a;

/* renamed from: com.alipay.sdk.widget.b */
class C0665b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0664a f690a;

    C0665b(C0664a c0664a) {
        this.f690a = c0664a;
    }

    public void run() {
        if (this.f690a.f682e == null) {
            this.f690a.f682e = new C0663a(this.f690a.f683f);
            this.f690a.f682e.setCancelable(this.f690a.f688k);
        }
        try {
            if (!this.f690a.f682e.isShowing()) {
                this.f690a.f682e.show();
                this.f690a.f689l.sendEmptyMessageDelayed(1, 15000);
            }
        } catch (Exception e) {
            C0646c.m1016a(e);
        }
    }
}
