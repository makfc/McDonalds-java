package com.google.api.client.testing.http;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Set;

@Beta
public class MockHttpTransport extends HttpTransport {
    private MockLowLevelHttpRequest lowLevelHttpRequest;
    private MockLowLevelHttpResponse lowLevelHttpResponse;
    private Set<String> supportedMethods;

    @Beta
    public static class Builder {
    }

    public boolean supportsMethod(String method) throws IOException {
        return this.supportedMethods == null || this.supportedMethods.contains(method);
    }

    public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        Preconditions.checkArgument(supportsMethod(method), "HTTP method %s not supported", method);
        if (this.lowLevelHttpRequest != null) {
            return this.lowLevelHttpRequest;
        }
        LowLevelHttpRequest request = new MockLowLevelHttpRequest(url);
        if (this.lowLevelHttpResponse == null) {
            return request;
        }
        request.setResponse(this.lowLevelHttpResponse);
        return request;
    }
}
