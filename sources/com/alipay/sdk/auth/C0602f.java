package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.alipay.sdk.auth.f */
class C0602f implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C0600d f541a;

    C0602f(C0600d c0600d) {
        this.f541a = c0600d;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f541a.f538a.cancel();
        AuthActivity.this.f532g = false;
        C0603g.m841a(AuthActivity.this, AuthActivity.this.f529d + "?resultCode=150");
        AuthActivity.this.finish();
    }
}
