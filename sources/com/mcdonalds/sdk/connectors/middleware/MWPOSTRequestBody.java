package com.mcdonalds.sdk.connectors.middleware;

import com.google.gson.GsonBuilder;
import java.util.LinkedHashMap;

public class MWPOSTRequestBody extends LinkedHashMap<String, Object> {
    private final String mConfigBasePath = MiddlewareConnector.CONFIG_BASE_PATH;
    private GsonBuilder mGsonBuilder = new GsonBuilder();

    public MWPOSTRequestBody(boolean serializeNulls) {
        if (serializeNulls) {
            this.mGsonBuilder = this.mGsonBuilder.serializeNulls();
        }
        putDefaults();
    }

    private void putDefaults() {
    }
}
