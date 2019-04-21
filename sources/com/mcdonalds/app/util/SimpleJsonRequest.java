package com.mcdonalds.app.util;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class SimpleJsonRequest implements RequestProvider {
    private Class mResultClass;
    private String mUrl;

    public SimpleJsonRequest(String url, Class resultClass) {
        this.mUrl = url;
        this.mResultClass = resultClass;
    }

    public MethodType getMethodType() {
        Ensighten.evaluateEvent(this, "getMethodType", null);
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        Ensighten.evaluateEvent(this, "getRequestType", null);
        return RequestType.JSON;
    }

    public String getURLString() {
        Ensighten.evaluateEvent(this, "getURLString", null);
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        Ensighten.evaluateEvent(this, "getHeaders", null);
        return null;
    }

    public String getBody() {
        Ensighten.evaluateEvent(this, "getBody", null);
        return null;
    }

    public void setBody(Object object) {
        Ensighten.evaluateEvent(this, "setBody", new Object[]{object});
    }

    public Class getResponseClass() {
        Ensighten.evaluateEvent(this, "getResponseClass", null);
        return this.mResultClass;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        Ensighten.evaluateEvent(this, "getCustomTypeAdapters", null);
        return null;
    }
}
