package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.rdisoftware.security.RdiSecurity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class MWCustomerSecurityJSONRequestBody extends LinkedHashMap<String, Object> {
    private final String mConfigBasePath;
    private GsonBuilder mGsonBuilder;

    public MWCustomerSecurityJSONRequestBody() {
        this.mGsonBuilder = new GsonBuilder().serializeNulls();
        this.mConfigBasePath = MiddlewareConnector.CONFIG_BASE_PATH;
        putDefaults();
    }

    public MWCustomerSecurityJSONRequestBody(MWCustomerSecurityConnector connector) {
        this.mGsonBuilder = new GsonBuilder().serializeNulls();
        this.mConfigBasePath = connector.getConfigBasePath();
        putDefaults();
    }

    public MWCustomerSecurityJSONRequestBody(boolean serializeNulls) {
        this.mConfigBasePath = MiddlewareConnector.CONFIG_BASE_PATH;
        this.mGsonBuilder = new GsonBuilder();
        if (serializeNulls) {
            this.mGsonBuilder = this.mGsonBuilder.serializeNulls();
        }
        putDefaults();
    }

    public void generateHash() {
        String nonce = "happybaby";
        String versionId = (String) Configuration.getSharedInstance().getValueForKey(this.mConfigBasePath + ".versionId");
        String hash = RdiSecurity.computeHash((String) get("application"), versionId, "happybaby");
        put("versionId", versionId);
        put("nonce", "happybaby");
        put("hash", hash);
    }

    private void putDefaults() {
        put("marketId", Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + ".marketId"));
        put("application", Configuration.getSharedInstance().getStringForKey(this.mConfigBasePath + ".application"));
        put("languageName", Configuration.getSharedInstance().getCurrentLanguageTag());
        put("locale", Locale.getDefault().toString().replace('_', '-'));
        put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, "android");
    }

    public String toJson() {
        return toJson(null);
    }

    public String toJson(List<? extends CustomTypeAdapter> customTypeAdapters) {
        if (customTypeAdapters != null) {
            int size = customTypeAdapters.size();
            for (int i = 0; i < size; i++) {
                CustomTypeAdapter customTypeAdapter = (CustomTypeAdapter) customTypeAdapters.get(i);
                if (customTypeAdapter.getSerializer() != null) {
                    this.mGsonBuilder = this.mGsonBuilder.registerTypeAdapter(customTypeAdapter.getType(), customTypeAdapter.getSerializer());
                }
                if (customTypeAdapter.getDeserializer() != null) {
                    this.mGsonBuilder = this.mGsonBuilder.registerTypeAdapter(customTypeAdapter.getType(), customTypeAdapter.getDeserializer());
                }
            }
        }
        Gson create = this.mGsonBuilder.create();
        return !(create instanceof Gson) ? create.toJson((Object) this) : GsonInstrumentation.toJson(create, (Object) this);
    }

    public String toString() {
        return toJson();
    }
}
