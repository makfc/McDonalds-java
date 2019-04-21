package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import com.admaster.square.utils.Constant;

/* compiled from: ConvMobiInstance */
/* renamed from: com.admaster.square.api.g */
class C0482g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ConvMobiInstance f185a;
    /* renamed from: b */
    private final /* synthetic */ Context f186b;
    /* renamed from: c */
    private final /* synthetic */ String f187c;
    /* renamed from: d */
    private final /* synthetic */ String f188d;
    /* renamed from: e */
    private final /* synthetic */ boolean f189e;

    C0482g(ConvMobiInstance convMobiInstance, Context context, String str, String str2, boolean z) {
        this.f185a = convMobiInstance;
        this.f186b = context;
        this.f187c = str;
        this.f188d = str2;
        this.f189e = z;
    }

    public void run() {
        String a = DeviceInfo.m335a(this.f186b);
        if (!TextUtils.isEmpty(a)) {
            Constant.f267i = a;
        }
        this.f185a.f179a = ConvMobiHandler.m275a(this.f186b, this.f187c, this.f188d, this.f185a.f180b, this.f189e);
    }
}
