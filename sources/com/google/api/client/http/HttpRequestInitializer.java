package com.google.api.client.http;

import java.io.IOException;

public interface HttpRequestInitializer {
    void initialize(HttpRequest httpRequest) throws IOException;
}
