package com.tencent.p050mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizProfile */
public class JumpToBizProfile {

    /* renamed from: com.tencent.mm.sdk.modelbiz.JumpToBizProfile$Req */
    public static class Req extends BaseReq {
        public String extMsg;
        public int profileType = 0;
        public String toUserName;

        public boolean checkArgs() {
            if (this.toUserName == null || this.toUserName.length() == 0) {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizProfile.Req", "checkArgs fail, toUserName is invalid");
                return false;
            } else if (this.extMsg != null && this.extMsg.length() > 1024) {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizProfile.Req", "ext msg is not null, while the length exceed 1024 bytes");
                return false;
            } else if (this.profileType != 1 || (this.extMsg != null && this.extMsg.length() != 0)) {
                return true;
            } else {
                C4353b.m7889b("MicroMsg.SDK.JumpToBizProfile.Req", "scene is jump to hardware profile, while extmsg is null");
                return false;
            }
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.toUserName = bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name");
            this.extMsg = bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg");
        }

        public int getType() {
            return 7;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_profile_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_profile_req_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_scene", 0);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_profile_type", this.profileType);
        }
    }
}
