package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.util.Map;

public abstract class DCSRequest<T> extends MWRequest<T, Void> {
    private transient MWRequestHeaders mHeaders;

    public DCSRequest(boolean needsToken) {
        String token = null;
        if (needsToken) {
            token = SessionManager.getInstance().getToken();
        }
        this.mHeaders = getHeaderMap(token);
    }

    public DCSRequest() {
        this(true);
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getBody() {
        Gson gson = new GsonBuilder().create();
        return !(gson instanceof Gson) ? gson.toJson((Object) this) : GsonInstrumentation.toJson(gson, (Object) this);
    }

    @Deprecated
    public void setBody(Void body) {
    }
}
