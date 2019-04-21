package com.google.api.client.http;

import java.io.IOException;

public final class BasicAuthentication implements HttpExecuteInterceptor, HttpRequestInitializer {
    private final String password;
    private final String username;

    public void initialize(HttpRequest request) throws IOException {
        request.setInterceptor(this);
    }

    public void intercept(HttpRequest request) throws IOException {
        request.getHeaders().setBasicAuthentication(this.username, this.password);
    }
}
