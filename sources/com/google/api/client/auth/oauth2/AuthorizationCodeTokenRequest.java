package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import java.util.Collection;

public class AuthorizationCodeTokenRequest extends TokenRequest {
    @Key
    private String code;
    @Key("redirect_uri")
    private String redirectUri;

    public AuthorizationCodeTokenRequest(HttpTransport transport, JsonFactory jsonFactory, GenericUrl tokenServerUrl, String code) {
        super(transport, jsonFactory, tokenServerUrl, "authorization_code");
        setCode(code);
    }

    public AuthorizationCodeTokenRequest setRequestInitializer(HttpRequestInitializer requestInitializer) {
        return (AuthorizationCodeTokenRequest) super.setRequestInitializer(requestInitializer);
    }

    public AuthorizationCodeTokenRequest setTokenServerUrl(GenericUrl tokenServerUrl) {
        return (AuthorizationCodeTokenRequest) super.setTokenServerUrl(tokenServerUrl);
    }

    public AuthorizationCodeTokenRequest setScopes(Collection<String> scopes) {
        return (AuthorizationCodeTokenRequest) super.setScopes(scopes);
    }

    public AuthorizationCodeTokenRequest setGrantType(String grantType) {
        return (AuthorizationCodeTokenRequest) super.setGrantType(grantType);
    }

    public AuthorizationCodeTokenRequest setClientAuthentication(HttpExecuteInterceptor clientAuthentication) {
        return (AuthorizationCodeTokenRequest) super.setClientAuthentication(clientAuthentication);
    }

    public AuthorizationCodeTokenRequest setCode(String code) {
        this.code = (String) Preconditions.checkNotNull(code);
        return this;
    }

    public AuthorizationCodeTokenRequest setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public AuthorizationCodeTokenRequest set(String fieldName, Object value) {
        return (AuthorizationCodeTokenRequest) super.set(fieldName, value);
    }
}
