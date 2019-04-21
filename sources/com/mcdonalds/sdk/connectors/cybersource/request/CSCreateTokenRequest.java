package com.mcdonalds.sdk.connectors.cybersource.request;

import android.net.Uri;
import android.net.Uri.Builder;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSCreateTokenRequest implements RequestProvider<String, HashMap<String, String>> {
    private HashMap<String, String> mBody;
    private String mUrl;

    public CSCreateTokenRequest(HashMap<String, String> body, String url) {
        this.mUrl = url;
        this.mBody = body;
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.HTML;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        return null;
    }

    public String getBody() {
        Builder builder = Uri.parse("").buildUpon();
        for (String key : this.mBody.keySet()) {
            builder.appendQueryParameter(key, (String) this.mBody.get(key));
        }
        return builder.build().toString().replace("?", "");
    }

    public void setBody(HashMap<String, String> hashMap) {
    }

    public Class<String> getResponseClass() {
        return String.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
