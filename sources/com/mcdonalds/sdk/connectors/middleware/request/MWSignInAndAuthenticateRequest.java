package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInAndAuthenticateResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWSignInAndAuthenticateRequest extends MWRequest<MWSignInAndAuthenticateResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/session/sign-in-and-authenticate";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody;

    @Deprecated
    public MWSignInAndAuthenticateRequest(MiddlewareConnector ignored, String userName, String password, String newPassword, Map<String, Object> map) {
        this(userName, password, newPassword);
    }

    public MWSignInAndAuthenticateRequest(String userName, String password, String newPassword) {
        this.mHeaderMap = getHeaderMap();
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.generateHash();
        this.mPostBody.put("userName", userName);
        this.mPostBody.put("password", password);
        this.mPostBody.put("newPassword", newPassword);
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
