package com.google.api.client.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class TokenResponse extends GenericJson {
    @Key("access_token")
    private String accessToken;
    @Key("expires_in")
    private Long expiresInSeconds;
    @Key("refresh_token")
    private String refreshToken;

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final Long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public TokenResponse set(String fieldName, Object value) {
        return (TokenResponse) super.set(fieldName, value);
    }

    public TokenResponse clone() {
        return (TokenResponse) super.clone();
    }
}
