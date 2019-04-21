package com.admaster.jice.p007d;

import android.content.Context;

/* compiled from: HttpURLRequest */
/* renamed from: com.admaster.jice.d.c */
class C0478c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ HttpURLRequest f131a;
    /* renamed from: b */
    private final /* synthetic */ Context f132b;

    C0478c(HttpURLRequest httpURLRequest, Context context) {
        this.f131a = httpURLRequest;
        this.f132b = context;
    }

    public void run() {
        System.out.println(" switch on Main Looper Thread  to getWebKitUserAgent");
        this.f131a.m219b(this.f132b);
    }
}
