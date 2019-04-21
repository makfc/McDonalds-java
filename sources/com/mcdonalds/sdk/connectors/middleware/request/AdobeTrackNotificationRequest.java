package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class AdobeTrackNotificationRequest implements RequestProvider<Void, MWJSONRequestBody> {
    private static String URL_METHOD_PATH = "/r/?";
    protected MWGETQueryArgs mQueryArgs = new MWGETQueryArgs();
    private String mUrl;

    public AdobeTrackNotificationRequest(String baseURL, String messageId, String deliveryId, int tagID) {
        this.mQueryArgs.clear();
        this.mQueryArgs.put("id", String.format("%s,%s,%d", new Object[]{messageId, deliveryId, Integer.valueOf(tagID)}));
        this.mUrl = baseURL + URL_METHOD_PATH;
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return String.format("%s%s", new Object[]{this.mUrl, this.mQueryArgs.toString()});
    }

    public Map<String, String> getHeaders() {
        return null;
    }

    public String getBody() {
        return null;
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<Void> getResponseClass() {
        return Void.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
