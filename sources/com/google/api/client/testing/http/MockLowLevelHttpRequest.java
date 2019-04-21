package com.google.api.client.testing.http;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Beta
public class MockLowLevelHttpRequest extends LowLevelHttpRequest {
    private final Map<String, List<String>> headersMap = new HashMap();
    private MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
    private String url;

    public MockLowLevelHttpRequest(String url) {
        this.url = url;
    }

    public void addHeader(String name, String value) throws IOException {
        name = name.toLowerCase();
        List<String> values = (List) this.headersMap.get(name);
        if (values == null) {
            values = new ArrayList();
            this.headersMap.put(name, values);
        }
        values.add(value);
    }

    public LowLevelHttpResponse execute() throws IOException {
        return this.response;
    }

    public MockLowLevelHttpRequest setResponse(MockLowLevelHttpResponse response) {
        this.response = response;
        return this;
    }
}
