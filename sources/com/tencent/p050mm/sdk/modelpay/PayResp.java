package com.tencent.p050mm.sdk.modelpay;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseResp;

/* renamed from: com.tencent.mm.sdk.modelpay.PayResp */
public class PayResp extends BaseResp {
    public String extData;
    public String prepayId;
    public String returnKey;

    public PayResp(Bundle bundle) {
        fromBundle(bundle);
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.prepayId = bundle.getString("_wxapi_payresp_prepayid");
        this.returnKey = bundle.getString("_wxapi_payresp_returnkey");
        this.extData = bundle.getString("_wxapi_payresp_extdata");
    }

    public int getType() {
        return 5;
    }
}
