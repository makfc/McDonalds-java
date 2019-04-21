package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.response.DCSChangePasswordResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;

public class DCSChangePasswordRequest extends DCSRequest<DCSChangePasswordResponse> {
    private static final String URL_PATH = "customer/security/password/change";
    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("newPasswordConfirm")
    private String newPasswordConfirm;
    @SerializedName("oldPassword")
    private String oldPassword;
    @SerializedName("username")
    private String username;

    public DCSChangePasswordRequest(String username, String oldPassword, String newPassword, String newPasswordConfirm) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
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

    public Class<DCSChangePasswordResponse> getResponseClass() {
        return DCSChangePasswordResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
