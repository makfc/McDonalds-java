package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.p050mm.sdk.p066b.C4353b;
import java.io.File;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXFileObject */
public class WXFileObject implements IMediaObject {
    private int contentLengthLimit = 10485760;
    public byte[] fileData = null;
    public String filePath = null;

    private int getFileSize(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        return file.exists() ? (int) file.length() : 0;
    }

    public boolean checkArgs() {
        if ((this.fileData == null || this.fileData.length == 0) && (this.filePath == null || this.filePath.length() == 0)) {
            C4353b.m7889b("MicroMsg.SDK.WXFileObject", "checkArgs fail, both arguments is null");
            return false;
        } else if (this.fileData != null && this.fileData.length > this.contentLengthLimit) {
            C4353b.m7889b("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileData is too large");
            return false;
        } else if (this.filePath == null || getFileSize(this.filePath) <= this.contentLengthLimit) {
            return true;
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wxfileobject_fileData", this.fileData);
        bundle.putString("_wxfileobject_filePath", this.filePath);
    }

    public void setContentLengthLimit(int i) {
        this.contentLengthLimit = i;
    }

    public int type() {
        return 6;
    }

    public void unserialize(Bundle bundle) {
        this.fileData = bundle.getByteArray("_wxfileobject_fileData");
        this.filePath = bundle.getString("_wxfileobject_filePath");
    }
}
