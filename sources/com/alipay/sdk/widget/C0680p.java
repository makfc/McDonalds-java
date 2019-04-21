package com.alipay.sdk.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.alipay.sdk.app.C0588j;

/* renamed from: com.alipay.sdk.widget.p */
class C0680p implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0678n f714a;

    C0680p(C0678n c0678n) {
        this.f714a = c0678n;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f714a.f711a.cancel();
        this.f714a.f712b.f702w = false;
        C0588j.m790a(C0588j.m793c());
        this.f714a.f712b.f694a.finish();
    }
}
