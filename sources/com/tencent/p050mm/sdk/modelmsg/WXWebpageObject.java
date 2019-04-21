package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXWebpageObject */
public class WXWebpageObject implements IMediaObject {
    public String extInfo;
    public String webpageUrl;

    public boolean checkArgs() {
        if (this.webpageUrl != null && this.webpageUrl.length() != 0 && this.webpageUrl.length() <= 10240) {
            return true;
        }
        C4353b.m7889b("MicroMsg.SDK.WXWebpageObject", "checkArgs fail, webpageUrl is invalid");
        return false;
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxwebpageobject_extInfo", this.extInfo);
        bundle.putString("_wxwebpageobject_webpageUrl", this.webpageUrl);
    }

    public int type() {
        return 5;
    }

    public void unserialize(Bundle bundle) {
        this.extInfo = bundle.getString("_wxwebpageobject_extInfo");
        this.webpageUrl = bundle.getString("_wxwebpageobject_webpageUrl");
    }
}
