package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.response.DCSAuthenticationResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.Collections;
import java.util.List;

public class DCSAuthenticationRequest extends DCSRequest<DCSAuthenticationResponse> {
    private static final String URL_PATH = "customer/security/authentication";
    @SerializedName("password")
    private String password;
    @SerializedName("type")
    private String type;
    @SerializedName("loginUsername")
    private String username;

    public DCSAuthenticationRequest(String username, String type, String password) {
        super(false);
        this.username = username;
        this.type = type;
        this.password = password;
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

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return "type=traditional";
    }

    public Class<DCSAuthenticationResponse> getResponseClass() {
        return DCSAuthenticationResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return Collections.singletonList(new PreferenceDetailsCustomTypeAdapter());
    }
}
