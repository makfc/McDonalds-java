package com.google.api.client.auth.oauth2;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Data;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Map;

public class ClientParametersAuthentication implements HttpExecuteInterceptor, HttpRequestInitializer {
    private final String clientId;
    private final String clientSecret;

    public ClientParametersAuthentication(String clientId, String clientSecret) {
        this.clientId = (String) Preconditions.checkNotNull(clientId);
        this.clientSecret = clientSecret;
    }

    public void initialize(HttpRequest request) throws IOException {
        request.setInterceptor(this);
    }

    public void intercept(HttpRequest request) throws IOException {
        Map<String, Object> data = Data.mapOf(UrlEncodedContent.getContent(request).getData());
        data.put("client_id", this.clientId);
        if (this.clientSecret != null) {
            data.put("client_secret", this.clientSecret);
        }
    }
}
