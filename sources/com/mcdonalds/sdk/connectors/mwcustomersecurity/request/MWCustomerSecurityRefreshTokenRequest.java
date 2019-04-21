package com.mcdonalds.sdk.connectors.mwcustomersecurity.request;

import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain.MWCustomerSecurityJanrainAuthenticationNativeResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerSecurityRefreshTokenRequest implements RequestProvider<MWCustomerSecurityJanrainAuthenticationNativeResponse, MWCustomerSecurityJSONRequestBody> {
    private static final String URL_PATH = "/customer/security/authentication/refresh?refreshToken=";
    private final MWCustomerSecurityRequestHeaders mHeaderMap = new MWCustomerSecurityRequestHeaders();
    private final String mUrl;

    public MWCustomerSecurityRefreshTokenRequest(MWCustomerSecurityConnector connector, String refreshToken) {
        this.mUrl = connector.getURLStringForEndpoint(URL_PATH + refreshToken);
    }

    public MethodType getMethodType() {
        return MethodType.GET;
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
        return null;
    }

    public void setBody(MWCustomerSecurityJSONRequestBody body) {
    }

    public Class<MWCustomerSecurityJanrainAuthenticationNativeResponse> getResponseClass() {
        return MWCustomerSecurityJanrainAuthenticationNativeResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerSecurityAuthenticationRequest{mHeaderMap=" + this.mHeaderMap + "}";
    }
}
