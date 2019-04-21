package com.tencent.p050mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizWebview */
public final class JumpToBizWebview {

    /* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizWebview$Req */
    public static class Req extends BaseReq {
        public String extMsg;
        public int scene = 1;
        public String toUserName;
        public int webType;

        public boolean checkArgs() {
            if (this.toUserName == null || this.toUserName.length() <= 0) {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizWebview.Req", "checkArgs fail, toUserName is invalid");
                return false;
            } else if (this.extMsg == null || this.extMsg.length() <= 1024) {
                return true;
            } else {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizWebview.Req", "ext msg is not null, while the length exceed 1024 bytes");
                return false;
            }
        }

        public int getType() {
            return 8;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_webview_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_webview_req_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_jump_to_biz_webview_req_web_type", this.webType);
            bundle.putInt("_wxapi_jump_to_biz_webview_req_scene", this.scene);
        }
    }
}
