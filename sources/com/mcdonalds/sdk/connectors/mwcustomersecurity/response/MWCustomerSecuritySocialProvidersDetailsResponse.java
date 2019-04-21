package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecuritySocialProvidersDetailsResponse {
    @SerializedName("appId")
    private String appId;
    @SerializedName("icon")
    private String icon;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String toString() {
        return "MWCustomerSecuritySocialProvidersDetailsResponse{name='" + this.name + '\'' + ", url='" + this.url + '\'' + ", icon='" + this.icon + '\'' + ", appId='" + this.appId + '\'' + '}';
    }
}
