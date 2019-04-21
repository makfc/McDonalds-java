package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGeoFencingConfigurationResponse;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MWGeoFencingConfigurationRequest extends MWConfigurationRequest<MWGeoFencingConfigurationResponse, Object> {
    private static final String API_KEY = "connectors.GeoFencingConfiguration.apiKey";
    private static final String FOUNDATIONAL_CONFIG_ID = "archCfg";
    private static final String FOUNDATIONAL_US_CONFIG_ID = "USCfg";
    private Map<String, String> mHeaders = new HashMap();

    public MWGeoFencingConfigurationRequest() {
        this.mHeaders.put(MiddlewareConnector.CONFIG_HEADER_API_KEY, Configuration.getSharedInstance().getStringForKey(API_KEY));
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Object body) {
    }

    public Class<MWGeoFencingConfigurationResponse> getResponseClass() {
        return MWGeoFencingConfigurationResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return null;
    }

    public String getConfigURL() {
        return "/application/configuration";
    }

    public String getConfigurationID() {
        return FOUNDATIONAL_CONFIG_ID;
    }

    public String getUSConfigurationID() {
        return FOUNDATIONAL_US_CONFIG_ID;
    }
}
