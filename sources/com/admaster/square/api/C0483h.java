package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import com.admaster.square.utils.Constant;

/* compiled from: ConvMobiInstance */
/* renamed from: com.admaster.square.api.h */
class C0483h implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ConvMobiInstance f190a;
    /* renamed from: b */
    private final /* synthetic */ Context f191b;
    /* renamed from: c */
    private final /* synthetic */ String f192c;
    /* renamed from: d */
    private final /* synthetic */ String f193d;

    C0483h(ConvMobiInstance convMobiInstance, Context context, String str, String str2) {
        this.f190a = convMobiInstance;
        this.f191b = context;
        this.f192c = str;
        this.f193d = str2;
    }

    public void run() {
        String a = DeviceInfo.m335a(this.f191b);
        if (!TextUtils.isEmpty(a)) {
            Constant.f267i = a;
        }
        this.f190a.f179a = ConvMobiHandler.m275a(this.f191b, this.f192c, this.f193d, this.f190a.f180b, false);
    }
}
