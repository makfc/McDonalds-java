package com.tencent.p050mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizTempSession */
public final class JumpToBizTempSession {

    /* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizTempSession$Req */
    public static class Req extends BaseReq {
        public String sessionFrom;
        public int showType;
        public String toUserName;

        public boolean checkArgs() {
            if (this.toUserName == null || this.toUserName.length() <= 0) {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizTempSession.Req", "checkArgs fail, toUserName is invalid");
                return false;
            } else if (this.sessionFrom != null && this.sessionFrom.length() <= 1024) {
                return true;
            } else {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizTempSession.Req", "checkArgs fail, sessionFrom is invalid");
                return false;
            }
        }

        public int getType() {
            return 10;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_webview_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_webview_req_session_from", this.sessionFrom);
            bundle.putInt("_wxapi_jump_to_biz_webview_req_show_type", this.showType);
        }
    }
}
