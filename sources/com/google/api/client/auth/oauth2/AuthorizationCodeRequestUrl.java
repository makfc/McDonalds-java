package com.google.api.client.auth.oauth2;

import com.mcdonalds.sdk.modules.models.TenderType;
import java.util.Collection;
import java.util.Collections;

public class AuthorizationCodeRequestUrl extends AuthorizationRequestUrl {
    public AuthorizationCodeRequestUrl(String authorizationServerEncodedUrl, String clientId) {
        super(authorizationServerEncodedUrl, clientId, Collections.singleton(TenderType.COLUMN_CODE));
    }

    public AuthorizationCodeRequestUrl setResponseTypes(Collection<String> responseTypes) {
        return (AuthorizationCodeRequestUrl) super.setResponseTypes(responseTypes);
    }

    public AuthorizationCodeRequestUrl setRedirectUri(String redirectUri) {
        return (AuthorizationCodeRequestUrl) super.setRedirectUri(redirectUri);
    }

    public AuthorizationCodeRequestUrl setScopes(Collection<String> scopes) {
        return (AuthorizationCodeRequestUrl) super.setScopes(scopes);
    }

    public AuthorizationCodeRequestUrl setClientId(String clientId) {
        return (AuthorizationCodeRequestUrl) super.setClientId(clientId);
    }

    public AuthorizationCodeRequestUrl set(String fieldName, Object value) {
        return (AuthorizationCodeRequestUrl) super.set(fieldName, value);
    }

    public AuthorizationCodeRequestUrl clone() {
        return (AuthorizationCodeRequestUrl) super.clone();
    }
}
