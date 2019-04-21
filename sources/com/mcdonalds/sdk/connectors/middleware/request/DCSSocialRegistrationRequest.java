package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;

public class DCSSocialRegistrationRequest extends DCSRegistrationRequest {
    @SerializedName("provider")
    private String provider;
    @SerializedName("idpToken")
    private String token;

    public DCSSocialRegistrationRequest(String username, String type, String token, String provider, DCSProfile profile) {
        super(username, type, null, profile);
        this.token = token;
        this.provider = provider;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return "type=social";
    }
}
