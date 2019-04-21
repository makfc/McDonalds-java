package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerActivationCodeRequest implements RequestProvider<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/activation/code";
    private final MWJSONRequestBody mBody = new MWJSONRequestBody();
    private final MWRequestHeaders mHeaderMap;
    private String mUrl;

    public MWCustomerActivationCodeRequest(String ecpToken, String code) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mBody.put("hashCode", code);
        this.mUrl = MiddlewareConnector.getURLStringForEndpoint(URL_PATH);
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerResendActivationRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
