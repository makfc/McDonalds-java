package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWSocialLoginInfo;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInAndAuthenticateResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWSocialSignInRequest extends MWRequest<MWSignInAndAuthenticateResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/session/socialLogin";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody;

    @Deprecated
    public MWSocialSignInRequest(MiddlewareConnector ignored, MWSocialLoginInfo loginInfo) {
        this(loginInfo);
    }

    public MWSocialSignInRequest(MWSocialLoginInfo loginInfo) {
        this.mHeaderMap = getHeaderMap();
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.generateHash();
        this.mPostBody.put("loginInfo", loginInfo);
        this.mPostBody.put("Catalog", new ArrayList());
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
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
