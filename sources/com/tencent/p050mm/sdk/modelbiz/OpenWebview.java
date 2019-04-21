package com.tencent.p050mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelbase.BaseReq;
import com.tencent.p050mm.sdk.modelbase.BaseResp;
import java.net.URLEncoder;

/* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview */
public class OpenWebview {

    /* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview$Req */
    public static class Req extends BaseReq {
        public String url;

        public boolean checkArgs() {
            return this.url != null && this.url.length() >= 0 && this.url.length() <= 10240;
        }

        public int getType() {
            return 12;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_webview_url", URLEncoder.encode(this.url));
        }
    }

    /* renamed from: com.tencent.mm.sdk.modelbiz.OpenWebview$Resp */
    public static class Resp extends BaseResp {
        public String result;

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.result = bundle.getString("_wxapi_open_webview_result");
        }

        public int getType() {
            return 12;
        }
    }
}
