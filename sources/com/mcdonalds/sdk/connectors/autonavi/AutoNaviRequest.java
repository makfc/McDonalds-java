package com.mcdonalds.sdk.connectors.autonavi;

import com.mcdonalds.sdk.services.network.SimpleJsonRequestProvider;

public class AutoNaviRequest extends SimpleJsonRequestProvider<AutoNaviResponse> {
    private String mUrl;

    public AutoNaviRequest(String url) {
        this.mUrl = url;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Class<AutoNaviResponse> getResponseClass() {
        return AutoNaviResponse.class;
    }
}
