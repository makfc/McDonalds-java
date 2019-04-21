package com.tencent.p050mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import com.tencent.p050mm.sdk.p066b.C4361h;

/* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom */
public class CreateChatroom {

    /* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom$Req */
    public static class Req extends BaseReq {
        public String chatroomName;
        public String chatroomNickName;
        public String extMsg;
        public String groupId;

        public boolean checkArgs() {
            return !C4361h.m7904h(this.groupId);
        }

        public int getType() {
            return 14;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_create_chatroom_group_id", this.groupId);
            bundle.putString("_wxapi_create_chatroom_chatroom_name", this.chatroomName);
            bundle.putString("_wxapi_create_chatroom_chatroom_nickname", this.chatroomNickName);
            bundle.putString("_wxapi_create_chatroom_ext_msg", this.extMsg);
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom$Resp */
    public static class Resp extends BaseResp {
        public String extMsg;

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.extMsg = bundle.getString("_wxapi_create_chatroom_ext_msg");
        }

        public int getType() {
            return 14;
        }
    }

    private CreateChatroom() {
    }
}
