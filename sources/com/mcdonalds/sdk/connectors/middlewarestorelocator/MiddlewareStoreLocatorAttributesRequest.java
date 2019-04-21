package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import com.mcdonalds.sdk.connectors.middleware.request.MWRequestHeaders;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.model.MiddlewareStoreLocatorAttribute;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiddlewareStoreLocatorAttributesRequest implements RequestProvider<MiddlewareStoreLocatorAttribute[], Object> {
    private Map<String, String> mHeaderMap = new MWRequestHeaders();
    private String mUrl;

    public MiddlewareStoreLocatorAttributesRequest(String url) {
        this.mUrl = url;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Class<MiddlewareStoreLocatorAttribute[]> getResponseClass() {
        return MiddlewareStoreLocatorAttribute[].class;
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public void addHeader(String key, String value) {
        if (this.mHeaderMap == null) {
            this.mHeaderMap = new HashMap();
        }
        this.mHeaderMap.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Object body) {
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
