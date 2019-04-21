package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.response.DCSAuthenticationResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;

public class DCSRefreshTokenRequest extends DCSRequest<DCSAuthenticationResponse> {
    private static final String URL_PATH = "customer/security/authentication/refresh";
    public String refreshToken = "";

    public DCSRefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
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

    public Class<DCSAuthenticationResponse> getResponseClass() {
        return DCSAuthenticationResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
