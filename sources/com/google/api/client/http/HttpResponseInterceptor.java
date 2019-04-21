package com.google.api.client.http;

import java.io.IOException;

public interface HttpResponseInterceptor {
    void interceptResponse(HttpResponse httpResponse) throws IOException;
}
