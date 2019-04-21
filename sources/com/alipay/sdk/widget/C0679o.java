package com.alipay.sdk.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.widget.o */
class C0679o implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0678n f713a;

    C0679o(C0678n c0678n) {
        this.f713a = c0678n;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f713a.f712b.f702w = true;
        this.f713a.f711a.proceed();
        dialogInterface.dismiss();
    }
}
