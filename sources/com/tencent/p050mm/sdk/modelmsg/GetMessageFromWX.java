package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.modelmsg.WXMediaMessage.Builder;

/* renamed from: com.tencent.mm.sdk.modelmsg.GetMessageFromWX */
public final class GetMessageFromWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.GetMessageFromWX$Req */
    public static class Req extends BaseReq {
        public String country;
        public String lang;

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        public boolean checkArgs() {
            return true;
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.lang = bundle.getString("_wxapi_getmessage_req_lang");
            this.country = bundle.getString("_wxapi_getmessage_req_country");
        }

        public int getType() {
            return 3;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_getmessage_req_lang", this.lang);
            bundle.putString("_wxapi_getmessage_req_country", this.country);
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.GetMessageFromWX$Resp */
    public static class Resp extends BaseResp {
        public WXMediaMessage message;

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.message = Builder.fromBundle(bundle);
        }

        public int getType() {
            return 3;
        }
    }

    private GetMessageFromWX() {
    }
}
