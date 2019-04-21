package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.response.DCSApplicationSecurityResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;

public class DCSApplicationSecurityRequest extends DCSRequest<DCSApplicationSecurityResponse> {
    private static final String URL_PATH = "security/application/authentication";

    public DCSApplicationSecurityRequest() {
        super(false);
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getEndpoint() {
        return URL_PATH;
    }

    public Class<DCSApplicationSecurityResponse> getResponseClass() {
        return DCSApplicationSecurityResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
