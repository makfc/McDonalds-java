package com.alipay.sdk.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.alipay.sdk.widget.d */
class C0667d extends Handler {
    /* renamed from: a */
    final /* synthetic */ C0664a f692a;

    C0667d(C0664a c0664a, Looper looper) {
        this.f692a = c0664a;
        super(looper);
    }

    public void dispatchMessage(Message message) {
        this.f692a.mo8142c();
    }
}
