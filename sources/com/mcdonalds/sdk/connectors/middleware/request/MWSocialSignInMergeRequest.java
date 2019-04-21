package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWLoginInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWLoginToAdd;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInAndAuthenticateResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWSocialSignInMergeRequest implements RequestProvider<MWSignInAndAuthenticateResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/socialLogin/associate";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody = new MWJSONRequestBody();
    private final String mUrl;

    public MWSocialSignInMergeRequest(String ecpToken, MWLoginInfo loginInfo, MWLoginToAdd loginToAdd) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        this.mPostBody.generateHash();
        this.mPostBody.put("loginInfo", loginInfo);
        this.mPostBody.put("loginToAdd", loginToAdd);
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
        this.mPostBody = body;
    }

    public Class<MWSignInAndAuthenticateResponse> getResponseClass() {
        return MWSignInAndAuthenticateResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWSignInAndAuthenticateRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }
}
