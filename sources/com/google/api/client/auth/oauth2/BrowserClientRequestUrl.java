package com.google.api.client.auth.oauth2;

import java.util.Collection;

public class BrowserClientRequestUrl extends AuthorizationRequestUrl {
    public BrowserClientRequestUrl setResponseTypes(Collection<String> responseTypes) {
        return (BrowserClientRequestUrl) super.setResponseTypes(responseTypes);
    }

    public BrowserClientRequestUrl setRedirectUri(String redirectUri) {
        return (BrowserClientRequestUrl) super.setRedirectUri(redirectUri);
    }

    public BrowserClientRequestUrl setScopes(Collection<String> scopes) {
        return (BrowserClientRequestUrl) super.setScopes(scopes);
    }

    public BrowserClientRequestUrl setClientId(String clientId) {
        return (BrowserClientRequestUrl) super.setClientId(clientId);
    }

    public BrowserClientRequestUrl set(String fieldName, Object value) {
        return (BrowserClientRequestUrl) super.set(fieldName, value);
    }

    public BrowserClientRequestUrl clone() {
        return (BrowserClientRequestUrl) super.clone();
    }
}
