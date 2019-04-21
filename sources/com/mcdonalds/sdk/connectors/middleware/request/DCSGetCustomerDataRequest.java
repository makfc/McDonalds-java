package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.response.DCSGetProfileResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Collections;
import java.util.List;

public class DCSGetCustomerDataRequest extends DCSRequest<DCSGetProfileResponse> {
    private static final String URL_PATH = "customer/security/account";
    private final String mUsername;

    public DCSGetCustomerDataRequest(String username) {
        this.mUsername = username;
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return "selector=full&username=" + this.mUsername;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    public Class<DCSGetProfileResponse> getResponseClass() {
        return DCSGetProfileResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Collections.singletonList(new PreferenceDetailsCustomTypeAdapter());
    }
}
