package com.google.api.client.http;

import java.io.IOException;

public final class HttpRequestFactory {
    private final HttpRequestInitializer initializer;
    private final HttpTransport transport;

    HttpRequestFactory(HttpTransport transport, HttpRequestInitializer initializer) {
        this.transport = transport;
        this.initializer = initializer;
    }

    public HttpRequest buildRequest(String requestMethod, GenericUrl url, HttpContent content) throws IOException {
        HttpRequest request = this.transport.buildRequest();
        if (this.initializer != null) {
            this.initializer.initialize(request);
        }
        request.setRequestMethod(requestMethod);
        if (url != null) {
            request.setUrl(url);
        }
        if (content != null) {
            request.setContent(content);
        }
        return request;
    }

    public HttpRequest buildPostRequest(GenericUrl url, HttpContent content) throws IOException {
        return buildRequest("POST", url, content);
    }
}
