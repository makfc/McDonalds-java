package com.tencent.p050mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p050mm.sdk.p066b.C4353b;

/* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage */
public final class WXMediaMessage {
    public String description;
    public IMediaObject mediaObject;
    public String mediaTagName;
    public String messageAction;
    public String messageExt;
    public int sdkVer;
    public byte[] thumbData;
    public String title;

    /* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject */
    public interface IMediaObject {
        boolean checkArgs();

        void serialize(Bundle bundle);

        int type();

        void unserialize(Bundle bundle);
    }

    /* renamed from: com.tencent.mm.sdk.modelmsg.WXMediaMessage$Builder */
    public static class Builder {
        public static WXMediaMessage fromBundle(Bundle bundle) {
            WXMediaMessage wXMediaMessage = new WXMediaMessage();
            wXMediaMessage.sdkVer = bundle.getInt("_wxobject_sdkVer");
            wXMediaMessage.title = bundle.getString("_wxobject_title");
            wXMediaMessage.description = bundle.getString("_wxobject_description");
            wXMediaMessage.thumbData = bundle.getByteArray("_wxobject_thumbdata");
            wXMediaMessage.mediaTagName = bundle.getString("_wxobject_mediatagname");
            wXMediaMessage.messageAction = bundle.getString("_wxobject_message_action");
            wXMediaMessage.messageExt = bundle.getString("_wxobject_message_ext");
            String pathOldToNew = Builder.pathOldToNew(bundle.getString("_wxobject_identifier_"));
            if (pathOldToNew == null || pathOldToNew.length() <= 0) {
                return wXMediaMessage;
            }
            try {
                wXMediaMessage.mediaObject = (IMediaObject) Class.forName(pathOldToNew).newInstance();
                wXMediaMessage.mediaObject.unserialize(bundle);
                return wXMediaMessage;
            } catch (Exception e) {
                e.printStackTrace();
                C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "get media object from bundle failed: unknown ident " + pathOldToNew + ", ex = " + e.getMessage());
                return wXMediaMessage;
            }
        }

        private static String pathNewToOld(String str) {
            if (str != null && str.length() != 0) {
                return str.replace("com.tencent.mm.sdk.modelmsg", "com.tencent.mm.sdk.openapi");
            }
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "pathNewToOld fail, newPath is null");
            return str;
        }

        private static String pathOldToNew(String str) {
            if (str != null && str.length() != 0) {
                return str.replace("com.tencent.mm.sdk.openapi", "com.tencent.mm.sdk.modelmsg");
            }
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "pathOldToNew fail, oldPath is null");
            return str;
        }

        public static Bundle toBundle(WXMediaMessage wXMediaMessage) {
            Bundle bundle = new Bundle();
            bundle.putInt("_wxobject_sdkVer", wXMediaMessage.sdkVer);
            bundle.putString("_wxobject_title", wXMediaMessage.title);
            bundle.putString("_wxobject_description", wXMediaMessage.description);
            bundle.putByteArray("_wxobject_thumbdata", wXMediaMessage.thumbData);
            if (wXMediaMessage.mediaObject != null) {
                bundle.putString("_wxobject_identifier_", Builder.pathNewToOld(wXMediaMessage.mediaObject.getClass().getName()));
                wXMediaMessage.mediaObject.serialize(bundle);
            }
            bundle.putString("_wxobject_mediatagname", wXMediaMessage.mediaTagName);
            bundle.putString("_wxobject_message_action", wXMediaMessage.messageAction);
            bundle.putString("_wxobject_message_ext", wXMediaMessage.messageExt);
            return bundle;
        }
    }

    public WXMediaMessage() {
        this(null);
    }

    public WXMediaMessage(IMediaObject iMediaObject) {
        this.mediaObject = iMediaObject;
    }

    /* Access modifiers changed, original: final */
    public final boolean checkArgs() {
        if (getType() == 8 && (this.thumbData == null || this.thumbData.length == 0)) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, thumbData should not be null when send emoji");
            return false;
        } else if (this.thumbData != null && this.thumbData.length > 32768) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, thumbData is invalid");
            return false;
        } else if (this.title != null && this.title.length() > 512) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, title is invalid");
            return false;
        } else if (this.description != null && this.description.length() > 1024) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, description is invalid");
            return false;
        } else if (this.mediaObject == null) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, mediaObject is null");
            return false;
        } else if (this.mediaTagName != null && this.mediaTagName.length() > 64) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, mediaTagName is too long");
            return false;
        } else if (this.messageAction != null && this.messageAction.length() > 2048) {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, messageAction is too long");
            return false;
        } else if (this.messageExt == null || this.messageExt.length() <= 2048) {
            return this.mediaObject.checkArgs();
        } else {
            C4353b.m7889b("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, messageExt is too long");
            return false;
        }
    }

    public final int getType() {
        return this.mediaObject == null ? 0 : this.mediaObject.type();
    }
}
