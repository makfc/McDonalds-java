package com.alipay.sdk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.app.e */
class C0583e implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0581c f488a;

    C0583e(C0581c c0581c) {
        this.f488a = c0581c;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f488a.f485a.cancel();
        this.f488a.f486b.f481b = false;
        C0588j.m790a(C0588j.m793c());
        this.f488a.f486b.f480a.finish();
    }
}
