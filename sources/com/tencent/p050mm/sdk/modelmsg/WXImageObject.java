package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.p050mm.sdk.p066b.C4353b;
import java.io.File;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXImageObject */
public class WXImageObject implements IMediaObject {
    public byte[] imageData;
    public String imagePath;

    private int getFileSize(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        return file.exists() ? (int) file.length() : 0;
    }

    public boolean checkArgs() {
        if ((this.imageData == null || this.imageData.length == 0) && (this.imagePath == null || this.imagePath.length() == 0)) {
            C4353b.m7889b("MicroMsg.SDK.WXImageObject", "checkArgs fail, all arguments are null");
            return false;
        } else if (this.imageData != null && this.imageData.length > 10485760) {
            C4353b.m7889b("MicroMsg.SDK.WXImageObject", "checkArgs fail, content is too large");
            return false;
        } else if (this.imagePath != null && this.imagePath.length() > 10240) {
            C4353b.m7889b("MicroMsg.SDK.WXImageObject", "checkArgs fail, path is invalid");
            return false;
        } else if (this.imagePath == null || getFileSize(this.imagePath) <= 10485760) {
            return true;
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
    }

    public int type() {
        return 2;
    }

    public void unserialize(Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
    }
}
