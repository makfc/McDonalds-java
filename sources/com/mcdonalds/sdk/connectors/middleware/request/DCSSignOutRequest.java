package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.response.DCSResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;

public class DCSSignOutRequest extends DCSRequest<DCSResponse> {
    private static final String URL_PATH = "customer/security/authentication";
    @SerializedName("refreshToken")
    public String refreshToken = "";

    public DCSSignOutRequest(String refreshToken) {
        this.refreshToken = refreshToken;
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

    public Class<DCSResponse> getResponseClass() {
        return DCSResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
