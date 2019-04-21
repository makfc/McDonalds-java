package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.connectors.middleware.response.DCSRegistrationResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class DCSRegistrationRequest extends DCSRequest<DCSRegistrationResponse> {
    private static final String URL_PATH = "customer/security/account";
    @SerializedName("password")
    private String password;
    @SerializedName("profile")
    private DCSProfile profile;
    @SerializedName("type")
    private String type;
    @SerializedName("loginUsername")
    private String username;

    public DCSRegistrationRequest(String username, String type, String password, DCSProfile profile) {
        super(false);
        this.username = username;
        this.type = type;
        this.password = password;
        this.profile = profile;
    }

    public Map<String, String> getHeaders() {
        return super.getHeaderMap();
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

    public Class<DCSRegistrationResponse> getResponseClass() {
        return DCSRegistrationResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
