package com.alipay.sdk.util;

import android.app.Activity;

/* renamed from: com.alipay.sdk.util.o */
final class C0659o implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f668a;

    C0659o(Activity activity) {
        this.f668a = activity;
    }

    public void run() {
        this.f668a.finish();
    }
}
