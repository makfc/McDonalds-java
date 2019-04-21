package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;

public class MWForceUpdateResponse extends MWJSONResponse {
    @SerializedName("version-info")
    public MWVersionInfo versionInfo;

    @Deprecated
    public MWVersionInfo getVersionInfo() {
        return this.versionInfo;
    }

    @Deprecated
    public void setVersionInfo(MWVersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }
}
