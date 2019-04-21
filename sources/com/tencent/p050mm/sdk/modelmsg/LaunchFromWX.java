package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX */
public class LaunchFromWX {

    /* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX$Req */
    public static class Req extends BaseReq {
        public String country;
        public String lang;
        public String messageAction;
        public String messageExt;

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        public boolean checkArgs() {
            if (this.messageAction != null && this.messageAction.length() > 2048) {
                C4353b.m7889b("MicroMsg.SDK.LaunchFromWX.Req", "checkArgs fail, messageAction is too long");
                return false;
            } else if (this.messageExt == null || this.messageExt.length() <= 2048) {
                return true;
            } else {
                C4353b.m7889b("MicroMsg.SDK.LaunchFromWX.Req", "checkArgs fail, messageExt is too long");
                return false;
            }
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.messageAction = bundle.getString("_wxobject_message_action");
            this.messageExt = bundle.getString("_wxobject_message_ext");
            this.lang = bundle.getString("_wxapi_launch_req_lang");
            this.country = bundle.getString("_wxapi_launch_req_country");
        }

        public int getType() {
            return 6;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxobject_message_action", this.messageAction);
            bundle.putString("_wxobject_message_ext", this.messageExt);
            bundle.putString("_wxapi_launch_req_lang", this.lang);
            bundle.putString("_wxapi_launch_req_country", this.country);
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.LaunchFromWX$Resp */
    public static class Resp extends BaseResp {
        public int getType() {
            return 6;
        }
    }

    private LaunchFromWX() {
    }
}
