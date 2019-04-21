package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerChangePasswordRequest extends MWRequest<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/password";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWCustomerChangePasswordRequest(MiddlewareConnector ignored, String ecpToken, String userName, String oldPassword, String newPassword) {
        this(ecpToken, userName, oldPassword, newPassword);
    }

    public MWCustomerChangePasswordRequest(String ecpToken, String userName, String oldPassword, String newPassword) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", userName);
        this.mPostBody.put("password", oldPassword);
        this.mPostBody.put("newPassword", newPassword);
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
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
    }

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
