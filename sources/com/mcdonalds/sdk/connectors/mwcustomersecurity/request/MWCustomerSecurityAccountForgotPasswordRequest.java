package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountForgotPasswordResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityAccountForgotPasswordRequest implements RequestProvider<MWCustomerSecurityAccountForgotPasswordResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/password/reset";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private MWCustomerSecurityJSONRequestBody mPostBody;
    private final String mUrl;

    public MWCustomerSecurityAccountForgotPasswordRequest(MWCustomerSecurityConnector connector, String email) {
        this.mPostBody = new MWCustomerSecurityJSONRequestBody(connector);
        this.mPostBody.put("emailAddress", email);
        this.mUrl = connector.getURLStringForEndpoint(URL_PATH);
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

    public void setBody(MWCustomerSecurityJSONRequestBody body) {
    }

    public Class<MWCustomerSecurityAccountForgotPasswordResponse> getResponseClass() {
        return MWCustomerSecurityAccountForgotPasswordResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerRegisterRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + ", mUrl=\"" + this.mUrl + "\"}";
    }
}
