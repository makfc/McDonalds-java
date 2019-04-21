package com.admaster.square.api;

import android.content.Context;
import android.text.TextUtils;
import com.admaster.square.utils.Constant;

/* compiled from: ConvMobiInstance */
/* renamed from: com.admaster.square.api.i */
class C0484i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ConvMobiInstance f194a;
    /* renamed from: b */
    private final /* synthetic */ Context f195b;
    /* renamed from: c */
    private final /* synthetic */ String f196c;
    /* renamed from: d */
    private final /* synthetic */ boolean f197d;

    C0484i(ConvMobiInstance convMobiInstance, Context context, String str, boolean z) {
        this.f194a = convMobiInstance;
        this.f195b = context;
        this.f196c = str;
        this.f197d = z;
    }

    public void run() {
        String a = DeviceInfo.m335a(this.f195b);
        if (!TextUtils.isEmpty(a)) {
            Constant.f267i = a;
        }
        this.f194a.f179a = ConvMobiHandler.m276a(this.f195b, this.f196c, this.f194a.f180b, this.f197d);
    }
}
