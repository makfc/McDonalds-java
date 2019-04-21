package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.auth.e */
class C0601e implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0600d f540a;

    C0601e(C0600d c0600d) {
        this.f540a = c0600d;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AuthActivity.this.f532g = true;
        this.f540a.f538a.proceed();
        dialogInterface.dismiss();
    }
}
