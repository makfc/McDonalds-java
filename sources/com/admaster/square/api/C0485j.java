package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import com.admaster.square.utils.Constant;

/* compiled from: ConvMobiInstance */
/* renamed from: com.admaster.square.api.j */
class C0485j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ConvMobiInstance f198a;
    /* renamed from: b */
    private final /* synthetic */ Context f199b;
    /* renamed from: c */
    private final /* synthetic */ String f200c;

    C0485j(ConvMobiInstance convMobiInstance, Context context, String str) {
        this.f198a = convMobiInstance;
        this.f199b = context;
        this.f200c = str;
    }

    public void run() {
        String a = DeviceInfo.m335a(this.f199b);
        if (!TextUtils.isEmpty(a)) {
            Constant.f267i = a;
        }
        this.f198a.f179a = ConvMobiHandler.m276a(this.f199b, this.f200c, this.f198a.f180b, false);
    }
}
