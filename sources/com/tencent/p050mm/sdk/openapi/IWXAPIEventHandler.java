package com.tencent.p050mm.sdk.openapi;

import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.openapi.IWXAPIEventHandler */
public interface IWXAPIEventHandler {
    void onReq(BaseReq baseReq);

    void onResp(BaseResp baseResp);
}
