package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWVersionInfo {
    @SerializedName("clientApp")
    public String clientApp;
    @SerializedName("configuration")
    public MWVersionConfig configuration;
    @SerializedName("currentVersion")
    public String currentVersion;
    @SerializedName("minVersion")
    public String minVersion;

    @Deprecated
    public String getMinVersion() {
        return this.minVersion;
    }

    @Deprecated
    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    @Deprecated
    public String getCurrentVersion() {
        return this.currentVersion;
    }

    @Deprecated
    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Deprecated
    public MWVersionConfig getConfiguration() {
        return this.configuration;
    }

    @Deprecated
    public void setConfiguration(MWVersionConfig configuration) {
        this.configuration = configuration;
    }

    @Deprecated
    public String getClientApp() {
        return this.clientApp;
    }

    @Deprecated
    public void setClientApp(String clientApp) {
        this.clientApp = clientApp;
    }
}
