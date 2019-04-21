package com.tencent.p050mm.sdk.modelbase;

import android.os.Bundle;

/* renamed from: com.tencent.mm.sdk.modelbase.BaseResp */
public abstract class BaseResp {
    public int errCode;
    public String errStr;
    public String openId;
    public String transaction;

    /* renamed from: com.tencent.mm.sdk.modelbase.BaseResp$ErrCode */
    public interface ErrCode {
    }

    public void fromBundle(Bundle bundle) {
        this.errCode = bundle.getInt("_wxapi_baseresp_errcode");
        this.errStr = bundle.getString("_wxapi_baseresp_errstr");
        this.transaction = bundle.getString("_wxapi_baseresp_transaction");
        this.openId = bundle.getString("_wxapi_baseresp_openId");
    }

    public abstract int getType();
}
