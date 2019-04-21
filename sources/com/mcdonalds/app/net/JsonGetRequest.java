package com.mcdonalds.app.net;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class JsonGetRequest implements RequestProvider {
    private String mGetUrl;
    private Class mResponseClass;

    public JsonGetRequest(String url, Class reponseClass) {
        this.mGetUrl = url;
        this.mResponseClass = reponseClass;
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
        return this.mGetUrl;
    }

    public Map<String, String> getHeaders() {
        Ensighten.evaluateEvent(this, "getHeaders", null);
        return null;
    }

    public String getBody() {
        Ensighten.evaluateEvent(this, "getBody", null);
        return null;
    }

    public void setBody(Object body) {
        Ensighten.evaluateEvent(this, "setBody", new Object[]{body});
    }

    public Class getResponseClass() {
        Ensighten.evaluateEvent(this, "getResponseClass", null);
        return this.mResponseClass;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        Ensighten.evaluateEvent(this, "getCustomTypeAdapters", null);
        return null;
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return String.format("[Request url = %s]", new Object[]{this.mGetUrl});
    }
}
