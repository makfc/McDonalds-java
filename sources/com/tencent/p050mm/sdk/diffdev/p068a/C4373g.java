package com.tencent.p050mm.sdk.diffdev.p068a;

import com.autonavi.amap.mapcore.VTMCDataCache;

/* renamed from: com.tencent.mm.sdk.diffdev.a.g */
public enum C4373g {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(VTMCDataCache.MAXSIZE);
    
    private int code;

    private C4373g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    public final String toString() {
        return "UUIDStatusCode:" + this.code;
    }
}
