package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWServerConfigurationRequest extends MWConfigurationRequest<Object, Object> {
    private static final String SERVER_CONFIG_ID = "usServerCfg";
    private static final String URL_PATH = "/application/configuration";
    private MWRequestHeaders mRequestHeaders;

    public MWServerConfigurationRequest(String ecpToken) {
        this.mRequestHeaders = getHeaderMap(ecpToken);
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public Map<String, String> getHeaders() {
        return this.mRequestHeaders;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Object body) {
    }

    public Class<Object> getResponseClass() {
        return Object.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String getConfigURL() {
        return URL_PATH;
    }

    public String getConfigurationID() {
        return SERVER_CONFIG_ID;
    }

    public String getUSConfigurationID() {
        return SERVER_CONFIG_ID;
    }
}
