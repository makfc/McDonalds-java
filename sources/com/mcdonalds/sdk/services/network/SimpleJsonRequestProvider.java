package com.mcdonalds.sdk.services.network;

import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public abstract class SimpleJsonRequestProvider<T> implements RequestProvider<T, Object> {
    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public Map<String, String> getHeaders() {
        return null;
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
