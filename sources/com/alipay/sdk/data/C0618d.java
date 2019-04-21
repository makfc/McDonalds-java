package com.alipay.sdk.data;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* renamed from: com.alipay.sdk.data.d */
class C0618d implements Callable<String> {
    /* renamed from: a */
    final /* synthetic */ Context f584a;
    /* renamed from: b */
    final /* synthetic */ HashMap f585b;
    /* renamed from: c */
    final /* synthetic */ C0617c f586c;

    C0618d(C0617c c0617c, Context context, HashMap hashMap) {
        this.f586c = c0617c;
        this.f584a = context;
        this.f585b = hashMap;
    }

    /* renamed from: a */
    public String call() throws Exception {
        return this.f586c.m877a(this.f584a, this.f585b);
    }
}
