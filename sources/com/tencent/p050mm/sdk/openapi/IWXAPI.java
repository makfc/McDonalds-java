package com.tencent.p050mm.sdk.openapi;

import android.content.Intent;
import com.tencent.p050mm.sdk.modelbase.BaseReq;

/* renamed from: com.tencent.mm.sdk.openapi.IWXAPI */
public interface IWXAPI {
    boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler);

    boolean isWXAppInstalled();

    boolean isWXAppSupportAPI();

    boolean registerApp(String str);

    boolean sendReq(BaseReq baseReq);

    void unregisterApp();
}
