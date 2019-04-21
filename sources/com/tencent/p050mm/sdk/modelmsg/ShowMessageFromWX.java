package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.modelmsg.WXMediaMessage.Builder;

/* renamed from: com.tencent.mm.sdk.modelmsg.ShowMessageFromWX */
public class ShowMessageFromWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.ShowMessageFromWX$Req */
    public static class Req extends BaseReq {
        public String country;
        public String lang;
        public WXMediaMessage message;

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        public boolean checkArgs() {
            return this.message == null ? false : this.message.checkArgs();
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.lang = bundle.getString("_wxapi_showmessage_req_lang");
            this.country = bundle.getString("_wxapi_showmessage_req_country");
            this.message = Builder.fromBundle(bundle);
        }

        public int getType() {
            return 4;
        }

        public void toBundle(Bundle bundle) {
            Bundle toBundle = Builder.toBundle(this.message);
            super.toBundle(toBundle);
            bundle.putString("_wxapi_showmessage_req_lang", this.lang);
            bundle.putString("_wxapi_showmessage_req_country", this.country);
            bundle.putAll(toBundle);
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.ShowMessageFromWX$Resp */
    public static class Resp extends BaseResp {
        public int getType() {
            return 4;
        }
    }

    private ShowMessageFromWX() {
    }
}
