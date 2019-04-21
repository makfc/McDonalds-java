package com.alipay.sdk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.app.d */
class C0582d implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0581c f487a;

    C0582d(C0581c c0581c) {
        this.f487a = c0581c;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f487a.f486b.f481b = true;
        this.f487a.f485a.proceed();
        dialogInterface.dismiss();
    }
}
