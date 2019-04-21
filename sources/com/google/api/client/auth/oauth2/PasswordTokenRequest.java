package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import java.util.Collection;

public class PasswordTokenRequest extends TokenRequest {
    public PasswordTokenRequest setRequestInitializer(HttpRequestInitializer requestInitializer) {
        return (PasswordTokenRequest) super.setRequestInitializer(requestInitializer);
    }

    public PasswordTokenRequest setTokenServerUrl(GenericUrl tokenServerUrl) {
        return (PasswordTokenRequest) super.setTokenServerUrl(tokenServerUrl);
    }

    public PasswordTokenRequest setScopes(Collection<String> scopes) {
        return (PasswordTokenRequest) super.setScopes(scopes);
    }

    public PasswordTokenRequest setGrantType(String grantType) {
        return (PasswordTokenRequest) super.setGrantType(grantType);
    }

    public PasswordTokenRequest setClientAuthentication(HttpExecuteInterceptor clientAuthentication) {
        return (PasswordTokenRequest) super.setClientAuthentication(clientAuthentication);
    }

    public PasswordTokenRequest set(String fieldName, Object value) {
        return (PasswordTokenRequest) super.set(fieldName, value);
    }
}
