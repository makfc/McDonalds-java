package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.annotations.SerializedName;

public class DCSSocialAuthenticationRequest extends DCSAuthenticationRequest {
    @SerializedName("provider")
    private String provider;
    @SerializedName("idpToken")
    private String token;

    public DCSSocialAuthenticationRequest(String username, String type, String token, String provider) {
        super(username, type, null);
        this.token = token;
        this.provider = provider;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return "type=social";
    }
}
