package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain.MWCustomerSecurityJanrainAuthenticationNativeResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityAuthenticationRequest implements RequestProvider<MWCustomerSecurityJanrainAuthenticationNativeResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/authentication";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    protected MWCustomerSecurityJSONRequestBody mPostBody;
    private final String mUrl;

    public MWCustomerSecurityAuthenticationRequest(MWCustomerSecurityConnector connector, String userName, String password) {
        this.mPostBody = new MWCustomerSecurityJSONRequestBody(connector);
        this.mPostBody.put("emailAddress", userName);
        this.mPostBody.put("password", password);
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
        this.mPostBody = body;
    }

    public Class<MWCustomerSecurityJanrainAuthenticationNativeResponse> getResponseClass() {
        return MWCustomerSecurityJanrainAuthenticationNativeResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerSecurityAuthenticationRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }
}
