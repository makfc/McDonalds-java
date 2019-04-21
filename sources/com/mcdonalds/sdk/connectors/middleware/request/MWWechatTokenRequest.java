package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWWechatTokenResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWWechatTokenRequest implements RequestProvider<MWWechatTokenResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/session/socialLogin/token";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody = new MWJSONRequestBody();
    private final String mUrl;

    public MWWechatTokenRequest(String ecpToken, int socialNetworkId, String code) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mPostBody.put("socialNetworkId", Integer.valueOf(socialNetworkId));
        this.mPostBody.put("requestCode", code);
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
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWWechatTokenResponse> getResponseClass() {
        return MWWechatTokenResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWValidateOrderRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + getBody() + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
