package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import java.util.Collection;

public class ClientCredentialsTokenRequest extends TokenRequest {
    public ClientCredentialsTokenRequest setRequestInitializer(HttpRequestInitializer requestInitializer) {
        return (ClientCredentialsTokenRequest) super.setRequestInitializer(requestInitializer);
    }

    public ClientCredentialsTokenRequest setTokenServerUrl(GenericUrl tokenServerUrl) {
        return (ClientCredentialsTokenRequest) super.setTokenServerUrl(tokenServerUrl);
    }

    public ClientCredentialsTokenRequest setScopes(Collection<String> scopes) {
        return (ClientCredentialsTokenRequest) super.setScopes(scopes);
    }

    public ClientCredentialsTokenRequest setGrantType(String grantType) {
        return (ClientCredentialsTokenRequest) super.setGrantType(grantType);
    }

    public ClientCredentialsTokenRequest setClientAuthentication(HttpExecuteInterceptor clientAuthentication) {
        return (ClientCredentialsTokenRequest) super.setClientAuthentication(clientAuthentication);
    }

    public ClientCredentialsTokenRequest set(String fieldName, Object value) {
        return (ClientCredentialsTokenRequest) super.set(fieldName, value);
    }
}
