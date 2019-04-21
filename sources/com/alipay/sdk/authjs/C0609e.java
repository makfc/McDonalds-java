package com.alipay.sdk.authjs;

import com.alipay.sdk.authjs.C0606a.C0605a;
import org.json.JSONException;

/* renamed from: com.alipay.sdk.authjs.e */
class C0609e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0606a f562a;
    /* renamed from: b */
    final /* synthetic */ C0608d f563b;

    C0609e(C0608d c0608d, C0606a c0606a) {
        this.f563b = c0608d;
        this.f562a = c0606a;
    }

    public void run() {
        C0605a a = this.f563b.m857b(this.f562a);
        if (a != C0605a.NONE_ERROR) {
            try {
                this.f563b.m856a(this.f562a.mo8039b(), a, true);
            } catch (JSONException e) {
            }
        }
    }
}
