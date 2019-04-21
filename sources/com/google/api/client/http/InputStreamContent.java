package com.google.api.client.http;

import java.io.InputStream;

public final class InputStreamContent extends AbstractInputStreamContent {
    private final InputStream inputStream;
    private long length;
    private boolean retrySupported;

    public long getLength() {
        return this.length;
    }

    public boolean retrySupported() {
        return this.retrySupported;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }
}
