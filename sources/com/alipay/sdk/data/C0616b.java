package com.alipay.sdk.data;

import android.content.Context;
import com.alipay.sdk.packet.C0628b;
import com.alipay.sdk.packet.impl.C0633b;
import com.alipay.sdk.util.C0646c;

/* renamed from: com.alipay.sdk.data.b */
class C0616b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f578a;
    /* renamed from: b */
    final /* synthetic */ C0615a f579b;

    C0616b(C0615a c0615a, Context context) {
        this.f579b = c0615a;
        this.f578a = context;
    }

    public void run() {
        try {
            C0628b a = new C0633b().mo8073a(this.f578a);
            if (a != null) {
                this.f579b.m868b(a.mo8066b());
                this.f579b.m871g();
            }
        } catch (Throwable th) {
            C0646c.m1016a(th);
        }
    }
}
