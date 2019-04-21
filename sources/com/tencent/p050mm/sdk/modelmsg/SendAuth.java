package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelmsg.SendAuth */
public final class SendAuth {

    /* renamed from: com.tencent.mm.sdk.modelmsg.SendAuth$Req */
    public static class Req extends BaseReq {
        public String scope;
        public String state;

        public boolean checkArgs() {
            if (this.scope == null || this.scope.length() == 0 || this.scope.length() > 1024) {
                C4353b.m7889b("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, scope is invalid");
                return false;
            } else if (this.state == null || this.state.length() <= 1024) {
                return true;
            } else {
                C4353b.m7889b("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, state is invalid");
                return false;
            }
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.scope = bundle.getString("_wxapi_sendauth_req_scope");
            this.state = bundle.getString("_wxapi_sendauth_req_state");
        }

        public int getType() {
            return 1;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_sendauth_req_scope", this.scope);
            bundle.putString("_wxapi_sendauth_req_state", this.state);
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.SendAuth$Resp */
    public static class Resp extends BaseResp {
        public String code;
        public String country;
        public String lang;
        public String state;
        public String url;

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.code = bundle.getString("_wxapi_sendauth_resp_token");
            this.state = bundle.getString("_wxapi_sendauth_resp_state");
            this.url = bundle.getString("_wxapi_sendauth_resp_url");
            this.lang = bundle.getString("_wxapi_sendauth_resp_lang");
            this.country = bundle.getString("_wxapi_sendauth_resp_country");
        }

        public int getType() {
            return 1;
        }
    }

    private SendAuth() {
    }
}
