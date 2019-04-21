package com.google.api.client.http.apache;

import com.google.api.client.util.Preconditions;
import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

final class HttpExtensionMethod extends HttpEntityEnclosingRequestBase {
    private final String methodName;

    public HttpExtensionMethod(String methodName, String uri) {
        this.methodName = (String) Preconditions.checkNotNull(methodName);
        setURI(URI.create(uri));
    }

    public String getMethod() {
        return this.methodName;
    }
}
