package com.google.api.client.auth.openidconnect;

import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.io.IOException;

@Beta
public class IdTokenResponse extends TokenResponse {
    @Key("id_token")
    private String idToken;

    public IdToken parseIdToken() throws IOException {
        return IdToken.parse(getFactory(), this.idToken);
    }

    public static IdTokenResponse execute(TokenRequest tokenRequest) throws IOException {
        return (IdTokenResponse) tokenRequest.executeUnparsed().parseAs(IdTokenResponse.class);
    }

    public IdTokenResponse set(String fieldName, Object value) {
        return (IdTokenResponse) super.set(fieldName, value);
    }

    public IdTokenResponse clone() {
        return (IdTokenResponse) super.clone();
    }
}
