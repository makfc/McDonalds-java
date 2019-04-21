package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWRegisterCardRequest implements RequestProvider<String, String> {
    private static final String CUSTOMER_ID_KEY = "customerId";
    private static final String ONE_TIME_PAYMENT_KEY = "oneTimePayment";
    private static final String SESSION_ID_KEY = "sessionId";
    private String mBody;
    private final MWRequestHeaders mHeaderMap;
    private String mUrl;

    public MWRegisterCardRequest(String ecpToken, String url, String sessionId, String customerId, boolean isOneTimePayment) {
        this.mUrl = url;
        this.mBody = "sessionId=" + sessionId + "&" + CUSTOMER_ID_KEY + "=" + customerId + "&" + ONE_TIME_PAYMENT_KEY + "=" + isOneTimePayment;
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
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
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mBody;
    }

    public void setBody(String body) {
    }

    public Class<String> getResponseClass() {
        return String.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWRegisterCardRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
