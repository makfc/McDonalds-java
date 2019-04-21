package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWDevice {
    @SerializedName("DeviceOperatingSystem")
    private Integer deviceOperatingSystem;
    @SerializedName("DeviceToken")
    private String deviceToken;

    public MWDevice(String deviceToken, Integer deviceOperatingSystem) {
        this.deviceToken = deviceToken;
        this.deviceOperatingSystem = deviceOperatingSystem;
    }

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public Integer getDeviceOperatingSystem() {
        return this.deviceOperatingSystem;
    }
}
