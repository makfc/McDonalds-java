package com.mcdonalds.app.nutrition;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class CategoryImagesRequest implements RequestProvider<CategoryImagesResponse, Void> {
    private String URLString;

    public CategoryImagesRequest(String URLString) {
        this.URLString = URLString;
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
        return this.URLString;
    }

    public Map<String, String> getHeaders() {
        Ensighten.evaluateEvent(this, "getHeaders", null);
        return null;
    }

    public String getBody() {
        Ensighten.evaluateEvent(this, "getBody", null);
        return null;
    }

    public void setBody(Void aVoid) {
        Ensighten.evaluateEvent(this, "setBody", new Object[]{aVoid});
    }

    public Class<CategoryImagesResponse> getResponseClass() {
        Ensighten.evaluateEvent(this, "getResponseClass", null);
        return CategoryImagesResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        Ensighten.evaluateEvent(this, "getCustomTypeAdapters", null);
        return null;
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return null;
    }
}
